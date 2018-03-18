function formatDate(timestamp) {
    var d = new Date(timestamp);
    var month = '' + (d.getMonth() + 1);
    var day = '' + d.getDate();
    var year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}

function formatDateTime(timestamp) {
    var d = new Date(timestamp);
    var month = '' + (d.getMonth() + 1);
    var day = '' + d.getDate();
    var year = ''+ d.getFullYear();
    var hours = '' + d.getHours();
    var minutes = '' + d.getMinutes();
    var seconds = '' + d.getSeconds();
    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;
    if (hours.length < 2) hours = '0' + hours;
    if (minutes.length < 2) minutes = '0' + minutes;
    if (seconds.length < 2) seconds = '0' + seconds;
    return [year, month, day].join('-') + ' ' + [hours, minutes, seconds].join(':');
}

function formatDateTimeExceptSecond(timestamp) {
    var d = new Date(timestamp);
    var month = '' + (d.getMonth() + 1);
    var day = '' + d.getDate();
    var year = ''+ d.getFullYear();
    var hours = '' + d.getHours();
    var minutes = '' + d.getMinutes();
    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;
    if (hours.length < 2) hours = '0' + hours;
    if (minutes.length < 2) minutes = '0' + minutes;
    return [year, month, day].join('-') + ' ' + [hours, minutes].join(':');
}

function subTwoDateTime(start, end) {
    var t1 = Date.parse(new Date(start)) /1000;
    var t2 = Date.parse(new Date(end)) / 1000;
    var diff = t2 - t1;
    var hours = diff / 3600 + '';
    var minutes = diff / 60 % 60 + '';
    if (hours.length < 2) hours = '0' + hours;
    if (minutes.length < 2) minutes = '0' + minutes;
    return [hours, minutes].join(':');
}

function getFormJson(frm) {  //frm：form表单的id
    var o = {};
    var a = $("#"+frm).serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}

