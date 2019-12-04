// gets all video segments from AWS and creates library entries for each of them
function getVideoSegments() {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "https://ijhrhn9pr5.execute-api.us-east-2.amazonaws.com/dev/videoSegment/get", true);
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
    xhr.open("GET", "https://ijhrhn9pr5.execute-api.us-east-2.amazonaws.com/dev/playlist/get", true);
    xhr.send();
    console.log("sent");
    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("XHR:" + xhr.responseText);
            json = JSON.parse(xhr.responseText);
            console.log(json.list);
            for (var i = 0; i < json.list.length; i++) {
                playlistName = json.list[i].name;
                playlistId = json.list[i].ID;
                addPlaylist("https://nerdist.com/wp-content/uploads/2019/03/Star-Trek-5-Captains-star-trek-41126417-1200-630-1200x676.jpg", playlistName, playlistId);
            }
        }
        preparePlaylistSlider();
    };
}

function createPlaylist(name) {
    var data = {};
    data["name"] = name;
    var js = JSON.stringify(data);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "https://ijhrhn9pr5.execute-api.us-east-2.amazonaws.com/dev/playlist/create", true);
    xhr.send(js);
    console.log("sent");
    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("status:" + xhr.status);
            if (xhr.status == 200) {
                console.log("XHR:" + xhr.responseText);
                var js = JSON.parse(xhr.responseText);
                var status = js["statusCode"];
                if (status != 200) {
                    alert("Error: " + status + "\n" + js["error"]);
                } else {
                    $('.list#Playlist').children().remove();
                    getPlaylists();
                }


            } else {
                console.log("actual:" + xhr.responseText);
                var js = JSON.parse(xhr.responseText);
                var err = js["response"];
                alert(err);
            }
        }
    };
}

function deletePlaylist(id) {
    var data = {};
    data["id"] = id;
    var js = JSON.stringify(data);
    console.log(js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "https://ijhrhn9pr5.execute-api.us-east-2.amazonaws.com/dev/playlist/delete", true);
    xhr.send(js);
    console.log("sent");
    xhr.onloadend = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            console.log("status:" + xhr.status);
            if (xhr.status === 200) {
                console.log("XHR:" + xhr.responseText);
                var js = JSON.parse(xhr.responseText);
                var status = js["statusCode"];
                if (status !== 200) {
                    alert("Error: " + status + "\n" + js["error"]);
                } else {
                    $('.list#Playlist').children().remove();
                    getPlaylists();
                }
                return true;
            } else {
                console.log("actual:" + xhr.responseText);
                var js = JSON.parse(xhr.responseText);
                var err = js["response"];
                alert(err);
                return false;
            }
        }
    };
}

function appendVideoToPlaylist(playlistID, videoID) {
    var data = {};
    data["playlistID"] = playlistID;
    data["videoID"] = videoID;
    var js = JSON.stringify(data);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "https://ijhrhn9pr5.execute-api.us-east-2.amazonaws.com/dev/playlist/append", true);
    xhr.send(js);
    console.log("sent");
    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("status:" + xhr.status);
            if (xhr.status == 200) {
                console.log ("XHR:" + xhr.responseText);
                $('.list#Library2').children().remove();
                getVideoSegments();
                // var js = JSON.parse(xhr.responseText);
                // var status = js["statusCode"];
                // if(status != 200){
                //     alert("Error: " + status + "\n" + js["error"]);
                // }else{
                //     $('.list#Playlist').children().remove();
                //     getPlaylists();
                // }


            } else {
                console.log("actual:" + xhr.responseText)
                var js = JSON.parse(xhr.responseText);
                var err = js["response"];
                alert (err);
            }

        }
    };
}