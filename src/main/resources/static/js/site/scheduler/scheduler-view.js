(function () {

    var app = angular.module("scheduler-view", []);

    app.controller("main", function ($scope) {
        var vm = $scope.vm = {};

        vm.days = new Calendar(data.schedulerViewData).create();
    });
})();