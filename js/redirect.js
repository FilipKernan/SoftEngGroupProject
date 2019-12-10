async function redirect(js, url) {
    let result = await makeRequest("POST", url, js);
    console.log(result.statusText);
    var resultJs = JSON.parse(result.statusText);
    if (result.statusText === 200) {
        if (js.statusCode !== 200){
            alert("Error: " + status + "\n" + js["error"]);
            return window.location.href;
        } else {
            return resultJs.url;
        }
    } else {
        console.log("actual:" + result.statusText);
        var err = js["error"];
        alert (err);
    }
}

async function getRedirect(js, url) {
    return await redirect(js, url);
}