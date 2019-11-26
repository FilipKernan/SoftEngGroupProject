$(document).ready(function () {
    getVideoSegments();


    appendVideoClip("test.ogg", "", "");
    appendVideoClip("test.ogg", "", "");
    appendVideoClip("test.ogg", "", "");
    appendVideoClip("test.ogg", "", "");
    appendVideoClip("test.ogg", "", "");

    var $item2 = $('div.library2'), //Cache your DOM selector
        visible2 = 4, //Set the number of items that will be visible
        index2 = 0, //Starting index
        endIndex2 = $item2.length - visible2; //End index


// slide video clips to the left one and update the state of the controls
    $('div#arrowRL2').click(function () {
        if (index2 < endIndex2) {
            index2++;
            $item2.animate({'left': '-=25.1%'}); //Set width of div here
            if (index2 === endIndex2) {
                $('div#arrowRL2').css("color", "red");
            }
            if (index2 !== 0) {
                $('div#arrowLL2').css("color", "black");
            }
        }
    });

// slide video clips to the right one and update the state of the controls
    $('div#arrowLL2').click(function () {
        if (index2 > 0) {
            index2--;
            $item2.animate({'left': '+=25.1%'}); //Set width of div here
            if (index2 === 0) {
                $('div#arrowLL2').css("color", "red");
            }
            if (index2 !== endIndex2) {
                $('div#arrowRL2').css("color", "black");
            }
        }
    });
});

// adds a new video clip to the end of the slider
function addVideoClip(url, transcript, character) {
    var clip = $("<div class='item library'>\n" +
        "                            <a>Video Failed to load</a>\n" +
        "                            <video controls class=\"video\">\n" +
        "                                <source src=\""+url+"\" type=\"video/ogg\">\n" +
        "                            </video>\n" +
        "                            <div class=\"controls\">\n" +
        "                            </div>\n" +
        "                            <div style=\"bottom: 4%; position:absolute; left: 50%; transform: translate(-50%);\">Character: "+ character +"</div>\n" +
        "                            <div style=\"bottom: 0; position:absolute; left: 50%; transform: translate(-50%); width: 100%; overflow-wrap: break-word; font-size: 6.5px\">Transcript: "+ transcript +"</div>\n" +
        "                        </div>" +
        "                        <a> </a>");
    $("#Library").append(clip);
}

function appendVideoClip(url, transcript, character) {
    var clip = $("<div class='item library2'>\n" +
        "                            <a>Video Failed to load</a>\n" +
        "                            <video controls class=\"video\">\n" +
        "                                <source src=\""+url+"\" type=\"video/ogg\">\n" +
        "                            </video>\n" +
        "                            <div class=\"controls\">\n" +
        "                                <div class=\"remove_clip\" style=\"top: 0\">\n" +
        "                                    <i class=\"material-icons\">\n" +
        "                                        close\n" +
        "                                    </i>\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                        </div>" +
        "                        <a> </a>");
    $("#Library2").append(clip);
}