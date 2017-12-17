'use strict'

angular.module('spBlogger.admin.services', [])

    .factory('Post',['$resource','API_ENDPOINT',function($resource,API_ENDPOINT){
    return $resource(API_ENDPOINT, { id: '@_id' }, {
        update: {
            method: 'PUT'
        }
    });

}]).service('popupService',['$window',function($window){
    this.showPopup=function(message){
        return $window.confirm(message); //Ask the users if they really want to delete the post entry
    }

}]).factory('authService',['AUTH_ENDPOINT','LOGOUT_ENDPOINT','$http','$cookieStore', '$localStorage',
    function(AUTH_ENDPOINT,LOGOUT_ENDPOINT,$http,$cookieStore, $localStorage){

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

            $cookieStore.put('user',auth.user);
            $cookieStore.put('token',auth.token);

            // store username and token in local storage to keep user logged in between page refreshes
            $localStorage.currentUser = { user: auth.user, token: auth.token };

            // add jwt token to auth header for all requests made by the $http service
            $http.defaults.headers.common.Authorization = 'Bearer ' + auth.token;




            // auth.user = response.data;
            // auth.token = response.token;
            // $cookieStore.put('user',auth.user);
            // $cookieStore.put('token',auth.token);
            return auth.user;
        });

    }

    auth.logout=function(){
        return $http.post(LOGOUT_ENDPOINT).then(function(response){
            auth.user = undefined;
            auth.token = undefined;
            $cookieStore.remove('user');
            $cookieStore.remove('token');

            delete $localStorage.currentUser;
            $http.defaults.headers.common.Authorization = '';
        });
    }

    return auth;

}]);



angular.module('spBlogger.admin.services').value('API_ENDPOINT','http://localhost:8090/api/posts/:id');
angular.module('spBlogger.admin.services').value('AUTH_ENDPOINT','http://localhost:8090/authenticate');
angular.module('spBlogger.admin.services').value('LOGOUT_ENDPOINT','http://localhost:8090/logout');