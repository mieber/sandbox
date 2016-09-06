app.controller('hh_controller', function($scope, $log) {
    $scope.persons = [
Person('m', 'm'),
Person('m', 'm'),
Person('m', 'm')
    ];
    
    
    $scope.add = function() {
		var firstname = 'A';
		var lastname = 'b';
		
		var person = Person(firstname, lastname);
		$log.info("Adding: " + person);
		$scope.persons.push(person);
	}
    
    
    function Person(pFirstname, pLastname) {

		var firstname = pFirstname;
		var lastname = pLastname;

		return {
			getFirstname : function() {
				return firstname;
			},
			getLastname : function() {
				return lastname;
			},
			toString : function() {
				return "Person: " + firstname + " " + lastname;
			}
		}
	}
});