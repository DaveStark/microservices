(function() {
    'use strict';

    angular
        .module('webgateway')
        .config(localStorageConfig);

    localStorageConfig.$inject = ['$localStorageProvider', '$sessionStorageProvider'];

    function localStorageConfig($localStorageProvider, $sessionStorageProvider) {
        $localStorageProvider.setKeyPrefix('wgt-');
        $sessionStorageProvider.setKeyPrefix('wgt-');
    }
})();
