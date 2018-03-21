function Filter(opt) {
    var $schedulerName = $("#scheduler-name");
    var $actions = $("#actions");
    var $schedulerType = $("#scheduler-type");
    var $btn = $(".filter .btn");
    var requiredFields = {
        name: 1,
        actions: 1
    };

    (function () {
        $schedulerName.on("keyup", function () {
            changeBtnCondition($(this).val(), "name");
        });

        $actions.selectize({
            plugins: ['restore_on_backspace', 'remove_button'],
            persist: false,
            delimiter: ',',
            placeholder: "Напишите список действий",
            create: function (input) {
                return {
                    value: input,
                    text: input
                }
            },
            onChange: function (val) {
                changeBtnCondition(val, "actions");
            }
        });

        var shTypes = window.data.scheduler_types;
        var options = [];

        for (var k in shTypes) options.push({value: k, text: shTypes[k]})

        $schedulerType.selectize({
            options: options,
            items: [options[0].value],
            placeholder: "Выберите тип планировщика",
            create: false
        });

        addListeners();

        function addListeners() {
            $(".filter button").on("click", function () {
                if (!$(this).is(":disabled")) opt.onSubmit();
            })
        }

        function changeBtnCondition(val, name) {
            if (val) delete requiredFields[name];
            else requiredFields[name] = 1;

            if (Object.keys(requiredFields).length === 0) {
                $btn.removeAttr("disabled")
            } else {
                $btn.attr("disabled", true);
            }
        }
    })();

    this.getData = function () {
        return {
            actions: $actions.val(),
            type: $schedulerType.val(),
            schedulerName: $schedulerName.val()
        };
    };
}