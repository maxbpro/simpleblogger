angular.module('spBlogger.posts.controllers',[]).controller('PostController',['$scope','Post',function($scope,Post) {

    $scope.posts = Post.query();

}]).controller('PostCreationController',['$scope','$location','Post',function($scope,$location,Post){

        $scope.post = new Post();

        $scope.savePost=function(){
            $scope.buttonText="Saving. . .";
            $scope.post.$save(function(){
                $location.path('/');
            });
        }

    }]).controller('PostUpdateController',['$scope','Post','$location',function($scope,Post,$location){

        //$scope.post=Post.get({id:$stateParams.id});

        $scope.buttonText="Update";

        $scope.updatePost=function(){
            $scope.buttonText="Updating. . .";
            $scope.post.$update(function(){
                //$state.go('admin.postUpdate',{id:$stateParams.id},{reload:true});
                $location.path('/');
            });
        }

    }]).controller('PostListController',['$scope','Post','popupService','$location',function($scope,Post,popupService,$location){

        $scope.posts=Post.query();



        // $scope.singlePost=function(post){
        //     $location.path('/posts/' + post.id);
        // }


        // $scope.deletePost=function(post){
        //     if (popupService.showPopup('Really delete this?')) {
        //         post.$delete(function() {
        //             $location.path('/');
        //         });
        //     }
        // }

    }]).controller('LoginController',['$scope','authService','$location',function($scope,authService,$location){

        $scope.buttonText="Login";

        $scope.login=function(){

            $scope.buttonText="Logging in. . .";

            authService.login($scope.credentials.username,$scope.credentials.password).then(function(data){
                $location.path('/');
            },function(err){
                $scope.invalidLogin=true;
            }).finally(function(){
                $scope.buttonText="Login";
            });
        }

}]).controller('PostDetailsController',['$location','$scope','Post', '$routeParams',
                                function($location,$scope,Post, $routeParams){

    $scope.sendComment=function(post){

        var savedPostInstance={};
        angular.copy($scope.comment,savedPostInstance);

        post.comments.unshift(savedPostInstance);


        post.$update();

        $scope.comment={};
    };

    $scope.singlePost=Post.get({id:$routeParams.id});




}]);