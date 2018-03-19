<#macro page title resourcesMacro mainAttr="">
<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="/static/css/lib/bootstrap.min.css"/>
    <link rel="stylesheet" href="/static/css/site/main.css"/>
    <script type="text/javascript" src="/static/js/lib/jquery.min.js"></script>
    <@resourcesMacro/>
</head>

<body>
<header class="container">
    <div class="flex-container">
        <span>Rand Scheduler</span>
        <div class="menu">
            <ul>
                <li><a href="/">Список</a></li>
                <li><a href="/scheduler/add">Добавить</a> </li>
                <li class="user-logo"><span>${userShortId}</span></li>
            </ul>
        </div>
    </div>
</header>

<main class="container" ${mainAttr}>
    <#nested>
</main>

<footer>
    <span>by dMJ</span>
</footer>

<script type="text/javascript" src="/static/js/lib/bootstrap.min.js"></script>
</body>
</html>
</#macro>