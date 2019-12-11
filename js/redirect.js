function redirect(js, url) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.send(js);
    //console.log("sent");
    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            //console.log("XHR:" + xhr.responseText);
            json = JSON.parse(xhr.responseText);
            console.log(json);

            window.location.replace(json.url).then(function (json) {
                setPlaylistId(json.data);
            });

            // for (var i = 0; i < json.list.length; i++) {
            //     url = json.list[i].url;
            //     transcript = json.list[i].transcript;
            //     character = json.list[i].character;
            //     videoId = json.list[i].UUID;
            //     addVideoClip(url, transcript, character, videoId);
            // }
        }
    };
    // let result = await makeRequest("POST", url, js);
    // console.log(result.statusText);
    // var resultJs = JSON.parse(result.statusText);
    // if (result.statusText === 200) {
    //     if (js.statusCode !== 200){
    //         alert("Error: " + status + "\n" + js["error"]);
    //         return window.location;
    //     } else {
    //         setPlaylistId(resultJs.data);
    //         console.log(resultJs.data);
    //         return resultJs.url;
    //     }
    // } else {
    //     console.log("actual:" + result.statusText);
    //     var err = js["error"];
    //     alert (err);
    // }
}

async function getRedirect(js, url) {
    return await redirect(js, url);
}