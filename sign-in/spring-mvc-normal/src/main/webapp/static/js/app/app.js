angular.module('demo', ["ngRoute", "dndLists", 'youtube-embed', 'ngFileUpload','colorpicker.module', 'wysiwyg.module'])
    .controller("SimpleDemoController", ['$scope','Upload', function ($scope,Upload) {

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
                        value:"",
                    },
                    {
                        type: "text",
                        value:"",
                    }
                ]
            }
        };

        $scope.$watch('models.dropzones', function(model) {
            $scope.modelAsJson = angular.toJson(model, true);
        }, true);

        $scope.func = function () {
            if($scope.models.selected.showme===true){
                $scope.models.selected.showme=false;
            }
            else if($scope.models.selected.showme===false){
                $scope.models.selected.showme=true;
            }
        };

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
    }
]);

