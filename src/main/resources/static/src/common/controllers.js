'use strict'

angular.module('spBlogger.controllers',[]);

angular.module('spBlogger.controllers')
    .controller('MainController', MainCtrl);

function MainCtrl($scope, authService, $localStorage) {
    var main = this;
    main.currentUser = $localStorage.currentUser;;

    main.logout = function() {
        main.currentUser = null;
        authService.logout();
    };
};