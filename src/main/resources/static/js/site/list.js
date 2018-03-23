(function () {

    var app = angular.module("list-page", []);

    app.controller("main", function ($scope, $http) {
        var vm = $scope.vm = {};
        vm.schedulers = window.data.schedulers;


        vm.schedulerClick = function (scheduler, target) {
            var $target = $(target);
            var cl = ".scheduler__control-panel";
            if (!$target.is(cl) && !$target.parent(cl).length) {
                location.href = "/scheduler/" + scheduler.id;
            }
        };

        vm.removeScheduler = function (id, index) {
            $http.post("/scheduler/remove?id=" + id).then(function () {
                vm.schedulers.splice(index, 1);
            })
        };

        vm.isValid = function (val) {
            return val !== undefined && val !== null;
        }
    })

})();
