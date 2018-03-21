<#import "../common.ftl" as c/>

<#macro resources>
<link rel="stylesheet" href="/static/css/site/scheduler-page.css"/>
<link rel="stylesheet" href="/static/css/lib/selectize.default.min.css"/>
</#macro>

<@c.page "Добавить планировщик" resources "ng-app='scheduler-page' ng-controller='main'">
<h4>Добавить планировщик</h4>

<!-- вынести в отдельный ftl и подключить через include -->
<section class="filter">
    <span class="title">Фильтр</span>
    <form>
        <div class="form-group">
            <label for="scheduler-name">Название</label>
            <input id="scheduler-name" type="text" class="form-control" placeholder="Назовите планировщик" required>
        </div>

        <div class="form-group">
            <label for="scheduler-type">Тип планировщика:</label>
            <select id="scheduler-type"></select>
        </div>

        <div class="form-group">
            <label for="actions">Действия:</label>
            <input id="actions" type="text" required>
        </div>

        <button type="submit" class="btn btn-primary" disabled>Сформировать</button>
    </form>
</section>

<section class="calendar">
    <div class="days-wrap">
        <div class="day" ng-repeat="(key,val) in vm.days" ng-class="val.class">
            <span class="date">{{key | date : 'd.MM.yy'}}</span>
            <ul>
                <li ng-repeat="action in val.actions">
                    {{action}}
                </li>
            </ul>
        </div>
    </div>
    <button ng-show="vm.showSaveBtn" type="button" class="btn btn-primary" ng-click="vm.save()">Сохранить</button>
</section>

<script>
    var data = {
        scheduler_types: ${jsonMapper.writeValueAsString(scheduler_types)}
    }
</script>

<script type="text/javascript" src="/static/js/lib/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-animate.js"></script>
<script type="text/javascript" src="/static/js/lib/selectize.min.js"></script>
<script type="text/javascript" src="/static/js/lib/jquery-ui.min.js"></script>
<script type="text/javascript" src="/static/js/site/filter.js"></script>
<script type="text/javascript" src="/static/js/site/scheduler-page.js"></script>
</@c.page>