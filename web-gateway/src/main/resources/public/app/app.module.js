(function() {
	'use strict';

    angular
        .module('webgateway', [
            'ngResource',
            'ui.bootstrap',
            'ui.router',
            'ngStorage',
            // jhipster-needle-angularjs-add-module JHipster will add new module here
            'angular-loading-bar'
        ])
        .run(run);

    run.$inject = ['stateHandler'];

    function run() {
        stateHandler.initialize();
    }
})();