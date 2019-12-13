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

    let remoteSites = await getRemoteSites();
    for(index = 0; index < remoteSites.list.length; index++){
        var keyIdx = remoteSites.list[index].url.indexOf("?apikey=");
        var url = remoteSites.list[index].url.substring(0, keyIdx);
        var api = remoteSites.list[index].url.substring(keyIdx+8);
        let result = await makeRequest("GET", url, "", api);
        result = JSON.parse(result.statusText);
        if(search(result, character, transcript)) {
           hasResult = true;
        }
    }

    await local.then(function () {
        if (!hasResult) {
            $("#Library").append("<p>no result found</p>");
        }
    });

}

function search(json, character, transcript) {
    hasResult = false;
    for (i = 0; i < json.segments.length; i++) {
        try {
            ifMarked = json.segments[i].ifMarked;
        } catch (e) {
            ifMarked = false;
        }

        if (character && json.segments[i].character.toLowerCase().includes(character) && !transcript) {
            console.log(json.segments[i].UUID);
            addVideoClip(json.segments[i].url, json.segments[i].text, json.segments[i].character, "",  ifMarked);
            hasResult = true;

        } else if (transcript && json.segments[i].text.toLowerCase().includes(transcript) && !character) {
            console.log(json.segments[i].UUID);
            addVideoClip(json.segments[i].url, json.segments[i].text, json.segments[i].character, "", ifMarked);
            hasResult = true;

        } else if (!transcript && !character) {
            addVideoClip(json.segments[i].url, json.segments[i].text, json.segments[i].character, "", ifMarked);
            hasResult = true;

        } else if (transcript && character && json.segments[i].text.toLowerCase().includes(transcript) && json.segments[i].character.toLowerCase().includes(character)) {
            console.log(json.segments[i].UUID);
            addVideoClip(json.segments[i].url, json.segments[i].text, json.segments[i].character, "", ifMarked);
            hasResult = true;
        }
    }
    return hasResult;
}