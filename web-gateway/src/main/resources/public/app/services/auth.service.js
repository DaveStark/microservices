(function() {
    'use strict';
    angular
        .module('webgateway')
        .factory('AuthService', AuthService);

    AuthService.$inject = ['$http','$localStorage', '$sessionStorage'];// injects to service the resource

    function AuthService ($http, $localStorage, $sessionStorage) {
    	var service = {
    		login: login,
    		logout: logout,
    		storeAuthenticationToken: storeAuthenticationToken,
    		loginWithToken: loginWithToken,
    		isLoggedIn : isLoggedIn
    	};
    	
    	return service;

        function login(credentials){
        	var data = {
        		scope: 'ui',
        		username : credentials.user,
        		password: credentials.password,
        		grant_type: 'password'
        	};
        	
        	return $http({
        		url: 'uaa/oauth/token',
        		method: 'post',
        		data: data,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'Authorization': 'Basic YnJvd3Nlcjo='
                    //,'Authorization': 'Basic ' + btoa('clientPassword:secret'),
                },
                transformRequest: function(obj) {
                    var str = [];
                    for (var p in obj) {
                        str.push(encodeURIComponent(p) + '=' + encodeURIComponent(obj[p]));
                    }
                    return str.join('&');
                }
            }).then(function (data) {
                var accessToken = data.data["access_token"];
                if (angular.isDefined(accessToken)) {
                    service.storeAuthenticationToken(accessToken, credentials.rememberMe);
                }
            });
        }
        
        function logout () {
            return $http.post('api/logout');            
        }
        
        function loginWithToken(accessToken, rememberMe) {
            var deferred = $q.defer();

            if (angular.isDefined(accessToken)) {
                this.storeAuthenticationToken(accessToken, rememberMe);
                deferred.resolve(accessToken);
            } else {
                deferred.reject();
            }

            return deferred.promise;
        }

        function isLoggedIn(){
            return false;//TODO
        }
        
        function storeAuthenticationToken(accessToken, rememberMe) {
            if(rememberMe){
                $localStorage.authenticationToken = accessToken;
            } else {
                $sessionStorage.authenticationToken = accessToken;
            }
        }

        function logout () {
            delete $localStorage.authenticationToken;
            delete $sessionStorage.authenticationToken;
        }
        
    }
})();