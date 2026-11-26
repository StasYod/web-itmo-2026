<#import "commons.ftlh" as c>

<@c.page>
    <#if userProfile??>
    <div class="userProfile">
        <div class="caption">User profile</div>
        <div class="id"> Id: ${userProfile.id}</div>
        <div class="login"> Login: ${userProfile.login}</div>
        <div class="creationTime"> Creation time: ${userProfile.creationTime?string("yyyy-MM-dd HH:mm:ss")}</div>
    </div>
    <#else>
        <div class="user profile">
            <div class="caption">No such user</div>
        </div>
    </#if>
</@c.page>
