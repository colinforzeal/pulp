angular.module('demo', ["ngRoute", "dndLists"])
    .controller("SimpleDemoController", ['$scope','$sce', function ($scope,$sce) {

        $scope.text ="";

        $scope.models = {
            selected: null,
            templates: [
                {type: "item", id: 7,"showme":false}
            ],
            dropzones: {
                "A": [
                    {
                        "type": "",
                        "id": "",
                        "hidden":"true",
                        "value":"",
                        "showme":false
                    },
                    {
                        "type": "item",
                        "id": "4",
                        "value":"",
                        "showme":false
                    }
                ]
            }
        };

        $scope.$watch('models.dropzones', function(model) {
            $scope.modelAsJson = angular.toJson(model, true);
        }, true);

        $scope.func =function () {
            if($scope.models.selected.showme===true){
                $scope.models.selected.showme=false;
            }
            else if($scope.models.selected.showme===false){
                $scope.models.selected.showme=true;
            }

        };
    }





]);

