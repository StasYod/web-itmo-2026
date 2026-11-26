<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Created</th>
                <#if user?? && user.admin>
                    <th>Actions</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <#if users?size==0>
                <tr class="noData">
                    <td colspan="<#if user?? && user.admin>4<#else>3</#if>">
                        No data
                    </td>
                </tr>
            <#else>
                <#list users as userIter>
                <tr>
                    <td class="id">${userIter.id}</td>
                    <td class="login">${userIter.login}</td>
                    <td class="creationTime">${userIter.creationTime}</td>
                    <#if user?? && user.admin>
                        <td>
                            <form method="post" action="/users/disable">
                                <input type="hidden" name="userId" value="${userIter.id}">

                                <#if userIter.disabled>
                                    <input type="hidden" name="disabled" value="false">
                                    <input type="submit" value="Enable">
                                <#else>
                                    <input type="hidden" name="disabled" value="true">
                                    <input type="submit" value="Disable">
                                </#if>
                            </form>
                        </td>
                    </#if>
                </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
</@c.page>
