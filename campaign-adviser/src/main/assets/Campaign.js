var os = '';

$(document).ready(function () {
    var queries = {};
    $.each(document.location.search.substr(1).split('&'), function (c, q) {
        var i = q.split('=');
        queries[i[0].toString()] = i[1].toString();
    });

    document.getElementById("img_view").src = decodeURIComponent(queries["img"]);
    os = queries["os"];
    document.getElementById("title").innerHTML = decodeURIComponent(queries["title"]);
});

function testEcho(str) {
    console.log(str);
}

function exec(functionName, parameter) {
    if (os == 'iOS')
        window.webkit.messageHandlers.campaign.postMessage({
            'func': isNaN(parameter) ? functionName : functionName + ":",
            'param': parameter
        });
    else if (os == 'AOS')
        window.android[functionName](parameter);
}