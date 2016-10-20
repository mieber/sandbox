var app = angular.module('hh_app', [ 'ngMaterial', 'ngMessages', 'ngResource' ]).controller("hh_view", function($scope) {

	
});

app.factory("hhStatsPlayer", function($resource, $log) {
	return $resource("/hh/api/stats/player/:id/:map");
});

app.factory("hhBestMatch", function($resource, $log) {
	return $resource("/hh/api/bestmatch/:name");
});

app.factory("hhStatsMap", function($resource, $log) {
	return $resource("/hh/api/stats/map/:map");
});

app.factory("hhStatsHero", function($resource, $log) {
	return $resource("/hh/api/stats/hero");
});

app.factory("hhUpdateStats", function($resource, $log) {
	return $resource("hh/api/updatestats");
});

app.controller('hh_controller', function($scope, $log, hhStatsPlayer, hhBestMatch, hhStatsMap, hhUpdateStats, hhStatsHero) {

	$scope.stompClient = null;
	
	$scope.enemies = [];
	
	$scope.allies = [];
	
	$scope.map = null;
	
	$scope.heroMapStats = [];

	$scope.connect = function() {
		$log.info("TEST");
		var socket = new SockJS('/screenupdate');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			$log.info('Connected: ' + frame);
			stompClient.subscribe('/topic/updates', function(update) {
				var update = JSON.parse(update.body)
				$log.info('received e: ' + update.enemies)
				$log.info('received a: ' + update.friends)
				$log.info('received m: ' + update.map)
				
				$scope.map = update.map;
				
				$scope.allyHeroes = update.friendHeroes;
				$scope.enemyHeroes = update.enemyHeroes;
					
				
				var statsMap = hhStatsMap.get({ map : update.map}, function(statsMap) {
					$scope.heroMapStats = statsMap.heroMapStats;
				});
				
				var statsHero = hhStatsHero.save(update, function(statsHero) {
					$scope.allyHeroWinStats = statsHero.allyStats;
					$scope.enemyHeroWinStats = statsHero.enemyStats;
				});
				
				for (var int = 0; int < update.enemies.length; int++) {
					
					var bestMatch = hhBestMatch.get({ name : update.enemies[int] }, function(bestMatch) {
						if (!(angular.isUndefined(bestMatch.id) || bestMatch.id === null)) {
							hhStatsPlayer.get({
								id : bestMatch.id,
								map: update.map
							}, function(history) {
								
								bestMatch.matches = history.rows;
								bestMatch.statistics = history.statistics;
								bestMatch.winrate = history.winrate;
							});
						} else {
							bestMatch.matches =  [ {
								hero : "nothing found"
							} ];
						}
						
					});
					
					$scope.enemies[int] = bestMatch;
					$scope.enemies[int].name = update.enemies[int];
				}
				
				for (var int = 0; int < update.friends.length; int++) {
					
					var bestMatch = hhBestMatch.get({ name : update.friends[int]}, function(bestMatch) {
						
						if (!(angular.isUndefined(bestMatch.id) || bestMatch.id === null)) {
							hhStatsPlayer.get({
								id : bestMatch.id,
								map : update.map
							}, function(history) {
								
								bestMatch.matches = history.rows;
								bestMatch.statistics = history.statistics;
								bestMatch.winrate = history.winrate;
							});
						} else {
							bestMatch.matches =  [ {
								hero : "nothing found"
							} ];
						}
						
					});
					
					$scope.allies[int] = bestMatch;
					$scope.allies[int].name = update.friends[int];
				}
			});
		});
	}

	$scope.disconnect = function() {

		 stompClient.disconnect();
		 $log.info("Disconnected");
	}
	
	$scope.update = function(index, row) {
		var element;
		
		if (row == 0) {
			element = $scope.enemies[index].name;
		} else {
			element = $scope.allies[index].name;
		}

		 $log.info("Update: " + index + "/" + row);
		 $log.info("Update: " + element);
		 
		 var bestMatch = hhBestMatch.get({ name : element
			}, function(bestMatch) {
				if (!(angular.isUndefined(bestMatch.id) || bestMatch.id === null)) {
					hhStatsPlayer.get({
						id : bestMatch.id,
						map : $scope.map
					}, function(history) {
						bestMatch.matches = history.rows;
						bestMatch.statistics = history.statistics;
						bestMatch.winrate = history.winrate;
					});
				} else {
					bestMatch.matches =  [ {
						hero : "nothing found"
					} ];
				}
				
			});
		 
		if (row == 0) {
			 $scope.enemies[index] = bestMatch;
			 $scope.enemies[index].name = element;
		} else {
			$scope.allies[index] = bestMatch;
			$scope.allies[index].name = element;

		}
	}
	
	$scope.updateFromHots = function() {

		 $log.info("Update from hots started.");
		 
		 hhUpdateStats.get();
		 
		 $log.info("Done calling the rest EP. Now the pop up should come up.");
	}
	
	$scope.test = function() {
		
		$log.info("Test");
		
		stompClient.send("/app/screenupdate", {}, JSON.stringify({"friends":["PandaAttack","Czarny","Zander","Ziggy69","SalazarPT"],"enemies":["huzzler","Gurkchen","szept","Sh33p","KorzoN"],"friendHeroes":["Muradin", "JAINA", "ZagAra", "BrightWIng", ". ."], "enemyHeroes":["KERRIGAN", "Falstad", null, "Diablo", "Xul"],"map":"BRAXIS HOLDOUT"}));
		
		$log.info("SENDED");
	}
	
	$scope.getColor = function(threatIn, min, max, inverse) {
		
		// normalize threat from min to max 
		var threat = Math.max(threatIn, min);
		threat = Math.min(threat, max);
		
		// normalize from 0 to 1
		var normalized = (threat - min) / (max - min);
		
		var r; 
		var g
		
		if (normalized <= 0.5) {
			r = Math.min(Math.round(normalized * 255 * 2), 255);
			g = 255;
		} else {
			r = 255;
			g = Math.min(Math.round((1 - normalized) * 255 * 2), 255);
		}
		
		if (inverse) {
			return "" + g + "," + r + ",0";
		}
		
		return "" + r + "," + g + ",0";
	}
	
	

});