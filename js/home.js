$(document).ready(function () {
    loading();
    getVideoSegments();
    getPlaylists().then(doneLoading);

    // Get the modal
    var modal = document.getElementById("playlistModalSubmit");

    var submit = document.getElementById("modalSubmit");

// Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

// When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    };

    submit.onclick = function () {
        modal.style.display = "none";
    };

// When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };

    $('body').on('click', 'div.delete_video', function (e) {
        console.log("deleting...");
        var target = $(e.target).parent().parent().parent();
        target.remove();
        preparelibrarySlider()
    });

    $('body').on('click', 'div.delete_playlist', function (e) {
        loading();
        var id = $((e.target.parentElement.parentElement).parentElement).context.id;
        if( deletePlaylist(id)) {
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

    /*
    addPlaylist("https://nerdist.com/wp-content/uploads/2019/03/Star-Trek-5-Captains-star-trek-41126417-1200-630-1200x676.jpg", "test1");
    addPlaylist("https://nerdist.com/wp-content/uploads/2019/03/Star-Trek-5-Captains-star-trek-41126417-1200-630-1200x676.jpg", "test2");
    addPlaylist("https://nerdist.com/wp-content/uploads/2019/03/Star-Trek-5-Captains-star-trek-41126417-1200-630-1200x676.jpg", "test3");
    addPlaylist("https://nerdist.com/wp-content/uploads/2019/03/Star-Trek-5-Captains-star-trek-41126417-1200-630-1200x676.jpg", "test4");
    addPlaylist("https://nerdist.com/wp-content/uploads/2019/03/Star-Trek-5-Captains-star-trek-41126417-1200-630-1200x676.jpg", "test5");*/
});

//<source src=\""+url+"\" type=\"video/ogg\">\n" +
// adds a new video clip to the end of the slider
function addVideoClip(url, transcript, character) {
    var clip = $("                        <div class='item library'>\n" +
        "                            <a>Video Failed to load</a>\n" +
        "                            <video controls class=\"video\">\n" +
        "                                <source src=\""+url+"\" type=\"video/ogg\">\n" +
        "                            </video>\n" +
        "                            <div class=\"controls\">\n" +
        "                                <div class=\"delete_playlist\" style=\"top: 0\">\n" +
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
     "                                 class=\"video\">\n" +
     "                            <div class=\"controls\">\n" +
     "                                <div class=\"edit_playlist\">\n" +
     "                                    <i class=\"material-icons\">\n" +
     "                                        edit\n" +
     "                                    </i>\n" +
     "                                </div>\n" +
     "                                <div class=\"delete_playlist\" onclick='deletePlaylistModal(\"" + name + "\",\""+ id +"\")'>\n" +
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
function openModal() {
    var modal = document.getElementById("newPlaylist");
    modal.style.display = "block";
}


function closeModal() {
    var modal = document.getElementById("newPlaylist");
    var playlistNameField = document.getElementById("playlistNameField");
    if(playlistNameField.value !== ""){
        loading();
        createPlaylist(playlistNameField.value).then(doneLoading);
        console.log("Created a new playlist.");
    }

playlistNameField.value = "";
    modal.style.display = "none";
}

function deletePlaylistModal(name, playlistID) {
    console.log(name);
    var modal = "<div class='modal-content'>" +
        "               <span class=\"close\" onclick='closeModal(\"deletePlaylist\")'>&times;</span>" +
        "               Do you want to delete " + name + "?<br/>" +
        "               <button onclick='deletePlaylist(" + playlistID + "); closeModal(\"deletePlaylist\")'>delete</button> " +
        "           </div>";


    document.getElementById("deletePlaylist").innerHTML = modal;
    openModal("deletePlaylist");

}

function deletePlaylist(playlistID) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "https://vhrvh0my7h.execute-api.us-east-2.amazonaws.com/dev/playlist/delete", true);
    xhr.send(JSON.stringify("{ 'playlistID': " + playlistID + "}"));//put json in here
    //console.log("sent");
    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            json = JSON.parse(xhr.responseText);
            console.log(json);
            if(json.list.id == playlistID  && json.responseText != '200') {
                $("#Playlist").empty();
                getPlaylists();
            }

        }
    };
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
    xhr.open("POST", "https://ijhrhn9pr5.execute-api.us-east-2.amazonaws.com/dev/videoSegment/add", true);

    xhr.send(js);

    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                $("#Library").empty();
                getVideoSegments();
            } else {
                console.log("actual:" + xhr.responseText)
                var js = JSON.parse(xhr.responseText);
                var err = js["response"];
                alert (err);
            }
        }
    }
}

