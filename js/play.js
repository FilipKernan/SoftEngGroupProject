var index = 0;
var list;
var playlistID;
$(document).ready(function () {
    console.log(playlistID);
    var playlistID = getUrlVars()["playlistID"];
    console.log("playlistID: " + playlistID);
    list = listClipsInPlaylist(playlistID);
    console.log($("#video"));


    function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
            vars[key] = value;
        });
        return vars;
    }
});

$("#video")[0].on("ended", function() {
    console.log('Video has ended!');
    index++;
    var video = document.getElementById('video');
    video.setAttribute("src", json.list[index].url);
    video.load();
});

function listClipsInPlaylist(id) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "https://ijhrhn9pr5.execute-api.us-east-2.amazonaws.com/dev/playlist/getSegments", true);
    var data = {};
    var data = {};
    data["plID"] = id;
    var js = JSON.stringify(data);
    console.log(js);
    xhr.send(js);
    console.log("sent");
    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("XHR:" + xhr.responseText);
            json = JSON.parse(xhr.responseText);
            console.log(json.list);
            var video = document.getElementById('video');
            video.setAttribute("src", json.list[index].url);
            video.load();
            return json.list;
        }
    };

}

function next() {
    console.log('Video has ended!');
    index++;
    var video = document.getElementById('video');
    video.setAttribute("src", json.list[index].url);
    video.load();
    video.play();
}

async function goToHome() {
    data = {};
    data["page"] = "home.html";
    js = JSON.stringify(data);

    window.location = await redirect(js, "url").then(function (value) {

    });
}