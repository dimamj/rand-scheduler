<#import "common.ftl" as c/>

<#macro css>
<link rel="stylesheet" href="/static/css/site/list.css"/>
</#macro>


<@c.page "Список планировщиков" css js "ng-app='list-page' ng-controller='main'">
<h4>Список планировщиков</h4>

<section class="list">
    <div class="scheduler" ng-repeat="s in vm.schedulers" ng-click="vm.schedulerClick(s,$event.target)">
        <span class="scheduler__id">{{'#' + ($index+1)}}</span>
        <span class="scheduler__name">{{s.filterData.schedulerName}}</span>
        <div class="scheduler__actions">
            <span ng-if="vm.isValid(s.todayAction)"><span style="font-weight: bold">Сегодня:</span> {{s.todayAction.action}}</span>
            <span ng-if="vm.isValid(s.nextAction)"><span style="font-weight: 500;color: #a3a3a3;">Следующее:</span> {{s.nextAction.action}} ({{s.nextAction.date | date : 'd.MM EEE'}})</span>
        </div>
        <div class="scheduler__info">
            <div title="Дата проведения">
                <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAMAAAC6V+0/AAAAmVBMVEU0mNs3mtw5mtw+nd1Cn95Jo99LpN9QpuBXquFaq+JereJgruNmseRps+RrtOVstOVtteVvtuVxt+Z0uOZ4uueHwuqIw+qMxOubzO2u1vGx1/G32vK52/PB3/TC4PTF4vXI4/XT6ffW6vja7Pja7Pnf7/ni8Prl8vrn8/vq9Pvt9vzx+Pzx+P3y+f30+f31+v34+/79/v/////45dwQAAAAqElEQVQYV5XQxw4CMQwE0KF31hRjeiemLRD//8eR7MIBCSExB4/yJCuSYTHXe1aW5o04usA09giYvPGKx7YU2uO0K+XodQGvcM5tcD/CXSImQMQsAYFbwPjwhxxTF4a+8CMZNohqLaIyEdWbRDmq2So1k/Dt/mz2AxPm5oC5wsztPvO/61+xJ9IailRFpDMQ+Xf9GxbnquO1ak9VJ0tVHAPOCp/3SLw9AYlkMC2QgfHwAAAAAElFTkSuQmCC">
                <span>{{s.filterData.from | date : 'd.MM'}} - {{s.filterData.to | date : 'd.MM'}}</span>
            </div>
            <div title="Общее кол-во действий">
                <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAMAAAC6V+0/AAAAeFBMVEU0mNtEoN5God5Sp+BVqeFhr+Nir+NksONlseRps+RrtOVstOVtteVvtuVwtuZzuOaEwOmEwemfzu6j0O+q1PCs1fCv1/Gw1/G73PO83fPN5vbO5vbQ5/fS6PfT6ffW6vjp9Pvq9Pv1+v32+/37/f78/f79/v////9HOYFmAAAAnUlEQVQY023RxxaCUAyE4d+GlYvm2lFskHn/N3QjHIpZ5XyryQTV496sNBaC99E3sPYWFmaWAMzM7P7DSGusg2kY4kSattDnAGfpAjBzCXkKwPv1+gCwceEZACsd9loDELzGq2658hrlAaCsns+qBEhdSL6AnU5w1BYSbyItbQwjW/bD/72oh48Y4wJgHmMs2tUFSH1QcpYNSu684wuqBiL6iwyGBgAAAABJRU5ErkJggg==">
                <span>{{s.totalActions}}</span>
            </div>
            <div title="Осталось действий">
                <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAMAAAC6V+0/AAAAeFBMVEUnrmA4tGw6tW5HunhLu3pWwINYwIRawYVcwodgw4phxItixIxkxY1mxo5nxo9qx5F8zp59zp+Z2bSd2rek3byn3r6q38Gr4MG25Mm45MvK69jL7NnN7NrP7dvQ7t3T7t/n9u7p9+/1+/j2/Pj7/fz8/v39/v7////UVqI0AAAAnklEQVQY023RxxaCUAyE4d8CVi6aay+Ihcz7v6Eb4VDMKudbTSaoHvdmpbEQvI++hlXVwsLMUoDEzO4/jLTGOpiFIU6kaQt9BnCSzgBJJSHPAHg9n28A1i48B2Cp/U4rAILXeNHtqkuN8gDwqcqy+gBkLiSfw1ZHOGgDqTeRFjaGkS364f9e1MNHjHEOMIsxFu3qAmQ+KDnPByV33vEFptUi95HJ3qMAAAAASUVORK5CYII=">
                <span>{{s.futureActionCount}}</span>
            </div>
        </div>
        <div class="scheduler__control-panel">
            <img ng-click="vm.removeScheduler(s.id, $index)"
                 src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAMAAAC6V+0/AAAAh1BMVEXnTDznTT7pWkvpXE7qYlTqY1XrZ1nugnfug3jvhHnvhnvviH3viX/vioDwjYPwkYfxk4nxlIvxl47ym5L0sKn1sar1s6z1tK32urP2urT2u7X2vrj2v7n3wbv3xcD4yMP74uD75OL85uP98fD+9/f++Pf++fj++vr++/r//f3//v3//v7///8wxxZbAAAAnklEQVQYV13OyVICURAF0cQGWsABFHAWmeW9/P/vY9FVGnA3lXFWhbpq3o3V5/5a0Q9gHjaB3o/4Bal1AtDb8Eu3eRoMqMPQRQnjCXf96CZuW/Bf08QrbYvipbbFQLdpNycT8xdgmVjv/qxTrgyWivX+wuBFfIgebwdRnxyjRsV9aIMzAG6Lpn5jfUwLfRWtU8ad6X7IWzx/CFLrUT0DWY4cqUcSKEEAAAAASUVORK5CYII=">
        </div>
    </div>
</section>

</@c.page>

<#macro js>
<script>
    var data = {
        schedulers: ${jsonMapper.writeValueAsString(schedulers)}
    };
</script>
<script type="text/javascript" src="/static/js/site/list.js"></script>
</#macro>