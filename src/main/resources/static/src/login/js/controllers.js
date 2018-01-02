'use strict'

angular.module('spBlogger.login.controllers',[])

.controller('SignInController',['$scope','authService','$location',function($scope,authService,$location){

    $scope.buttonText="Login";

    $scope.signin=function(){

        $scope.buttonText="Logging in. . .";

        authService.login($scope.credentials.username,$scope.credentials.password).then(function(data){
            $location.path('/');
        },function(err){
            $scope.invalidLogin=true;
        }).finally(function(){
            $scope.buttonText="Login";
        });
    }

    $("input,textarea").not("[type=submit]").jqBootstrapValidation();

}]).controller('SignUpController',['$scope','authService','$location',function($scope,authService,$location){

    $scope.buttonText="Sign Up";

    $scope.register=function(){

        $scope.buttonText="Signing up. . .";

        authService.register($scope.credentials.firstname,
                          $scope.credentials.lastname,
                          $scope.credentials.email,
                            $scope.credentials.username,
                              $scope.credentials.password).then(function(data){

            $location.path('/');

        },function(err){
            $scope.invalidLogin=true;
        }).finally(function(){
            $scope.buttonText="Sign Up";
        });
    }

    $("input,textarea").not("[type=submit]").jqBootstrapValidation();

}]).controller('AboutController',[function(){


}]).controller('ContactController',[function(){

    $("input,textarea").not("[type=submit]").jqBootstrapValidation();
}]);



