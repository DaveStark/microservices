(function() {
    'use strict';

    angular
        .module('webgateway')
        .controller('NavbarController', NavbarController);
    
    NavbarController.$inject = ['$state', 'AuthService'];

    function NavbarController ($state, AuthService) {
        var ctl = this;
        ctl.logout = logout;

        function logout() {
//            collapseNavbar();
            AuthService.logout();
            $state.go('login');
        }
    }
//        ctl.isNavbarCollapsed = true;
//        ctl.isAuthenticated = Principal.isAuthenticated;
//
//        ProfileService.getProfileInfo().then(function(response) {
//            ctl.inProduction = response.inProduction;
//            ctl.swaggerEnabled = response.swaggerEnabled;
//        });
//
//        ctl.login = login;
//        ctl.toggleNavbar = toggleNavbar;
//        ctl.collapseNavbar = collapseNavbar;
//        ctl.$state = $state;
//
//        function login() {
//            collapseNavbar();
//            LoginService.open();
//        }
//
//
//
//        function toggleNavbar() {
//            ctl.isNavbarCollapsed = !ctl.isNavbarCollapsed;
//        }
//
//        function collapseNavbar() {
//            ctl.isNavbarCollapsed = true;
//        }
//    }
})();
