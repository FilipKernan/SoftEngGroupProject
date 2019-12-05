$(document).ready(function () {
    getVideoSegments();
// Load 5 video clips


// Get the modal
    var modal = document.getElementById("newPartyModel");

    var submit = document.getElementById("modalSubmit");

// Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

// When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    };

    submit.onclick = function () {
        modal.style.display = "none";
    };

// When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };

    $('body').on('click', 'i.material-icons.mark', function (e) {
        console.log("marking");
        var target = $(e.target);
        target.replaceWith("<i class=\"material-icons marked\" style=\"top: 0\">\n" +
            "                            radio_button_checked\n" +
            "                        </i>")
    });

    $('body').on('click', 'i.material-icons.marked', function (e) {
        console.log("unmarking");
        var target = $(e.target);
        target.replaceWith("<i class=\"material-icons mark\" style=\"top: 0\">\n" +
            "                            radio_button_unchecked\n" +
            "                        </i>")
    });
});

// Creates a new video clip in the slider
function addVideoClip(url) {
    var clip = $("<div class='item library'>\n" +
        "                            <video controls class=\"video\">\n" +
        "                                <source src=\""+url+"\" type=\"video/ogg\">\n" +
        "                            </video>\n" +
        "                            <div class=\"controls\">\n" +
        "                                <i class=\"material-icons mark\" style=\"top: 0\">\n" +
        "                                    radio_button_unchecked\n" +
        "                                </i>\n" +
        "                            </div>\n" +
        "                               <div style=\"bottom: 4%; position:absolute; left: 50%; transform: translate(-50%);\">Character: "+ character +"</div>\n" +
        "                               <div style=\"bottom: 0; position:absolute; left: 50%; transform: translate(-50%); width: 100%; overflow-wrap: break-word; font-size: 6.5px\">Transcript: "+ transcript +"</div>\n" +
        "                        </div>\n" +
        "                        <a> </a>");
    $("#Library").append(clip);
}

// When the user clicks on the button, open the modal
function openModal() {
    var modal = document.getElementById("newPartyModel");
    modal.style.display = "block";
}

function closeModal() {
    var modal = document.getElementById("newPartyModel");
    var thirdPartyName = document.getElementById("thirdPartyNameField");
    thirdPartyName.value = "";
    var thirdPartyURL = document.getElementById("thirdPartyURLField");
    thirdPartyURL.value = "";
    modal.style.display = "none";
}

function registerThirdPartySite(url, add) {
    var data = {};
    data["url"] = url;
    data["addTPS"] = add;
    var js = JSON.stringify(data);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "https://ijhrhn9pr5.execute-api.us-east-2.amazonaws.com/dev/admin/thirdParty", true);
    xhr.send(js);
    console.log("sent");
    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("status:" + xhr.status);
            if (xhr.status == 200) {
                var js = JSON.parse(xhr.responseText);
                var status = js["statusCode"];
                if (status != 200) {
                    alert("Error: " + status + "\n" + js["error"]);
                }else {
                    $('#remoteSites').children().remove();
                }

            } else {
                console.log("actual:" + xhr.responseText);
                var js = JSON.parse(xhr.responseText);
                var err = js["response"];
                alert (err);
            }
        }
        preparelibrary2Slider();
    };
}

function getThirdPartySites() {

}