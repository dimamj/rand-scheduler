(function () {

    var app = angular.module("list-page", []);

    app.controller("main", function ($scope, $http) {
        var vm = $scope.vm = {};
        vm.schedulers = window.data.schedulers;


        vm.schedulerClick = function (scheduler, target) {
            var $target = $(target);
            if (!$target.is(".control_panel") && !$target.parent(".control_panel").length) {
                console.log(scheduler, target);
            }
        };

        vm.removeScheduler = function (id, index) {
            $http.post("/scheduler/remove?id=" + id).then(function () {
                vm.schedulers.splice(index, 1);
            })
        }
    })

})();
