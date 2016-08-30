(function() {
	'use strict';

    angular
        .module('simpleApp', [
            'ngResource',
            'ui.bootstrap',
            'ui.router',
            // jhipster-needle-angularjs-add-module JHipster will add new module here
            'angular-loading-bar'
        ])
        .run(run);

    //run.$inject = ['stateHandler'];

    function run() {
        //stateHandler.initialize();
    }
})();