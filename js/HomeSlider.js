$(document).ready(function () {

    addVideoClip("movie.ogg");
    addVideoClip("movie.ogg");
    addVideoClip("movie.ogg");
    addVideoClip("movie.ogg");
    addVideoClip("movie.ogg");

    var $itemP = $('div.playlist'), //Cache your DOM selector
        visibleP = 4, //Set the number of items that will be visible
        indexP = 0, //Starting index
        endIndexP = $itemP.length - visibleP; //End index

    $('div#arrowR').click(function () {
        if (indexP < endIndexP) {
            indexP++;
            $itemP.animate({'left': '-=25.1%'});//Set width of your div here
            if (indexP === endIndexP) {
                $('div#arrowR').css("color", "red");
            }
            if (indexP !== 0) {
                $('div#arrowL').css("color", "black");
            }
        }
    });

    $('div#arrowL').click(function () {
        if (indexP > 0) {
            indexP--;
            $itemP.animate({'left': '+=25.1%'});//Set width of your div here
            if (indexP === 0) {
                $('div#arrowL').css("color", "red");
            }
            if (indexP !== endIndexP) {
                $('div#arrowR').css("color", "black");
            }
        }
    });

    var $item = $('div.library'), //Cache your DOM selector
        visible = 4, //Set the number of items that will be visible
        index = 0, //Starting index
        endIndex = $item.length - visible; //($item.length / visible) - 1; //End index

    $('div#arrowRL').click(function () {
        if (index < endIndex) {
            index++;
            $item.animate({'left': '-=25.1%'});//Set width of your div here
            if (index === endIndex) {
                $('div#arrowRL').css("color", "red");
            }
            if (index !== 0) {
                $('div#arrowLL').css("color", "black");
            }
        }
    });

    $('div#arrowLL').click(function () {
        if (index > 0) {
            index--;
            $item.animate({'left': '+=25.1%'});//Set width of your div here
            if (index === 0) {
                $('div#arrowLL').css("color", "red");
            }
            if (index !== endIndex) {
                $('div#arrowRL').css("color", "black");
            }
        }
    });
});

function addVideoClip(url) {
    var clip = $("<div class='item library'>\n" +
        "                            <a>Video Failed to load</a>\n" +
        "                            <video controls class=\"video\">\n" +
        "                                <source src=\"movie.ogg\" type=\"video/ogg\">\n" +
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