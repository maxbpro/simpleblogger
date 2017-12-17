'use strict'

angular.module('spBlogger.admin',['spBlogger.admin.controllers',
    'spBlogger.admin.directives','spBlogger.admin.services','spBlogger.admin.filters',
    'ngStorage']);

angular.module('spBlogger.admin').config(['$stateProvider',function($stateProvider){
    $stateProvider.state('login',{
        url:'/login',
        controller: 'LoginController',
        resolve:{
            user:['authService','$q',function(authService,$q){
                if(authService.user){
                    return $q.reject({authorized:true});
                }
            }]
        },
        templateUrl:'modules/admin/views/login.html'
    }).state('admin',{
        url:'/admin',
        abstract:true,
        controller:'AdminController',
        resolve:{
            user:['authService','$q',function(authService,$q){
                return authService.user || $q.reject({unAuthorized:true});
            }]
        },
        templateUrl:'modules/admin/views/admin-home.html'
    }).state('admin.postNew',{
        url:'/posts/new',
        controller: 'PostCreationController',
        templateUrl:'modules/admin/views/admin-new-post.html'
    }).state('admin.postUpdate',{
        url:'/posts/:id/edit',
        controller: 'PostUpdateController',
        templateUrl:'modules/admin/views/admin-update-post.html'
    }).state('admin.postViewAll',{
        url:'',
        controller: 'PostListController',
        templateUrl:'modules/admin/views/admin-all-posts.html'
    });
}]).run(['$rootScope','$state', '$http','$cookieStore','$localStorage','authService',function($rootScope,$state,
                                                                                              $http, $cookieStore,
                                                                                     $localStorage,authService){

    $rootScope.$on('$stateChangeError', function(event, toState, toParams, fromState, fromParams, error) {

        if(error.unAuthorized) {
            $state.go('login');
        }
        else if(error.authorized){
            $state.go('admin.postViewAll');
        }
    });

    // keep user logged in after page refresh
    // if ($localStorage.currentUser) {
    //     $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
    // }

    authService.user = $cookieStore.get('user');
    authService.token = $cookieStore.get('token');

    // keep user logged in after page refresh
    if (authService.token) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + authService.token;
    }

}]);