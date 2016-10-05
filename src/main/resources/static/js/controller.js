var app = angular.module('hh_app', [ 'ngMaterial', 'ngMessages', 'ngResource' ]).controller("hh_view", function($scope) {

	
});

app.factory("hhHistory", function($resource, $log) {
	return $resource("/hh/api/history/:id");
});

app.factory("hhBestMatch", function($resource, $log) {
	return $resource("/hh/api/bestmatch/:name");
});

app.factory("hhStatsMap", function($resource, $log) {
	return $resource("/hh/api/stats/map/:map");
});

app.controller('hh_controller', function($scope, $log, hhHistory, hhBestMatch, hhStatsMap) {

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
				
				var statsMap = hhStatsMap.get({ map : update.map
				}, function(statsMap) {
					$scope.heroMapStats = statsMap.heroMapStats;
				});
				
				for (var int = 0; int < update.enemies.length; int++) {
					
					var bestMatch = hhBestMatch.get({ name : update.enemies[int]
					}, function(bestMatch) {
						bestMatch.matches =  [ {
							map : "loading",
							hero : "loading",
							lvl : "0"
						} ];
						
						bestMatch.statistics =  [ {
							hero : "loading",
							lvl : "0",
							percentage : "0",
							number: 0
						} ];
						
						if (!(angular.isUndefined(bestMatch.id) || bestMatch.id === null)) {
							hhHistory.get({
								id : bestMatch.id
							}, function(history) {
								
								bestMatch.matches = history.rows;
								bestMatch.statistics = history.statistics;
							});
						}
						
					});
					
					$scope.enemies[int] = bestMatch;
				}
				
				for (var int = 0; int < update.friends.length; int++) {
					
					var bestMatch = hhBestMatch.get({ name : update.friends[int]
					}, function(bestMatch) {
						bestMatch.matches =  [ {
							map : "loading",
							hero : "loading",
							lvl : "0"
						} ];
						
						bestMatch.statistics =  [ {
							hero : "loading",
							lvl : "0",
							percentage : "0",
							number: 0
						} ];
						
						if (!(angular.isUndefined(bestMatch.id) || bestMatch.id === null)) {
							hhHistory.get({
								id : bestMatch.id
							}, function(history) {
								
								bestMatch.matches = history.rows;
								bestMatch.statistics = history.statistics;
							});
						}
						
					});
					
					$scope.allies[int] = bestMatch;
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
				bestMatch.matches =  [ {
					map : "loading",
					hero : "loading",
					lvl : "0"
				} ];
				
				if (!(angular.isUndefined(bestMatch.id) || bestMatch.id === null)) {
					hhHistory.get({
						id : bestMatch.id
					}, function(history) {
						bestMatch.matches = history.rows;
						bestMatch.statistics = history.statistics;
					});
				}
				
			});
		 
		if (row == 0) {
			 $scope.enemies[index] = bestMatch;
		} else {
			$scope.allies[index] = bestMatch;
		}
			
			
	}
	
	$scope.test = function() {
		
		$log.info("Test");
		
		stompClient.send("/app/screenupdate", {}, JSON.stringify({"friends":["PandaAttack","Czarny","Zander","Ziggy69","SalazarPT"],"enemies":["huzzler","Gurkchen","szept","Sh33p","KorzoN"],"map":"BRAXIS HOLDOUT"}));
		
		$log.info("SENDED");
		
//		var enemies = [];
//		enemies.push("Barsig");
//		enemies.push("Gurkchen");
//		enemies.push("s3phster");
//		enemies.push("Murat");
//		enemies.push("HellBreath");
//		
//		for (var int = 0; int < enemies.length; int++) {
//			
//			var bestMatch = hhBestMatch.get({ name : enemies[int]
//			}, function(bestMatch) {
//				bestMatch.matches =  [ {
//					map : "loading",
//					hero : "loading",
//					lvl : "0"
//				} ];
//				
//				if (!(angular.isUndefined(bestMatch.id) || bestMatch.id === null)) {
//					hhHistory.get({
//						id : bestMatch.id
//					}, function(history) {
//						bestMatch.matches = history.rows;
//						bestMatch.statistics = history.statistics;
//					});
//				}
//				
//			});
//			
//			$scope.enemies[int] = bestMatch;
//		}
	}

});