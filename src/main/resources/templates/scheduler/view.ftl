<#import "../common.ftl" as c/>
<#-- @ftlvariable name="schInfo" type="ru.randscheduler.web.controllers.scheduler.SchedulerPageCtrl.SchedulerViewInfo" -->

<#macro css>
<link rel="stylesheet" href="/static/css/site/scheduler/scheduler-page.css"/>
<link rel="stylesheet" href="/static/css/site/scheduler/view.css"/>
</#macro>

<@c.page "${schInfo.name}" css js "ng-app='scheduler-view' ng-controller='main'">

<h4>${schInfo.name}
    <#if (schInfo.futureActionCount > 0)>
        <span class="scheduler-status scheduler-status_active"></span>
    <#else>
        <span class="scheduler-status scheduler-status_not-active"></span>
    </#if>
</h4>

<section class="sch-info">
    <span class="title">Осталось действий: ${schInfo.futureActionCount}</span>
</section>

<section class="calendar" style="padding-top: 20px">
    <div class="calendar__days-wrap">
        <div class="calendar__day" ng-repeat="(key,val) in vm.days" ng-class="[val.class, {'calendar__day_with-action' : val.actions.length}]">
            <div class="calendar__date">
                <span>{{key | date : 'd.MM EEE'}}</span>
            </div>
            <ul class="calendar__action-list">
                <li ng-repeat="action in val.actions">
                    {{action}}
                </li>
            </ul>
        </div>
    </div>
</section>

</@c.page>

<#macro js>
<script>
    var data = {
        schedulerViewData: ${jsonMapper.writeValueAsString(schViewData)}
    }
</script>

<script type="text/javascript" src="/static/js/site/scheduler/calendar.js"></script>
<script type="text/javascript" src="/static/js/site/scheduler/scheduler-view.js"></script>
</#macro>