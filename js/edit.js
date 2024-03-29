$(document).ready(function () {
    loading();
    var playlistID =getUrlVars()["playlistID"];
    var playlistName = getPlaylistName(playlistID);
    console.log("playlistID: " + playlistID);
    var clips = getClipInPlayList(playlistID);
    var remote = getRemoteVideoSegments(["https://avhiou2y5d.execute-api.us-east-2.amazonaws.com/RemoteSite/publicsegments?apikey=mH0naThzgz3LRgli6PiEa8ktNznRmClw83de0vCc"]);
    var vids = getVideoSegments();
    Promise.all([playlistName, clips, remote, vids]).then(doneLoading).then(preparelibrarySlider).then(preparelibrary2Slider);
    //appendVideoClip("test.ogg", "", "");
    //appendVideoClip("test.ogg", "", "");
    // appendVideoClip("test.ogg", "", "");
    // appendVideoClip("test.ogg", "", "");
    // appendVideoClip("test.ogg", "", "");




    // $('body').on('click', 'div.add_video', function (e) {
    //     loading();
    //     console.log("adding...");
    //     var target = $(e.target.parentElement.parentElement.parentElement);
    //     console.log(target);
    //     appendVideoToPlaylist(playlistID, target.context.id).then(doneLoading);
    //     //preparelibrarySlider();
    // });

    $('body').on('click', 'div.remove_clip', function (e) {
        loading();
        console.log("removing...");
        var target = $(e.target.parentElement.parentElement.parentElement);
        console.log(target);
        //target.remove();
        removeVideoFromPlaylist(playlistID, target.context.id).then(doneLoading);
        //preparelibrarySlider();
    });

    $('button.play').click(function () {
        var playlistID =getUrlVars()["playlistID"];
        console.log("Go play...");

        window.location.href =  "play.html?playlistID=" + playlistID;

    });

    $('form.name').submit(function () {
        var name = $("input[name=playlistName]").val();
        console.log(name);
        loading();
        renamePlaylist(playlistID, name).then(doneLoading);
    });
});


function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

// adds a new video clip to the end of the slider
function addVideoClip(url, transcript, character, id, ifMarked) {
    if (!id.includes("-")) {
        var clip = $("<div class='item library' id=\'" + id + "\'>\n" +
            "                            <a>Video Failed to load</a>\n" +
            "                            <video controls class=\"video\">\n" +
            "                                <source name='source' src=\""+url+"\" type=\"video/ogg\">\n" +
            "                            </video>\n" +
            "                            <div class=\"controls\">\n" +
            "                                <div onclick='addRemoteToPlaylist(\"" + id + "\", \"" + character + "\", \"" + transcript+ "\", \"" + url + "\")' class=\"add_video\" style=\"top: 0\">\n" +
            "                                    <i class=\"material-icons\">\n" +
            "                                        add\n" +
            "                                    </i>\n" +
            "                                </div>\n" +
            "                            </div>\n" +        "                            <div name='character' style=\"bottom: 4%; position:absolute; left: 50%; transform: translate(-50%);\">Character: "+ character +"</div>\n" +
            "                            <div name='transcript' style=\"bottom: 0; position:absolute; left: 50%; transform: translate(-50%); width: 100%; overflow-wrap: break-word; font-size: 6.5px\">Transcript: "+ transcript +"</div>\n" +
            "                        </div>" +
            "                        <a> </a>");
    } else {
        var clip = $("<div class='item library' id=\'" + id + "\'>\n" +
            "                            <a>Video Failed to load</a>\n" +
            "                            <video controls class=\"video\">\n" +
            "                                <source src=\""+url+"\" type=\"video/ogg\">\n" +
            "                            </video>\n" +
            "                            <div class=\"controls\">\n" +
            "                                <div onclick='addLocalToPlaylist(" + id + ")' class=\"add_video\" style=\"top: 0\">\n" +
            "                                    <i class=\"material-icons\">\n" +
            "                                        add\n" +
            "                                    </i>\n" +
            "                                </div>\n" +
            "                            </div>\n" +        "                            <div style=\"bottom: 4%; position:absolute; left: 50%; transform: translate(-50%);\">Character: "+ character +"</div>\n" +
            "                            <div style=\"bottom: 0; position:absolute; left: 50%; transform: translate(-50%); width: 100%; overflow-wrap: break-word; font-size: 6.5px\">Transcript: "+ transcript +"</div>\n" +
            "                        </div>" +
            "                        <a> </a>");
    }

    $("#Library").append(clip);
}

function appendVideoClip(url, transcript, character, id) {
    var clip = $("<div class='item library2' id=\'" + id + "\'>\n" +
        "                            <a>Video Failed to load</a>\n" +
        "                            <video controls class=\"video2\">\n" +
        "                                <source src=\""+url+"\" type=\"video/ogg\">\n" +
        "                            </video>\n" +
        "                            <div class=\"controls\">\n" +
        "                                <div class=\"remove_clip\" style=\"top: 0\">\n" +
        "                                    <i class=\"material-icons\">\n" +
        "                                        close\n" +
        "                                    </i>\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                        </div>" +
        "                        <a> </a>");
    $("#Library2").append(clip);
}

function setPlaylistId(id) {
    playlistID = id;
}


function goToHome() {
    data = {};
    data["page"] = "home.html";
    js = JSON.stringify(data);

    getRedirect(js, "https://m8hr3y5zj4.execute-api.us-east-2.amazonaws.com/dev/redirect").then(function (x) {
        console.log(x);
    });

    //window.location = getRedirect(js, "https://m8hr3y5zj4.execute-api.us-east-2.amazonaws.com/dev/redirect");
}

function addRemoteToPlaylist(id, character, transcript, tpsURL) {
    loading();
    console.log("adding...");
    var playlistID = getUrlVars()["playlistID"];
    appendVideoToPlaylist(playlistID, id, character, transcript, tpsURL).then(doneLoading);
}

function addLocalToPlaylist(id) {
    loading();
    console.log("adding...");
    var playlistID = getUrlVars()["playlistID"];
    appendVideoToPlaylist(playlistID, id).then(doneLoading);
}