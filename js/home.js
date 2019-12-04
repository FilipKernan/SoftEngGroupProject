$(document).ready(function () {
    getVideoSegments();
    getPlaylists();

    // Get the modal
    var modal = document.getElementById("newPlaylist");
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
     "                                 class=\"video\">\n" +
     "                            <div class=\"controls\">\n" +
     "                                <div class=\"edit_playlist\">\n" +
     "                                    <i class=\"material-icons\">\n" +
     "                                        edit\n" +
     "                                    </i>\n" +
     "                                </div>\n" +
     "                                <div class=\"delete_playlist\">\n" +
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
    if(playlistNameField.value != ""){
        createPlaylist(playlistNameField.value);
        console.log("Created a new playlist.");
    }

playlistNameField.value = "";
    modal.style.display = "none";
}