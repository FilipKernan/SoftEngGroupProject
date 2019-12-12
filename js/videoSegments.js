// gets all video segments from AWS and creates library entries for each of them


async function getVideoSegments() {


    let result = await makeRequest("GET", getVideoSegment, "");
    console.log(result.statusText);
    var js = JSON.parse(result.statusText);
    if (result.status === 200) {
        if (js["statusCode"] !== 200) {
            alert("Error: " + status + "\n" + js["error"]);
        }else {
            for (var i = 0; i < js.segments.length; i++) {
                url = js.segments[i].url;
                transcript = js.segments[i].transcript;
                character = js.segments[i].character;
                videoId = js.segments[i].UUID;
                ifMarked = js.segments[i].isMarked;
                addVideoClip(url, transcript, character, videoId, ifMarked);
            }
        }
    } else {
        console.log("actual:" + result.statusText);
        var err = js["error"];
        alert (err);
    }
    //preparelibrarySlider();
}

async function getRemoteVideoSegments() {
    let remoteSites = await getRemoteSites();
    console.log(remoteSites);
    for(index = 0; index < remoteSites.list.length; index++){
        var keyIdx = remoteSites.list[index].url.indexOf("?apikey=");
        var url = remoteSites.list[index].url.substring(0, keyIdx);
        var api = remoteSites.list[index].url.substring(keyIdx+8);
        let result = await makeRequest("GET", url, "", api);
        var js = JSON.parse(result.statusText);
        console.log(js);
        if (result.status === 200) {
            if (js["statusCode"] !== 200) {
                alert("Error: " + status + "\n" + js["error"]);
            }else {
                    for (var i = 0; i < js.segments.length; i++) {
                        url = js.segments[i].url;
                        text = js.segments[i].text;
                        character = js.segments[i].character;
                        addVideoClip(url, text, character, url, false);
                    }
            }
        } else {
            console.log("actual:" + result.statusText);
            var err = js["error"];
            alert (err);
        }
        //preparelibrarySlider();
    }
}

async function getRemoteSites(){
    let result = await makeRequest("POST", getThirdParty, "");
    console.log(result.statusText);
    var js = JSON.parse(result.statusText);
    if (result.status === 200) {
        if (js["statusCode"] !== 200) {
            alert("Error: " + status + "\n" + js["error"]);
        }else {
            return js;
        }
    } else {
        console.log("actual:" + result.statusText);
        var err = js["error"];
        alert (err);
    }
}

async function getPlaylists() {
    let result = await makeRequest("GET", getPlaylist, "");
    console.log(result.statusText);
    var js = JSON.parse(result.statusText);
    if (result.status === 200) {
        if (js["statusCode"] !== 200) {
            alert("Error: " + status + "\n" + js["error"]);
        }else {
            for (var i = 0; i < js.list.length; i++) {
                var playlistName = js.list[i].name;
                var playlistId = js.list[i].ID;
                addPlaylist("https://nerdist.com/wp-content/uploads/2019/03/Star-Trek-5-Captains-star-trek-41126417-1200-630-1200x676.jpg", playlistName, playlistId);
            }
        }
    } else {
        console.log("actual:" + result.statusText);
        var err = js["error"];
        alert (err);
    }
    preparePlaylistSlider();
}

async function getPlaylistName(id) {
    let result = await makeRequest("GET", getPlaylist, "");
    console.log(result.statusText);
    var js = JSON.parse(result.statusText);
    if (result.status === 200) {
        if (js["statusCode"] !== 200) {
            alert("Error: " + status + "\n" + js["error"]);
        }else {
            for (var i = 0; i < js.list.length; i++) {
                if (js.list[i].ID === id) {
                    $("input[name=playlistName]").val(js.list[i].name);
                    break;
                }
            }
        }
    } else {
        console.log("actual:" + result.statusText);
        var err = js["error"];
        alert (err);
    }
}

async function renamePlaylist(id, name) {
    var data = {};
    data["newName"] = name;
    data["id"] = id;
    var js = JSON.stringify(data);
    let result = await makeRequest("POST", playlistRename, js);
    console.log(result.statusText);
    var js = JSON.parse(result.statusText);
    if (result.status === 200) {
        if (js["statusCode"] !== 200) {
            alert("Error: " + status + "\n" + js["error"]);
        }
    } else {
        console.log("actual:" + result.statusText);
        var err = js["error"];
        alert (err);
    }
}

async function createPlaylist(name) {
    var data = {};
    data["name"] = name;
    var js = JSON.stringify(data);
    let result = await makeRequest("POST", playlistCreate, js);
    console.log(result.statusText);
    var js = JSON.parse(result.statusText);
    if (result.status === 200) {
        if (js["statusCode"] !== 200) {
            alert("Error: " + status + "\n" + js["error"]);
        }else {
            $('.list#Playlist').children().remove();
            await getPlaylists();
        }
    } else {
        console.log("actual:" + result.statusText);
        var err = js["error"];
        alert (err);
    }
}

