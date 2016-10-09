var app = angular.module('demo', ["ngRoute", "dndLists", 'youtube-embed', 'ngFileUpload', 'colorpicker.module', 'wysiwyg.module'])
    .controller("SimpleDemoController", ['$scope','Upload', '$http', '$window', function ($scope,Upload,$http,$window) {

        $scope.models = {
            selected: null,
            templates: [
                {type: "text"},
                {type: "video"},
                {type: "image"}
            ],
            dropzones: {
                "A": [
                    {
                        type: "",
                        hidden:true,
                        value:""
                    },
                    {
                        type: "text",
                        value:""
                    }
                ]
            }
        };

        $scope.$watch('models.dropzones.A', function(model) {
            $scope.modelAsJson = angular.toJson(model, true);
        }, true);


        // upload on file select or drop
        $scope.upload = function (file) {
            Upload.upload({
                url: 'upload',
                data: {file: file, 'username': $scope.username}
            }).then(function (resp) {
                console.log('Success ' + resp.config.data.file.name + 'uploaded. Response: ' + resp.data);
            }, function (resp) {
                console.log('Error status: ' + resp.status);
            }, function (evt) {
                var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
            });
        };
        
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
])
    app.controller("PageViewController", ['$scope', '$http', '$window', function ($scope,$http,$window){
        $scope.data = {};
    }
    ]);

// html filter (render text as html)
app.filter('html', ['$sce', function ($sce) {
    return function (text) {
        return $sce.trustAsHtml(text);
    };
}])

