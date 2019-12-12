$(document).ready(function () {
    $('form.search').submit(function () {
        var character = $("input[name=character]").val().toLowerCase();
        var transcript = $("input[name=transcript]").val().toLowerCase();
        search(null, character, transcript);

    });
});

function search(response, character, transcript) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", getVideoSegment, true);
    xhr.send();
    xhr.onloadend = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            var json = JSON.parse(xhr.responseText);

            $('.list#Library').children().remove();
            var hasResult = false;

            for (var i = 0; i < json.list.length; i++) {

                try{
                    var ifMarked = json.list[i].ifMarked;
                }catch(e){
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