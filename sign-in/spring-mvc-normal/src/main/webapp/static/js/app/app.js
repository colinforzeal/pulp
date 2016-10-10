var app = angular.module('editor', ["ngRoute", "dndLists", 'youtube-embed', 'colorpicker.module', 'wysiwyg.module'])
    .controller("PageCreateController", ['$scope', '$http', '$window', function ($scope,$http,$window) {

        $scope.models = {
            selected: null,
            templates: [
                {type: "text"},
                {type: "video"}
            ],
            layout: [
                    {
                        type: "text",
                        value:""
                    }
            ]
        };

        $scope.$watch('models.layout', function(model) {
            $scope.modelAsJson = angular.toJson(model, true);
        }, true);
        
        $scope.save = function () {
            var pathArray = $window.location.pathname.split('/');
            var siteName = pathArray[2];
            console.log(siteName);
            var pageName = pathArray[4];
            console.log(pageName);

            var post = $http.post('/sites/'+siteName+'/pages/'+pageName+'/create', $scope.modelAsJson);
            post.success(function(data) {
                console.log(data);
                $window.location.href = '/sites/'+siteName+'/pages/'+pageName;
            });
        };
    }
]);
    app.controller("PageViewController", ['$scope', '$http', '$window', function ($scope){
        $scope.data = {};
    }
    ]);

// html filter (render text as html)
app.filter('html', ['$sce', function ($sce) {
    return function (text) {
        return $sce.trustAsHtml(text);
    };
}]);

