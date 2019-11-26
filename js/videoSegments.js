// gets all video segments from AWS and creates library entries for each of them
function getVideoSegments() {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "https://vhrvh0my7h.execute-api.us-east-2.amazonaws.com/dev/videoSegment/get", true);
    xhr.send();
    //console.log("sent");
    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            //console.log("XHR:" + xhr.responseText);
            json = JSON.parse(xhr.responseText);
            //console.log(json.list);
            for (var i = 0; i < json.list.length; i++) {
                url = json.list[i].url;
                transcript = json.list[i].transcript;
                character = json.list[i].character;
                addVideoClip(url, transcript, character);
            }
        }
        preparelibrarySlider();
    };
}


function getPlaylists() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "https://vhrvh0my7h.execute-api.us-east-2.amazonaws.com/dev/playlist/get", true);
    xhr.send();
    console.log("sent");
    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("XHR:" + xhr.responseText);
            json = JSON.parse(xhr.responseText);
            console.log(json.list);
            for (var i = 0; i < json.list.length; i++) {
                playlistName = json.list[i].name;
                addPlaylist("https://nerdist.com/wp-content/uploads/2019/03/Star-Trek-5-Captains-star-trek-41126417-1200-630-1200x676.jpg", playlistName);
            }
        }
        preparePlaylistSlider();
    };
}