async function deletePlaylist(id) {
    var data = {};
    data["id"] = id;
    var js = JSON.stringify(data);
    let result = await makeRequest("POST", playlistDelete, js);
    console.log(result.statusText);
    var js = JSON.parse(result.statusText);
    if (result.status === 200) {
        if (js["statusCode"] !== 200) {
            alert("Error: " + status + "\n" + js["error"]);
        }else {
            $('.list#Playlist').children().remove();
            await getPlaylists();
        }
    } else {
        console.log("actual:" + result.statusText);
        var err = js["error"];
        alert (err);
    }
    preparelibrary2Slider();
}

async function deleteVideo(id) {
    var data = {};
    data["id"] = id;
    var js = JSON.stringify(data);
    let result = await makeRequest("POST", deleteVideoSegment, js);
    console.log(result.statusText);
    var js = JSON.parse(result.statusText);
    if (result.status === 200) {
        if (js["statusCode"] !== 200) {
            alert("Error: " + status + "\n" + js["error"]);
        }else {
            $('.list#Library').children().remove();
            await getVideoSegments();
        }
    } else {
        console.log("actual:" + result.statusText);
        var err = js["error"];
        alert (err);
    }
    preparelibrary2Slider();
}

async function getClipInPlayList(id) {
    var data = {};
    data["plID"] = id;
    var js = JSON.stringify(data);
    let result = await makeRequest("POST", getPlaylistSegments, js);
    console.log(result.statusText);
    var js = JSON.parse(result.statusText);
    if (result.status === 200) {
        if (js["statusCode"] !== 200) {
            alert("Error: " + status + "\n" + js["error"]);
        }else {
            for (var i = 0; i < js.list.length; i++) {
                var clipUrl = js.list[i].url;
                var clipCharacter = js.list[i].character;
                var clipTranscript = js.list[i].transcript;
                var clipId = js.list[i].UUID;
                await appendVideoClip(clipUrl, clipTranscript, clipCharacter, clipId);
            }
        }
    } else {
        console.log("actual:" + result.statusText);
        var err = js["error"];
        alert (err);
    }
    preparelibrary2Slider();
}

async function appendVideoToPlaylist(playlistID, videoID) {
    var data = {};
    data["playlistID"] = playlistID;
    if (videoID.includes("http")) {
        var data = {};
        data["character"] = $('#' + videoID + '').transcript;

        data["tpsURL"] = videoID;

        data["transcript"] = $('#' + videoID + '').transcript;

        data["id"] = "";

        data["iLocal"] = "false";

        var js = JSON.stringify(data);
        console.log("JS:" + js);
        var xhr = new XMLHttpRequest();
        xhr.open("POST", addVideoSegment, true);

        xhr.send(js);

        xhr.onloadend = function () {
            console.log(xhr);
            console.log(xhr.request);
            if (xhr.readyState == XMLHttpRequest.DONE) {
                if (xhr.status == 200) {
                    var js = JSON.parse(xhr.responseText);

                    $('#' + videoID + '').attr('id', js.videoID);

                    videoID = js.videoID;

                } else {
                    console.log("actual:" + xhr.responseText);
                    var js = JSON.parse(xhr.responseText);
                    var err = js["response"];
                    alert (err);
                }
            }
        }
    }
    data["videoID"] = videoID;
    var js = JSON.stringify(data);
    let result = await makeRequest("POST", appendPlaylist, js);
    console.log(result.statusText);
    var js = JSON.parse(result.statusText);
    if (result.status === 200) {
        if (js["statusCode"] !== 200) {
            alert("Error: " + status + "\n" + js["error"]);
        }else {
            console.log("XHR:" + result.statusText);
            $('.list#Library2').children().remove();
            await getClipInPlayList(playlistID);
        }
    } else {
        console.log("actual:" + result.statusText);
        var err = js["error"];
        alert (err);
    }
    preparelibrary2Slider();

}

async function removeVideoFromPlaylist(playlistID, videoID) {
    var data = {};
    data["playlistID"] = playlistID;
    data["videoID"] = videoID;
    console.log(data);
    var js = JSON.stringify(data);
    let result = await makeRequest("POST", deleteVideoSegment, js);
    console.log(result.statusText);
    var js = JSON.parse(result.statusText);
    if (result.status === 200) {
        if (js["statusCode"] !== 200) {
            alert("Error: " + status + "\n" + js["error"]);
        }else {
            console.log("XHR:" + result.statusText);
            $('.list#Library2').children().remove();
            await getClipInPlayList(playlistID);
        }
    } else {
        console.log("actual:" + result.statusText);
        var err = js["error"];
        alert (err);
    }
    preparelibrary2Slider();
}

function makeRequest(method, url, js, apikey = null) {
    return new Promise(function (resolve, reject) {
        let xhr = new XMLHttpRequest();
        xhr.open(method, url);
        if(apikey) {
            xhr.setRequestHeader("x-api-key", apikey);
        }
        xhr.onload = function () {
            resolve({
                status: this.status,
                statusText: xhr.response});
        };
        xhr.onerror = function () {
            resolve({
                status: this.status,
                statusText: xhr.statusText
            });
        };
        xhr.send(js);
    });
}