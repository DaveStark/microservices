(function() {
    'use strict';
    
    angular
        .module('webgateway')
		.config(function($stateProvider){
			$stateProvider.state("login", {
				parent:'app',
				url: '/login',
				views:{
					'content@': {
						templateUrl: 'app/login/login.html',
						controller: 'LoginController',
						controllerAs: 'ctl',
					}
				},
        		resolve:{
        			formValues: function(){
        				return {
        					user: null,
        					password: null,
        					rememberMe: false
        				};
        			}
        		}
        	});
        });
    
})();