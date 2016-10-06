angular.module('demo', ["ngRoute", "dndLists"])
    .controller("SimpleDemoController", ['$scope','$sce', function ($scope,$sce) {

    //
    // $scope.models = {
    //     selected: null,
    //     templates: [
    //         {type: "Text"},
    //         {type:"Image"},
    //         {type:"Youtube"}
    //
    //     ],
    //     lists: {"A": [], "B": [
    //         {
    //             type:"Text",
    //             text:$sce.trustAsHtml("<textarea></textarea>"),
    //             value:""
    //
    //         },
    //         {
    //             type:"Image",
    //             text:"",
    //             value:""
    //         },
    //         {
    //             type:"Youtube",
    //             text:"",
    //             value:""
    //         }
    //     ]}
    // };
    //
    // // Model to JSON for demo purpose
    // $scope.$watch('models', function(model) {
    //     $scope.modelAsJson = angular.toJson(model, true);
    // }, true);
    //
    //

        $scope.models = {
            selected: null,
            templates: [
                {type: "item", id: 7, "textarea":x($sce.trustAsHtml("<textarea ng-model='item.value'></textarea>"))}
            ],
            dropzones: {
                "A": [
                    {
                        "type": "",
                        "id": "",
                        "hidden":"true",
                        "value":""
                    },
                    {
                        "type": "item",
                        "id": "4"

                    },
                    {
                        "type": "item",
                        "id": "5"

                    },
                    {
                        "type": "item",
                        "id": "6"

                    }
                ]
            }
        };

        $scope.$watch('models.dropzones', function(model) {
            $scope.modelAsJson = angular.toJson(model, true);
        }, true);



}]);

