function preparelibrarySlider() {
    var $item = $('div.library'), //Cache your DOM selector
        visible = 4, //Set the number of items that will be visible
        index = 0, //Starting index
        endIndex = $item.length - visible; //End index

    var $item2 = $('div.library2'), //Cache your DOM selector
        visible2 = 4, //Set the number of items that will be visible
        index2 = 0, //Starting index
        endIndex2 = $item2.length - visible2; //End index

    // slide video clips to the left one and update the state of the controls
    $('div#arrowRL').click(function () {
        if (index < endIndex) {
            index++;
            $item.animate({'left': '-=25.1%'}); //Set width of div here
            if (index === endIndex) {
                $('div#arrowRL').css("color", "red");
            }
            if (index !== 0) {
                $('div#arrowLL').css("color", "black");
            }
        }
    });

// slide video clips to the right one and update the state of the controls
    $('div#arrowLL').click(function () {
        if (index > 0) {
            index--;
            $item.animate({'left': '+=25.1%'}); //Set width of div here
            if (index === 0) {
                $('div#arrowLL').css("color", "red");
            }
            if (index !== endIndex) {
                $('div#arrowRL').css("color", "black");
            }
        }
    });
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

    // slide video clips and update the state of the controls based on mouse wheel input
    $('#Library').bind('mousewheel', function(e){
        if(e.originalEvent.wheelDelta /120 > 0) {
            console.log('scrolling up !');
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
        }
        else{
            console.log('scrolling down !');
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
        }
    });

    $('#Library2').bind('mousewheel', function(e){
        if(e.originalEvent.wheelDelta /120 > 0) {
            console.log('scrolling up !');
            if (index2 > 0) {
                index2--;
                $item2.animate({'left': '+=25.1%'});//Set width of your div here
                if (index2 === 0) {
                    $('div#arrowLL2').css("color", "red");
                }
                if (index2 !== endIndex2) {
                    $('div#arrowRL2').css("color", "black");
                }
            }
        }
        else{
            console.log('scrolling down !');
            if (index2 < endIndex2) {
                index2++;
                $item2.animate({'left': '-=25.1%'});//Set width of your div here
                if (index2 === endIndex2) {
                    $('div#arrowRL2').css("color", "red");
                }
                if (index2 !== 0) {
                    $('div#arrowLL2').css("color", "black");
                }
            }
        }
    });
}