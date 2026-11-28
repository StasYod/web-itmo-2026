<#import "commons.ftlh" as c>

<@c.page>
    <@c.post post = post/>
    <div class="comments">
        <div class="title">Comments (${post.comments?size})</div>
        <#if user??>
            <div class="addComment">
                <form method="post" action="">
                    <textarea name="text" placeholder="Write a comment"></textarea>
                    <button type="submit">Add comment</button>
                </form>
            </div>
        </#if>
        <#list post.comments as comment>
            <div class="comment">
                <div class="username">${comment.user.login}</div>
                <div class="content">${comment.text}</div>
                <div class="time">${comment.creationTime}</div>
            </div>
        </#list>
    </div>
</@c.page>