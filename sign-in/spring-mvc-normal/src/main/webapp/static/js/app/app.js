angular.module('demo', ["ngRoute", "dndLists", 'youtube-embed', 'ngFileUpload'])
    .controller("SimpleDemoController", ['$scope','Upload', '$http', '$window', function ($scope,Upload,$http,$window) {

        // ,'colorpicker.module', 'wysiwyg.module'

        $scope.text ="";

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
            var post = $http.post('/pages', $scope.modelAsJson);
            post.success(function(data) {
                console.log(data);
                $window.location.href = '/pages';
            });
        };
    }
])
    .controller("PageViewController", ['$scope', '$http', '$window', function ($scope,$http,$window){

    }
    ]);

