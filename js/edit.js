$(document).ready(function () {
    var playlistID =getUrlVars()["playlistID"];
    console.log("playlistID: " + playlistID);
    getVideoSegments();
    getClipInPlayList(playlistID);

    //appendVideoClip("test.ogg", "", "");
    //appendVideoClip("test.ogg", "", "");
    // appendVideoClip("test.ogg", "", "");
    // appendVideoClip("test.ogg", "", "");
    // appendVideoClip("test.ogg", "", "");


    function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
            vars[key] = value;
        });
        return vars;
    }

    $('body').on('click', 'div.add_video', function (e) {
        console.log("adding...");
        var target = $(e.target.parentElement.parentElement.parentElement);
        console.log(target);
        appendVideoToPlaylist(playlistID, target.context.id);
        //preparelibrarySlider();
    });

    $('body').on('click', 'div.remove_clip', function (e) {
        console.log("removing...");
        var target = $(e.target.parentElement.parentElement.parentElement);
        console.log(target);
        //target.remove();
        removeVideoFromPlaylist(playlistID, target.context.id);
        //preparelibrarySlider();
    });

    $('button.play').click(function () {
        var playlistID =getUrlVars()["playlistID"];
        console.log("Go edit...");
        window.location.href = "play.html?playlistID=" + playlistID;

    });
});

// adds a new video clip to the end of the slider
function addVideoClip(url, transcript, character, id) {
    var clip = $("<div class='item library' id=\'" + id + "\'>\n" +
        "                            <a>Video Failed to load</a>\n" +
        "                            <video controls class=\"video\">\n" +
        "                                <source src=\""+url+"\" type=\"video/ogg\">\n" +
        "                            </video>\n" +
        "                            <div class=\"controls\">\n" +
        "                                <div class=\"add_video\" style=\"top: 0\">\n" +
        "                                    <i class=\"material-icons\">\n" +
        "                                        add\n" +
        "                                    </i>\n" +
        "                                </div>\n" +
        "                            </div>\n" +        "                            <div style=\"bottom: 4%; position:absolute; left: 50%; transform: translate(-50%);\">Character: "+ character +"</div>\n" +
        "                            <div style=\"bottom: 0; position:absolute; left: 50%; transform: translate(-50%); width: 100%; overflow-wrap: break-word; font-size: 6.5px\">Transcript: "+ transcript +"</div>\n" +
        "                        </div>" +
        "                        <a> </a>");
    $("#Library").append(clip);
}

function appendVideoClip(url, transcript, character, id) {
    var clip = $("<div class='item library2' id=\'" + id + "\'>\n" +
        "                            <a>Video Failed to load</a>\n" +
        "                            <video controls class=\"video\">\n" +
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