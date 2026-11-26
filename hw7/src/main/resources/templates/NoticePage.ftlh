<#import "commons.ftlh" as c>

<@c.page>
    <div class="form-box">
        <div class="header">Add notice</div>
        <div class="body">
            <form method="post" action="">
                <div class="field">
                    <div class="name">
                        <label for="content">Content</label>
                    </div>
                    <div class="notice-content">
                        <textarea id="content" name="content">${noticeForm.content!}</textarea>
                    </div>
                    <@c.error "noticeForm.content"/>
                </div>
                <div class="button-field">
                    <input type="submit" value="Add">
                </div>
            </form>
        </div>
    </div>
</@c.page>
