var app = angular.module('hh_app', []).controller("hh_view", function($scope) {

	$scope.headers = {
		values : [ "Map", "Hero", "Won" ]
	}
	
	$scope.add_button = "Add Person";

});

