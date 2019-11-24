$(document).ready(function () {

    addVideoClip("movie.ogg");
    addVideoClip("movie.ogg");
    addVideoClip("movie.ogg");
    addVideoClip("movie.ogg");
    addVideoClip("movie.ogg");
});

function addVideoClip(url) {
    var clip = $("<div class='item library'>\n" +
        "                            <a>Video Failed to load</a>\n" +
        "                            <video controls class=\"video\">\n" +
        "                                <source src=\""+url+"\" type=\"video/ogg\">\n" +
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