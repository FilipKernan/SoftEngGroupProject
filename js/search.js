$(document).ready(function () {
    $('form.search').submit(function () {
        var character = $("input[name=character]").val().toLowerCase();
        var transcript = $("input[name=transcript]").val().toLowerCase();
        search(null, character, transcript);

    });
});


function search(response, character, transcript) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "https://vhrvh0my7h.execute-api.us-east-2.amazonaws.com/dev/videoSegment/get", true);
    xhr.send();
    xhr.onloadend = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            json = JSON.parse(xhr.responseText);

            $('.list#Library').children().remove();
            var hasResult = false;

            for (i = 0; i < json.list.length; i++) {
                if (character && json.list[i].character.toLowerCase() === character) {
                    console.log(json.list[i].UUID);
                    addVideoClip(json.list[i].url, json.list[i].transcript, json.list[i].character);
                    hasResult = true;

                } else if (transcript && json.list[i].transcript.toLowerCase().includes(transcript)) {
                    console.log(json.list[i].UUID);
                    addVideoClip(json.list[i].url, json.list[i].transcript, json.list[i].character);
                    hasResult = true;

                } else if (!transcript && !character) {
                    addVideoClip(json.list[i].url, json.list[i].transcript, json.list[i].character);
                }
            }

            if (!hasResult) {
                $("#Library").append("<p>no result found</p>");
            } else {
                preparelibrarySlider();
            }
        }
    }
}