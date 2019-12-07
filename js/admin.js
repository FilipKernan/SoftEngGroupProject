$(document).ready(function () {
    loading();
    getVideoSegments().then(doneLoading);
    populateThirdParties();
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
function openModal(id) {
    var modal = document.getElementById(id);
    modal.style.display = "block";
}

function closeModal() {
    var modal = document.getElementById("newPartyModel");
    var thirdPartyName = document.getElementById("thirdPartyNameField");
    thirdPartyName.value = "";
    var thirdPartyURL = document.getElementById("thirdPartyURLField");
    thirdPartyURL.value = "";
    modal.style.display = "none";
    var modal = document.getElementById("deleteThirdParty");
    modal.style.display = "none";


}


function openDeleteModal(name, url) {
    var modal = document.getElementById("deleteThirdParty");
    modal.style.display = "block";
    console.log("deleting: "  + url);
    $("#thirdParty").empty().append(name);
    $('#deleteButton').click(function () {
        handleDelete(name, url);
        closeModal("deleteThirdParty");
    });
}

function populateThirdParties() {

    var data = {};
    js = JSON.stringify(data);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "https://fqtldon5xe.execute-api.us-east-2.amazonaws.com/dev/admin/thirdParty/get", true);
    console.log("sending api request");
    xhr.send(js);

    xhr.onloadend = function () {
        var json = JSON.parse(xhr.responseText);
        console.log(json);
        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("status:" + json["statusCode"]) ;
            if (xhr.status == 200) {
                if (json["statusCode"] != 200 ){
                    alert("Error: " + status + "\n" + json["error"]);
                } else {
                    $("#thirdPartyList").empty();
                    for (var i = 0; i < json.list.length; i++){
                        var name = json.list[i].ID;
                        var url = json.list[i].url;
                        console.log(url);
                        addThirdParty(name, url);
                    }
                }

            }



        }
    }

}

function addThirdParty(name, url) {
    var thirdParty = $("<li><img src=\"../assets/error.png\" class=\"icon\" onclick=\"openDeleteModal('" + name +"', '"+ url +"')\">" +
        "               URL: <i>" + url + "</i>   " + name + "</li>");
    $("#thirdPartyList").append(thirdParty);
}

function handleAddThirdParty() {
    var form = document.newParty;
    var data = {};
    data["ID"] = $("input[name=thirdPartyNameField]").val();
    data["url"] = $("input[name=thirdPartyURLField]").val();
    data["addTPS"] = true;
    //console.log("url value is: " + $("input[name=thirdPartyURLField]").val());
    // get form data
    // create json
    // make api call
    // on endload add the li to the list

    var json = JSON.stringify(data);
    //console.log(json);
    xhr = new XMLHttpRequest();
    xhr.open("POST", "https://fqtldon5xe.execute-api.us-east-2.amazonaws.com/dev/admin/thirdParty/register", true);
    xhr.send(json);

    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("status:" + xhr.status);
            if (xhr.status == 200) {
                var js = JSON.parse(xhr.responseText);
                var status = js["statusCode"];
                if (status != 200) {
                    alert("Error: " + status + "\n" + js["error"]);
                }

            } else {
                console.log("actual:" + xhr.responseText);
                var js = JSON.parse(xhr.responseText);
                var err = js["response"];
                alert(err);
            }
            populateThirdParties();
        }

    }
}
function handleDelete(name, url) {

    var data = {};
    data["ID"] = name;
    data["url"] = url;
    data["addTPS"] = false;
    var json = JSON.stringify(data);

    xhr = new XMLHttpRequest();
    xhr.open("POST", "https://fqtldon5xe.execute-api.us-east-2.amazonaws.com/dev/admin/thirdParty/register", true);
    xhr.send(json);
    xhr.onloadend = function () {

        console.log(name)

        if (xhr.status == 200) {
            var js = JSON.parse(xhr.responseText);
            console.log(js);
            var status = js["statusCode"];
            if (status != 200) {
                alert("Error: " + status + "\n" + js["error"]);
            } else {
                $("li:contains(" + name + ")").remove();
            }

        } else {
            console.log("actual:" + xhr.responseText);
            var js = JSON.parse(xhr.responseText);
            var err = js["response"];
            alert(err);
        }
    }

}