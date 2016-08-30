(function() {
    'use strict';

    angular
        .module('simpleApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('app', {
            abstract: true,
            views: {
                'navbar@': {
                    templateUrl: 'app/navbar/navbar.html',
                    controller: 'NavbarController',
                    controllerAs: 'ctl'
                }
            },
            resolve: {
                /*authorize: ['Auth',
                    function (Auth) {
                        return Auth.authorize();
                    }
                ]*/
            }
        });
    }
})();
