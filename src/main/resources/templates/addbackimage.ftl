<#import "parts/common.ftl" as c>

<@c.page>
<div>Добро пожаловать </div>
<a href="/main">Головна сторінка</a>

<div class="card my-3 " >
    <div class="p-3 mb-2 bg-info text-white">
        <img src="/img/project_lions.jpg" height="250" width="250">3
        .bg-info</div>
    <div class="card-footer text-muted">
        ${message.authorName}
    </div>
</div>
</@c.page>