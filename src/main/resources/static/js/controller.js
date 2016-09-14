var app = angular.module('hh_app', [ 'ngMaterial', 'ngResource' ]).controller("hh_view", function($scope) {

	$scope.stompConnected = false;
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
			$scope.stompConnected = true;
			$log.info('Connected: ' + frame);
			stompClient.subscribe('/topic/updates', function(update) {
				var update = JSON.parse(update.body)
				$log.info('received1: ' + update.enemies)
				
				for (var int = 0; int < update.enemies.length; int++) {
					
					var bestMatch = hhBestMatch.get({ name : update.enemies[int]
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
							});
						}
						
					});
					
					$scope.enemies[int] = bestMatch;
				}
			});
		});
	}

	$scope.disconnect = function() {

		var history = hhHistory.get({
			id : 4387231
		}, function(history) {
			$log.info(history.rows);
			$scope.matches1 = history.rows;
		});
		// stompClient.disconnect();
		// $scope.stompConnected = false;
		// $log.info("Disconnected");
	}

});