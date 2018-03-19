function Filter(opt) {
    var $actions = $("#actions");
    var $schedulerType = $("#scheduler-type");
    var $btn = $(".filter .btn");

    (function () {
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
                if (val) $btn.removeAttr("disabled");
                else $btn.attr("disabled", true);
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
    })();

    this.getData = function () {
        return {
            actions: $actions.val(),
            type: $schedulerType.val()
        };
    };
}