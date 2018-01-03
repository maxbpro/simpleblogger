angular.module('spBlogger.controllers',[]);

angular.module('spBlogger.controllers')
    .controller('MainController',['$scope','authService','$localStorage',
        function($scope,authService, $localStorage){

            var main = this;
            main.currentUser = $localStorage.currentUser;;

            $scope.$on('onCurrentUser', function (ctx, id) {
                main.currentUser = id;
                console.log(id);
                console.log(main.currentUser);
            });

            main.logout = function() {
                main.currentUser = null;
                authService.logout();
            };
    }]);