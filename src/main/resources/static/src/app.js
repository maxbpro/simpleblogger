'use strict'

angular.module('spBlogger',[
    'ngStorage',
    'ngResource',
    'ngAnimate',
    'ngRoute',
    'spBlogger.login',
    'spBlogger.posts',
    'spBlogger.controllers',
    'spBlogger.services']);



angular.module('spBlogger').config(['$httpProvider', '$routeProvider', '$locationProvider',
            function($httpProvider, $routeProvider, $locationProvider){


    $httpProvider.defaults.withCredentials = true;

     //state('editPosts',{
    //     url:'/',
    //     controller: 'PostController',
    //     templateUrl:'src/posts/views/admin-all-posts.html'
    //

    $routeProvider
        .when('/', {
            templateUrl: 'src/posts/views/posts.html',
            controller: 'PostListController',
            controllerAs: 'postList',
            resolve:{
                user:['$localStorage','$q',function($localStorage,$q){
                    if($localStorage.currentUser.token){
                        return $localStorage.currentUser.user || $q.reject({unAuthorized:true});
                    }
                    return false;
                }]
            }
        })
        .when('/posts/new', {
            templateUrl:'src/posts/views/new-post.html',
            controller: 'PostCreationController',
            controllerAs: 'postCreation',
            resolve:{
                user:['$localStorage','$q',function($localStorage,$q){
                    if($localStorage.currentUser.token){
                        return $localStorage.currentUser.user || $q.reject({unAuthorized:true});
                    }
                    return false;
                }]
            }
        })
        .when('/posts/:id/edit', {
            templateUrl:'src/posts/views/admin-update-post.html',
            controller: 'PostUpdateController',
            controllerAs: 'postUpdate',
            resolve:{
                user:['$localStorage','$q',function($localStorage,$q){
                    if($localStorage.currentUser.token){
                        return $localStorage.currentUser.user || $q.reject({unAuthorized:true});
                    }
                    return false;
                }]
            }
        })
        .when('/posts/:id', {
            templateUrl: 'src/posts/views/singlePost.html',
            controller: 'PostDetailsController',
            controllerAs: 'postDetils',
            resolve: {
                user: ['$localStorage', '$q', function ($localStorage, $q) {
                    if ($localStorage.currentUser.token) {
                        return $localStorage.currentUser.user || $q.reject({unAuthorized: true});
                    }
                    return false;
                }]
            }
        })
        .when('/login', {
            templateUrl:'src/login/views/login.html',
            controller: 'SignInController',
            controllerAs: 'login'
        })
        .when('/contact', {
            templateUrl: 'src/login/views/contact.html',
            controller: 'ContactController',
            controllerAs: 'contact'
        })
        .when('/about', {
            templateUrl: 'src/login/views/about.html',
            controller: 'AboutController',
            controllerAs: 'about'
        })
        .when('/signup', {
            templateUrl: 'src/login/views/register.html',
            controller: 'SignUpController',
            controllerAs: 'signup'
        })
        .otherwise({redirectTo: '/'});

        $locationProvider.html5Mode(true);
}]);

angular.module('spBlogger').run(['$rootScope', '$http','$localStorage','$location',
        function( $rootScope, $http, $localStorage, $location){


    $rootScope.$on('$routeChangeStart', function (e, curr, prev) {
        //LoadingService.setLoading(true);
    });

    $rootScope.$on('$routeChangeSuccess', function (e, curr, prev) {
        //LoadingService.setLoading(false);
    });

    $rootScope.$on('$locationChangeStart', function(event, newUrl) {

        if(newUrl.endsWith("signup") || newUrl.endsWith("about") || newUrl.endsWith("contact")){
            return;
        }

        if($localStorage.currentUser && $localStorage.currentUser.token){
            //$location.path('/');
        }else{
            $location.path('/login');
        }

    });

    // keep user logged in after page refresh
    if ($localStorage.currentUser && $localStorage.currentUser.token) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;

        $rootScope.$broadcast('onCurrentUser', $localStorage.currentUser.user);
        $rootScope.$broadcast('onCurrentUserId', $localStorage.currentUser.user.id);
    }

}]);