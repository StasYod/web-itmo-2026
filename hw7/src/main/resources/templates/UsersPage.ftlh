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
                            <#if userIter.disabled>
                                <button type="button" onclick="setDisabled(${userIter.id}, false)">
                                    Enable
                                </button>
                            <#else>
                                <button type="button" onclick="setDisabled(${userIter.id}, true)">
                                    Disable
                                </button>
                            </#if>
                        </td>
                    </#if>
                </tr>
                </#list>
                <form id="disable-form" method="post" action="/users/disable">
                    <input type="hidden" id="user-id" name="userId">
                    <input type="hidden" id="disabled" name="disabled">
                </form>
                <script>
                    function setDisabled(userId, disabled) {
                        document.getElementById("user-id").value = userId;
                        document.getElementById("disabled").value = disabled;
                        document.getElementById("disable-form").submit();
                    }
                </script>
            </#if>
            </tbody>
        </table>
    </div>
</@c.page>
