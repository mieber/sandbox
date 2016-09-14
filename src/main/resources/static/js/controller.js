var app = angular.module('hh_app', ['ngMaterial','ngResource']).controller("hh_view", function($scope) {

	$scope.headers = {
		values : [ "Map", "Hero", "Won" ]
	}
	
	$scope.add_button = "Add Person";
	
	$scope.stompConnected = false;
});

app.factory(
		"hhHistory",
		function($resource, $log) {
			return $resource("/hh/api/history/:id");
		});

app.factory(
		"hhBestMatch",
		function($resource, $log) {
			return $resource("/hh/api/bestmatch/:name");
		});

app.controller('hh_controller', function($scope, $log, hhHistory, hhBestMatch) {
	
	$scope.stompClient = null;
	
	$scope.matches1 = [];
	$scope.matches2 = [];
	$scope.matches3 = [];
	$scope.matches4 = [];
	$scope.matches5 = [];

	$scope.add = function() {
		var firstname = 'A';
		var lastname = 'b';

//		private String id;
//
//		private boolean win;
//
//		private String map;
//
//		private String length;
//
//		private String Hero;
//
//		private String Lvl;
//
//		private String mmr;
//
//		private String mmrChange;
//
//		private String date;
		var person = {id: 4387231, map: "Test", hero: "Hero", lvl: "12"};
		$scope.matches1.push(person);
		$scope.matches2.push(person);
		$scope.matches3.push(person);
		$scope.matches4.push(person);
		$scope.matches5.push(person);
	}

	$scope.connect = function() {
		$log.info("TEST");
		var socket = new SockJS('/screenupdate');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			$scope.stompConnected = true;
			$log.info('Connected: ' + frame);
			stompClient.subscribe('/topic/updates', function(update) {
				var update = JSON.parse(update.body)
				$log.info('received: ' + update)
				$log.info('received1: ' + update.enemies)
				
				$scope.enemyName1 = update.enemies[0];
				$scope.enemyName2 = update.enemies[1];
				$scope.enemyName3 = update.enemies[2];
				$scope.enemyName4 = update.enemies[3];
				$scope.enemyName5 = update.enemies[4];
				
				var bestMatch1 = hhBestMatch.get({name: $scope.enemyName1}, function(bestMatch1) {
					$log.info("Match1: " + bestMatch1.id);
					
					$scope.enemyId1 = bestMatch1.id;
					$scope.enemyMmr1 = bestMatch1.mmr;
					$scope.enemyRegion1 = bestMatch1.region;
					$scope.enemyGames1 = bestMatch1.numberOfGames;
					$scope.enemyMatches1 = bestMatch1.numberOfMatches;
					
					if (!(angular.isUndefined($scope.enemyId1) || $scope.enemyId1 === null)) {
						hhHistory.get({ id: $scope.enemyId1}, function(history) {
							$log.info(history.rows);
							$scope.matches1 = history.rows;
						});
					}
				});
				
				var bestMatch2 = hhBestMatch.get({name: $scope.enemyName2}, function(bestMatch2) {
					$log.info("Match2: " + bestMatch2.id);
					
					$scope.enemyId2 = bestMatch2.id;
					$scope.enemyMmr2 = bestMatch2.mmr;
					$scope.enemyRegion2 = bestMatch2.region;
					$scope.enemyGames2 = bestMatch2.numberOfGames;
					$scope.enemyMatches2 = bestMatch2.numberOfMatches;
					
					if (!(angular.isUndefined($scope.enemyId2) || $scope.enemyId2 === null)) {
						hhHistory.get({ id: $scope.enemyId2}, function(history) {
							$log.info(history.rows);
							$scope.matches2 = history.rows;
						});
					}
				});
				
				var bestMatch3 = hhBestMatch.get({name: $scope.enemyName3}, function(bestMatch3) {
					$log.info("Match3: " + bestMatch3.id);
					
					$scope.enemyId3 = bestMatch3.id;
					$scope.enemyMmr3 = bestMatch3.mmr;
					$scope.enemyRegion3 = bestMatch3.region;
					$scope.enemyGames3 = bestMatch3.numberOfGames;
					$scope.enemyMatches3 = bestMatch3.numberOfMatches;
					
					if (!(angular.isUndefined($scope.enemyId3) || $scope.enemyId3 === null)) {
						hhHistory.get({ id: $scope.enemyId3}, function(history) {
							$log.info(history.rows);
							$scope.matches3 = history.rows;
						});
					}
				});
				
				var bestMatch4 = hhBestMatch.get({name: $scope.enemyName4}, function(bestMatch4) {
					$log.info("Match4: " + bestMatch4.id);
					
					$scope.enemyId4 = bestMatch4.id;
					$scope.enemyMmr4 = bestMatch4.mmr;
					$scope.enemyRegion4 = bestMatch4.region;
					$scope.enemyGames4 = bestMatch4.numberOfGames;
					$scope.enemyMatches4 = bestMatch4.numberOfMatches;
					
					if (!(angular.isUndefined($scope.enemyId4) || $scope.enemyId4 === null)) {
						hhHistory.get({ id: $scope.enemyId4}, function(history) {
							$log.info(history.rows);
							$scope.matches4 = history.rows;
						});
					}
				});
				
				var bestMatch5 = hhBestMatch.get({name: $scope.enemyName5}, function(bestMatch5) {
					$log.info("Match5: " + bestMatch5.id);
					
					$scope.enemyId5 = bestMatch5.id;
					$scope.enemyMmr5 = bestMatch5.mmr;
					$scope.enemyRegion5 = bestMatch5.region;
					$scope.enemyGames5 = bestMatch5.numberOfGames;
					$scope.enemyMatches5 = bestMatch5.numberOfMatches;
					
					if (!(angular.isUndefined($scope.enemyId5) || $scope.enemyId5 === null)) {
						hhHistory.get({ id: $scope.enemyId5}, function(history) {
							$log.info(history.rows);
							$scope.matches5 = history.rows;
						});
					}
				});
				
				$log.info('UPDATED!: ' + $scope.enemy1)
				
				
				
				$log.info('UPDATED2: ' + $scope.enemy2)
			});
		});
	}

	$scope.disconnect = function() {
		
		var history = hhHistory.get({ id: 4387231}, function(history) {
			$log.info(history.rows);
			$scope.matches1 = history.rows;
		});
//		stompClient.disconnect();
//		$scope.stompConnected = false;
//		$log.info("Disconnected");
	}

});