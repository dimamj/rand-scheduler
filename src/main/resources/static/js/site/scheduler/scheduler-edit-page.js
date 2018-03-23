(function () {

    var app = angular.module("scheduler-edit", ["ngAnimate"]);

    app.controller("main", function ($scope, $http) {
            var vm = $scope.vm = {};

            vm.showSaveBtn = false;
            var fromDate;
            var toDate;
            var actions;

            var filter = new Filter({
                onSubmit: function () {
                    vm.showSaveBtn = false;
                    $http.get("/scheduler/create", {params: filter.getData()})
                        .then(loadScheduler);
                    return false;
                }
            });

            vm.save = function () {
                var filterData = filter.getData();
                filterData.from = fromDate;
                filterData.to = toDate;
                filterData.actions = filterData.actions.split(",");

                $http.post("", {
                    filterData: filterData,
                    actions: actions
                }).then(function (resp) {
                    if (resp.status === 200) location.href = "/";
                });
            };

            function loadScheduler(resp) {
                var res = resp.data;
                var daysMap = new Calendar(res).create();

                fromDate = res.from;
                toDate = res.to;
                actions = res.actions;

                vm.days = {};

                var time = -100;
                for (var k in daysMap) {
                    timeout(k);

                    function timeout(k) {
                        setTimeout(function () {
                            vm.days[k] = daysMap[k];
                            if (k === toDate) {
                                setTimeout(function () {
                                    vm.showSaveBtn = true;
                                    $scope.$apply();
                                }, 1000);
                            }

                            $scope.$apply();
                        }, time = time + 100);
                    }
                }
            }
        }
    );

})();