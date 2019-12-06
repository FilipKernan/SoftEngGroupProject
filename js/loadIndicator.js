function loading() {
    console.log("loading...");
    $(document.body).css({'cursor' : 'wait'});
}

function doneLoading() {
    console.log("done loading");
    $(document.body).css({'cursor' : 'auto'});
}