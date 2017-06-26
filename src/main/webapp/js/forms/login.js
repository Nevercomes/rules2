var student_id='';

function login() {
    var studentId = '';
    var password = '';
    studentId = $('#studentId').val();
    password = $('#password').val();

    var signonString = {studentId: studentId, password: password};
    var signonJson = $.toJSON(signonString);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'account/login',
        dataType: 'json',
        method: 'POST',
        data: signonJson,
        success: function (data) {
            $('#login-li').hide();
            $('#personal-li').show();
            setAccount(data);
            getTestRecord(data.studentId);
            setStudentId(data.studentId);
            isRegisted();
            changeStatus();

        },
        error: function (xhr) {
            // 导致出错的原因较多，以后再研究
            alert('error:' + JSON.stringify(xhr));
        }
    }).done(function (data) {
        // 请求成功后要做的工作
        console.log('success');
    }).fail(function () {
        // 请求失败后要做的工作
        console.log('error');
    }).always(function () {
        // 不管成功或失败都要做的工作
        console.log('complete');
    });
}
function setStudentId(student_id) {
    this.student_id=student_id;
}
function getStudentId() {
    return student_id;
}