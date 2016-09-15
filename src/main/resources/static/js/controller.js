var app = angular.module('hh_app', [ 'ngMaterial', 'ngMessages', 'ngResource' ]).controller("hh_view", function($scope) {

	
});

app.factory("hhHistory", function($resource, $log) {
	return $resource("/hh/api/history/:id");
});

app.factory("hhBestMatch", function($resource, $log) {
	return $resource("/hh/api/bestmatch/:name");
});

app.controller('hh_controller', function($scope, $log, hhHistory, hhBestMatch) {

	$scope.stompClient = null;

	$scope.enemies = [];

	$scope.connect = function() {
		$log.info("TEST");
		var socket = new SockJS('/screenupdate');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			$log.info('Connected: ' + frame);
			stompClient.subscribe('/topic/updates', function(update) {
				var update = JSON.parse(update.body)
				$log.info('received: ' + update.enemies)
				
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
			});
		});
	}

	$scope.disconnect = function() {

		 stompClient.disconnect();
		 $log.info("Disconnected");
	}
	
	$scope.update = function(index) {

		 $log.info("Update: " + index);
		 $log.info("Update: " + $scope.enemies[index].name);
		 
		 
		 var bestMatch = hhBestMatch.get({ name : $scope.enemies[index].name
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
			
			$scope.enemies[index] = bestMatch;
	}
	
	$scope.test = function() {
		
		$log.info("Test");
		
		var enemies = [];
		enemies.push("Barsig");
		enemies.push("Gurkchen");
		enemies.push("s3phster");
		enemies.push("Murat");
		enemies.push("HellBreath");
		
		for (var int = 0; int < enemies.length; int++) {
			
			var bestMatch = hhBestMatch.get({ name : enemies[int]
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
			
			$scope.enemies[int] = bestMatch;
		}
	}

});