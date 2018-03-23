<#import "common.ftl" as c/>

<#macro css>
<link rel="stylesheet" href="/static/css/site/login.css"/>
</#macro>

<@c.page "Представьтесь" css js "" false>

<section class="login-section">

    <form method="post">
        <div class="login-field">
            <input type="text" class="form-control" id="userId" placeholder="Введите логин">
            <small class="form-text err"></small>
        </div>

        <div class="custom-control custom-checkbox">
            <input type="checkbox" class="custom-control-input" name="firstTime" id="firstTime">
            <label class="custom-control-label" for="firstTime">Первый раз тут ?</label>
        </div>

        <button type="submit" class="btn btn-light">Вперед</button>
    </form>
</section>

</@c.page>

<#macro js>
<script>
    $(function () {
        var $userIdField = $("#userId");
        var $errField = $userIdField.next();

        $("form").on("submit", function () {
            $errField.text("");

            var id = $userIdField.val();
            if (!id || id.length < 3) {
                $errField.text("Минимум 3 символа");
                return false;
            } else {
                $.post("/login", {
                    userId: id,
                    firstTime: $("#firstTime").is(":checked")
                }, function (res) {
                    if (res) {
                        location.href = "/";
                    } else {
                        $errField.text("Такой логин уже существует!");
                    }
                })
            }
            return false;
        });
    })
</script>
</#macro>