$(document).ready(function () {
    getVideoSegments();

    addPlaylist("https://nerdist.com/wp-content/uploads/2019/03/Star-Trek-5-Captains-star-trek-41126417-1200-630-1200x676.jpg", "test1");
    addPlaylist("https://nerdist.com/wp-content/uploads/2019/03/Star-Trek-5-Captains-star-trek-41126417-1200-630-1200x676.jpg", "test2");
    addPlaylist("https://nerdist.com/wp-content/uploads/2019/03/Star-Trek-5-Captains-star-trek-41126417-1200-630-1200x676.jpg", "test3");
    addPlaylist("https://nerdist.com/wp-content/uploads/2019/03/Star-Trek-5-Captains-star-trek-41126417-1200-630-1200x676.jpg", "test4");
    addPlaylist("https://nerdist.com/wp-content/uploads/2019/03/Star-Trek-5-Captains-star-trek-41126417-1200-630-1200x676.jpg", "test5");
});

// adds a new video clip to the end of the slider
function addVideoClip(url) {
    var clip = $("<div class='item library'>\n" +
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
        "                        </div>" +
        "                        <a> </a>");
    $("#Library").append(clip);
}

function addPlaylist(url, name) {
 var playlist = $("<div class='item playlist'>\n" +
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

// gets all video segments from AWS and creates library entries for each of them
function getVideoSegments() {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "https://vhrvh0my7h.execute-api.us-east-2.amazonaws.com/dev/videoSegment/get", true);
    xhr.send();
    console.log("sent");
    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("XHR:" + xhr.responseText);
            json = JSON.parse(xhr.responseText);
            console.log(json.list);
            for (var i = 0; i < json.list.length; i++) {
                url = json.list[i].url;
                addVideoClip(url);
            }
        }
    };
}
