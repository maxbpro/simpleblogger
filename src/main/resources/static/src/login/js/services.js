angular.module('spBlogger.login.services', [])

.factory('Post',['$resource','API_ENDPOINT',function($resource,API_ENDPOINT){
    return $resource(API_ENDPOINT, { id: '@id' }, {
        update: {
            method: 'PUT'
        }
    });

}]).service('popupService',['$window',function($window){
    this.showPopup=function(message){
        return $window.confirm(message); //Ask the users if they really want to delete the post entry
    }

}]).factory('authService',['AUTH_ENDPOINT','SIGN_UP_ENDPOINT', 'LOGOUT_ENDPOINT','$http', '$localStorage',
    '$rootScope', '$location',
    function(AUTH_ENDPOINT, SIGN_UP_ENDPOINT, LOGOUT_ENDPOINT,$http, $localStorage, $rootScope, $location){

    var auth={};

    auth.login=function(username,password){

        var params = $.param({username:username,password:password});
        return $http({
            method: 'POST',
            url: AUTH_ENDPOINT,
            data: params,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}

        }).then(function(response,status){

            auth.user = response.data.user;
            auth.token = response.data.token;

            // store username and token in local storage to keep user logged in between page refreshes
            $localStorage.currentUser = { user: auth.user, token: auth.token };

            // add jwt token to auth header for all requests made by the $http service
            $http.defaults.headers.common.Authorization = 'Bearer ' + auth.token;

            $rootScope.$broadcast('onCurrentUser', $localStorage.currentUser.user);

            return auth.user;
        });

    }

    auth.register = function(firstName, lastName, email, username, password){

        var data = {firstName:firstName, lastName:lastName,
                                email:email,username:username,password:password};
        return $http({
            method: 'POST',
            url: SIGN_UP_ENDPOINT,
            data: data

        }).then(function(response,status){

            auth.user = response.data.user;
            auth.token = response.data.token;

            // store username and token in local storage to keep user logged in between page refreshes
            $localStorage.currentUser = { user: auth.user, token: auth.token };

            // add jwt token to auth header for all requests made by the $http service
            $http.defaults.headers.common.Authorization = 'Bearer ' + auth.token;

            $rootScope.$broadcast('onCurrentUser', $localStorage.currentUser.user);

            return auth.user;
        });

    }

    auth.logout=function(){
        return $http.post(LOGOUT_ENDPOINT).then(function(response){
            auth.user = undefined;
            auth.token = undefined;

            delete $localStorage.currentUser;
            $http.defaults.headers.common.Authorization = '';
            $location.path('/login');
        });
    }

    return auth;

}]);



angular.module('spBlogger.login.services').value('API_ENDPOINT','http://localhost:8090/api/posts/:id');
angular.module('spBlogger.login.services').value('AUTH_ENDPOINT','http://localhost:8090/authenticate');
angular.module('spBlogger.login.services').value('SIGN_UP_ENDPOINT','http://localhost:8090/register');
angular.module('spBlogger.login.services').value('LOGOUT_ENDPOINT','http://localhost:8090/logout');