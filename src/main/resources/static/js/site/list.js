(function () {

    var app = angular.module("list-page", []);

    app.controller("main", function ($scope, $http) {
        var vm = $scope.vm = {};
        vm.showNotActive = false;

        var allSchedulers = window.data.schedulers;

        vm.schedulers = allSchedulers.filter(function (d) {
            return d.futureActionCount > 0;
        });

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
                allSchedulers = allSchedulers.filter(function (e) {
                    return e.id !== id;
                })
            })
        };

        vm.isValid = function (val) {
            return val !== undefined && val !== null;
        };

        $scope.$watch("vm.showNotActive", function (newVal) {
            if (newVal) {
                vm.schedulers = allSchedulers;
            } else {
                vm.schedulers = allSchedulers.filter(function (d) {
                    return d.futureActionCount > 0;
                });
            }
        })


    })

})();
