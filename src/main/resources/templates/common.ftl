<#macro page title cssMacro jsMacro mainAttr="" enableNgCloak=true>
<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="/static/css/lib/bootstrap.min.css"/>
    <link rel="stylesheet" href="/static/css/site/main.css"/>

    <script type="text/javascript" src="/static/js/lib/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/lib/angular.min.js"></script>
    <script src="/static/js/lib/angular-locale_ru-ru.min.js"></script>
    <@cssMacro/>
</head>

<body>
<header class="header container">
    <div class="header__flex-container">
        <span>Rand Scheduler</span>
        <#if userShortId??>
            <div class="header__menu">
                <ul>
                    <li><a href="/">Список</a></li>
                    <li><a href="/scheduler/add">Добавить</a></li>
                    <li class="header__user-logo"><span>${userShortId}</span></li>
                    <li><img class="header__exit"
                             src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAMAAAC6V+0/AAAATlBMVEUAAABsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGxsbGyaGaPCAAAAGXRSTlMAAQIEBgcOFBUeJypARlZ7hYyRlLLFx+Lx8nYgtQAAAGxJREFUGFeN0MkOgCAMRdGnojjghGP//0dF0FiQGO+KnkXTAPKCi2hTuXCVDxLNlXsLjoaLCJL6wjYJEQJ64pgq20IjQ0F3fQR3GewE9GkeZhqDjJyU/T7ew6sLm9VOnfs8gLNCmOE3Gq75dABMgRHvtDsnzQAAAABJRU5ErkJggg==">
                    </li>
                </ul>
            </div>
        </#if>
    </div>
</header>

<main class="container" ${mainAttr}>
    <div <#if enableNgCloak>ng-cloak</#if>>
        <#nested>
    </div>
</main>

<footer>
    <span>by dMJ</span>
</footer>

<script>
    $(function () {
        $(".header__exit").on("click", function () {
            $.post("/login?exit=1", {}, function (res) {
                if (res === "ok") location.href = "/login";
            });
        })
    })
</script>
    <@jsMacro/>
<script type="text/javascript" src="/static/js/lib/bootstrap.min.js"></script>
</body>
</html>
</#macro>