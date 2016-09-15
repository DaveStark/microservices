(function() {
    'use strict';

    angular
        .module('webgateway')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$scope', '$state', '$stateParams', 'AuthService' , 'formValues'];

    function LoginController ($scope, $state, $stateParams,  AuthService, formValues) {
        var ctl = this;
        ctl.loginEntity = formValues;
        ctl.badCredentials = false;
        
         
//      function getToken () {
//      var token = $localStorage.authenticationToken;
//      return token;
//  }
//  
//  function hasValidToken () {
//      var token = this.getToken();
//      return !!token;
//  }
        
        ctl.login = function(){
        	console.log('login');
        	ctl.badCredentials = false;
        	$scope.entity = AuthService.login(ctl.loginEntity)
	        	.then(function (response) {
	        		$state.go("home", null, {reload:true});
	            }, function(response){
	            	if(response.status == 401)
	            		ctl.badCredentials = true;
	            	ctl.loginEntity.password = "";
	            });
        }
        
        ctl.logout = function(){
        	AuthService.logout()
        		.then(function (response) {
        			return response;
            });
        }
        
    }
})();