
$(document).ready(function () {
    loading();
    let vids = getVideoSegments();
    let playlists = getPlaylists();
    let remote = getRemoteVideoSegments(["https://avhiou2y5d.execute-api.us-east-2.amazonaws.com/RemoteSite/publicsegments?apikey=mH0naThzgz3LRgli6PiEa8ktNznRmClw83de0vCc"]);
    Promise.all([vids, playlists, remote]).then(preparelibrarySlider).then(doneLoading);
    // Get the modal
    var modal = document.getElementById("playlistModalSubmit");


// Get the <span> element that closes the modal

// When the user clicks on <span> (x), close the modal



// When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };

    $('body').on('click', 'div.delete_video', function (e) {
        loading();
        var id = $((e.target.parentElement.parentElement).parentElement).context.id;
        console.log(id);
        promise = deleteVideo(id);
        promise.then(function () {
            if(promise) {
                console.log("deleting...");
                $('.list#Library').children().remove();
                getVideoSegments().then(doneLoading).then(preparelibrarySlider);
            } else {
                doneLoading();
            }
        })

    });

    $('body').on('click', 'div.delete_playlist', function (e) {
        loading();
        var id = $((e.target.parentElement.parentElement).parentElement).context.id;
        if( deletePlaylist( id)) {
            console.log("deleting...");
            $('.list#Playlist').children().remove();
            getPlaylists().then(doneLoading);
        } else {
            doneLoading();
        }
    });

    $('body').on('click', 'div.edit_playlist', function (e) {
        var id = $((e.target.parentElement.parentElement).parentElement).context.id;
        console.log("Go edit...");


        window.location.href = "edit.html?playlistID=" + id;


    });

    $('body').on('click', 'img.image', function (e) {
        var id = $((e.target).parentElement).context.id;
        console.log("Go play...");

        window.location.href =  "play.html?playlistID=" + id;

    });
    /*
    addPlaylist("https://nerdist.com/wp-content/uploads/2019/03/Star-Trek-5-Captains-star-trek-41126417-1200-630-1200x676.jpg", "test1");
    addPlaylist("https://nerdist.com/wp-content/uploads/2019/03/Star-Trek-5-Captains-star-trek-41126417-1200-630-1200x676.jpg", "test2");
    addPlaylist("https://nerdist.com/wp-content/uploads/2019/03/Star-Trek-5-Captains-star-trek-41126417-1200-630-1200x676.jpg", "test3");
    addPlaylist("https://nerdist.com/wp-content/uploads/2019/03/Star-Trek-5-Captains-star-trek-41126417-1200-630-1200x676.jpg", "test4");
    addPlaylist("https://nerdist.com/wp-content/uploads/2019/03/Star-Trek-5-Captains-star-trek-41126417-1200-630-1200x676.jpg", "test5");*/
});

//<source src=\""+url+"\" type=\"video/ogg\">\n" +
// adds a new video clip to the end of the slider
function addVideoClip(url, transcript, character, id, ifMarked) {
    var clip = $("<div class='item library' id=\'" + id + "\'>\n" +
        "                            <a>Video Failed to load</a>\n" +
        "                            <video controls class=\"video\">\n" +
        "                                <source src=\""+url+"\" type=\"video/ogg\">\n" +
        "                            </video>\n" +
        "                            <div class=\"controls\">\n" +
        "                                <div class=\"delete_video\" style=\"top: 0\">\n" +
        "                                    <i class=\"material-icons\">\n" +
        "                                        close\n" +
        "                                    </i>\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                            <div style=\"bottom: 4%; position:absolute; left: 50%; transform: translate(-50%);\">Character: "+ character +"</div>\n" +
        "                            <div style=\"bottom: 0; position:absolute; left: 50%; transform: translate(-50%); width: 100%; overflow-wrap: break-word; font-size: 6.5px\">Transcript: "+ transcript +"</div>\n" +
        "                        </div>\n" +
        "                        <a> </a>");
    $("#Library").append(clip);
}

function addPlaylist(url, name, id) {
 var playlist = $("<div class='item playlist' id=\'" + id + "\'>\n" +
     "                            <a>"+name+"</a>\n" +
     "                            <br>\n" +
     "                            <img src=\""+url+"\"\n" +
     "                                 class=\"image\">\n" +
     "                            <div class=\"controls\">\n" +
     "                                <div class=\"edit_playlist\">\n" +
     "                                    <i class=\"material-icons\">\n" +
     "                                        edit\n" +
     "                                    </i>\n" +
     "                                </div>\n" +
     "                                <div class=\"delete_playlist\" >\n" +
     "                                    <i class=\"material-icons\">\n" +
     "                                        close\n" +
     "                                    </i>\n" +
     "                                </div>\n" +
     "                            </div>\n" +
     "                        </div>" +
     "                        <a> </a>");
 $("#Playlist").append(playlist);
}

// When the user clicks on the button, open the modal
function openModal(id) {
    var modal = document.getElementById(id);
    modal.style.display = "block";
}


function closeModal(id) {
    var modal = document.getElementById(id);
    loading();
    if (id == "newPlaylist") {
        var playlistNameField = document.getElementById("playlistNameField");
        if (playlistNameField.value !== "") {
            loading();
            createPlaylist(playlistNameField.value).then(doneLoading);
            console.log("Created a new playlist.");
        }
        playlistNameField.value = "";

    }

    modal.style.display = "none";
}





function newSegment() {
    var form = document.newVideoSegmentForm;

    var data = {};
    data["character"] = form.character.value;

    var segments = document.newVideoSegmentForm.base64Encoding.value.split(',');
    data["base64EncodedValue"] = segments[1];

    data["transcript"] = form.transcript.value;

    data["id"] = "";

    var js = JSON.stringify(data);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", addVideoSegmentLocal, true);

    xhr.send(js);

    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                $("#Library").empty();
                getVideoSegments().then(preparelibrarySlider).then(doneLoading);
            } else {
                console.log("actual:" + xhr.responseText);
                var js = JSON.parse(xhr.responseText);
                var err = js["response"];
                alert (err);
            }
        }
    }
}




