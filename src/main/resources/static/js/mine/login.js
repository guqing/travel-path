// 验证表单
function check(){
    var username =  $("#username").val();
    var password =  $("#password").val();
    if(username==='' || password===''){
        $.Toast("表单校验失败", "用户名或密码不能为空", "warning");
        return false;
    }
    // 验证表单数据是否符合条件，不符合返回false禁止提交
    return true;
}