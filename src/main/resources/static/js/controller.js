var app = angular.module('hh_app', ['ngResource']).controller("hh_view", function($scope) {

	$scope.headers = {
		values : [ "Map", "Hero", "Won" ]
	}
	
	$scope.add_button = "Add Person";
	
	$scope.stompConnected = false;
	
	$scope.matches = [];

});

app.factory(
		"hhHistory",
		function($resource, $log) {
			return $resource("/hh/api/history/:id");
		});

app.controller('hh_controller', function($scope, $log, hhHistory) {

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
		$log.info("Adding: " + person);
		if ($scope.matches == null) {
			$scope.matches = [];
		}
		$scope.matches.push(person);
	}

	$scope.stompClient = null;

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
			});
		});
	}

	$scope.disconnect = function() {
		
		var history = hhHistory.get({ id: 4387231}, function(history) {
			$log.info(history.rows);
			$scope.matches = history.rows;
		});
//		stompClient.disconnect();
//		$scope.stompConnected = false;
//		$log.info("Disconnected");
	}

});