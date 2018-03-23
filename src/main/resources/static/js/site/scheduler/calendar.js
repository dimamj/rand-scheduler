function Calendar(data) {


    this.create = function () {
        var daysMap = {};

        var fromDateJs = new Date(data.from);
        var currentDate = new Date();
        currentDate.setHours(0, 0, 0, 0);

        var currentDateStr = currentDate.toDateString();

        data.dates.forEach(function (d) {
            var day = daysMap[d] = {actions: []};
            var date = new Date(d);

            if (date < fromDateJs || date < currentDate) {
                day.class = "old-date";
            } else if (date.toDateString() === currentDateStr) {
                day.class = "current-date";
            }
        });

        data.actions.forEach(function (a) {
            daysMap[a.date].actions.push(a.action);
        });

        return daysMap;
    }

}