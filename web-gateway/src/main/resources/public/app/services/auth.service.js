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
    		isLoggedIn : isLoggedIn,
    		getToken : getToken
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
                    'Authorization': 'Basic YnJvd3Nlcjo=' // 'browser:' <- that means in authorization header 'user:password' in this case only will send the user to use it in '.withClient("browser")'
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
            if(getToken())
                return true;
            else
                return false;

        }

        function getToken () {
            return $localStorage.authenticationToken || $sessionStorage.authenticationToken;
        }

        
        function storeAuthenticationToken(accessToken, rememberMe) {
            if(rememberMe){
                $localStorage.authenticationToken = accessToken;
            } else {
                $sessionStorage.authenticationToken = accessToken;
            }
        }

        function logout () {
            removeOauthTokenFromStorage();
        }

        function getCurrentAccount(){//I think, i dont need this method
            var token = getToken();
            var account = null;
            if(token){
                return $http({
                    url: 'accounts/current',//TODO: Change this url
                    method: 'get',
                    headers: {
                        'Authorization': 'Bearer ' + token
                        //,'Authorization': 'Basic ' + btoa('clientPassword:secret'),
                    }
                }).then(function (data) {
                    account = data;
                },function(response){
                    removeOauthTokenFromStorage();
                });
            }
            return account;
        }

        function removeOauthTokenFromStorage(){
            delete $localStorage.authenticationToken;
            delete $sessionStorage.authenticationToken;
        }
        
    }
})();