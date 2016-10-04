
var app = angular.module("demo",["dndLists"])
app.controller("SimpleDemoController", function($scope) {


    $scope.lists = [
        {
            label: "People",
            people: [
                {name: "Mallory"},
                {name:"lix"}
            ]
        }];

     
};