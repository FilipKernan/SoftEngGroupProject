$(document).ready(function () {
    $('form.search').submit(function () {
        var character = $("input[name=character]").val().toLowerCase();
        var transcript = $("input[name=transcript]").val().toLowerCase();
        searchHandler(null, character, transcript).then(preparelibrarySlider);

    });
});

async function searchHandler(response, character, transcript) {
    $('.list#Library').children().remove();
    var hasResult = false;
    var local = getVideoSegments(false).then(function (result) {
        console.log(result);
        if (search(result, character, transcript)) {
            hasResult = true;
        }
    });

    remoteSites = ["https://g75v8iurq5.execute-api.us-east-1.amazonaws.com/RemoteSite/publicsegments?apikey=I14G0D8EJn4Q44b8dFhtb6CcdIraLflm9dpcyXAX"];
    for(index = 0; index < remoteSites.length; index++){
        var keyIdx = remoteSites[index].indexOf("?apikey=");
        var url = remoteSites[index].substring(0, keyIdx);
        var api = remoteSites[index].substring(keyIdx+8);
        let result = await makeRequest("GET", url, "", api);
        //if(search(result, character, transcript)) {
       //     hasResult = true;
       // }
    }

    await local.then(function () {
        if (!hasResult) {
            $("#Library").append("<p>no result found</p>");
        }
    });

}

function search(json, character, transcript) {
    hasResult = false;
    for (i = 0; i < json.list.length; i++) {
        try {
            ifMarked = json.list[i].ifMarked;
        } catch (e) {
            ifMarked = false;
        }
        if (character && json.list[i].character.toLowerCase().includes(character) && !transcript) {
            console.log(json.list[i].UUID);
            addVideoClip(json.list[i].url, json.list[i].transcript, json.list[i].character, ifMarked);
            hasResult = true;

        } else if (transcript && json.list[i].transcript.toLowerCase().includes(transcript) && !character) {
            console.log(json.list[i].UUID);
            addVideoClip(json.list[i].url, json.list[i].transcript, json.list[i].character, ifMarked);
            hasResult = true;

        } else if (!transcript && !character) {
            addVideoClip(json.list[i].url, json.list[i].transcript, json.list[i].character, ifMarked);
            hasResult = true;

        } else if (transcript && character && json.list[i].transcript.toLowerCase().includes(transcript) && json.list[i].character.toLowerCase().includes(character)) {
            console.log(json.list[i].UUID);
            addVideoClip(json.list[i].url, json.list[i].transcript, json.list[i].character, ifMarked);
            hasResult = true;
        }
    }
    return hasResult;
}