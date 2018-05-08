var returnValue = {};
var id = 10;

myApp.controllers = {
    welcomeView: function (page, state) {
        if (state == "init") {
            console.log('Init toggle button');
            $('#button-toggle-menu').click(function () {
                console.log('run');
                $('#mySplitter')[0].left.toggle();
            });
        }
    },
    listView: function (page, state) {
        if (state == "init") {
            if (!returnValue.action) {
                var data = [];
            } else if (returnValue.action == "ADD_NEW_NOTE") {
                console.log(returnValue);
                var item = `
            <ons-list-item id='note-${i}'>
                <ons-row>
                    <ons-col width="75%">${data[i]}</ons-col>
                    <ons-col></ons-col>
                </ons-row>
            </ons-list-item>`;
                $('#listItem').prepend(item);
            }

            for (i = 0; i < data.length; i++) {
                var item = `
            <ons-list-item id='note-${i}'>
                <ons-row>
                    <ons-col width="75%">${data[i]}</ons-col>
                    <ons-col>Image</ons-col>
                </ons-row>
            </ons-list-item>`;

                $('#listItem').prepend(item);
            }

            $('#button-new-note').click(function () {
                console.log(myApp.pages.newNote);
                $('#myNavigator')[0].pushPage(myApp.pages.newNote);
            });
            $('#button-toggle-menu').click(function () {
                $('#mySplitter')[0].left.toggle();
            });
        } else if (state == "show") {
            if (returnValue.action == "ADD_NEW_NOTE") {

                id++;

                // Add new item
                var item = `
                    <ons-list-item id='note-${id}'>
                        <ons-row>
                            <ons-col id='note-text-${id}' width="80%">${returnValue.value}</ons-col>
                            <ons-col width="10%">
                                <ons-icon onClick="editElement(${id});" id='btnEdit-${id}' style="display: block;" icon="md-edit"></ons-icon>
                            </ons-col>
                            <ons-col>
                                <ons-icon onClick="removeElement('#note-${id}');" id='btnDelete-${id}' style="display: block;" icon="md-close"></ons-icon>
                            </ons-col>
                        </ons-row>
                    </ons-list-item>`;
                
                $('#listItem').prepend(item);
                
                // // Add delete item listener
                // $('#btnDelete-' + id).click(function() {
                //     console.log('Delete ' + $('#note-' + id).attr(''));
                //     $('#note-' + id).remove();
                // });

                returnValue = {};
            } else if (returnValue.action == "EDIT_NOTE") {
                $(`#note-text-${returnValue.id}`).text(returnValue.value);
            }
        } else if (state == "destroy") {
            console.log('Destroy add new note');
        }
    },
    newNoteView: function (page, state) {
        if (state == "init") {
            $('#button-toggle-menu').click(function () {
                console.log('run');
                $('#mySplitter')[0].left.toggle();
            });
            
            $('#btnAddNote').click(function () {
                // console.log($('#txtNote').val());
                returnValue = {
                    action: 'ADD_NEW_NOTE',
                    value: $('#txtNote').val()
                }
                $('#myNavigator')[0].popPage();
            });
        }
    },
    editNoteView: function(page, state) {
        if (state == "init") {
            $('#button-toggle-menu').click(function () {
                console.log('run');
                $('#mySplitter')[0].left.toggle();
            });

            console.log("Edit mode " + $('#myNavigator')[0].topPage.data.id);

            $('#txtNote').val($('#myNavigator')[0].topPage.data.textNote);

            $('#btnEditNote').click(function () {
                // console.log($('#txtNote').val());
                returnValue = {
                    action: 'EDIT_NOTE',
                    value: $('#txtNote').val(),
                    id: $('#myNavigator')[0].topPage.data.id
                }
                $('#myNavigator')[0].popPage();
            });
        }
    },
    slideShowView: function (page, state) {
        if (state == "init") {
            // jQuery.getScript('lib/js/jssor.slider-27.1.0.min.js');
            $('#button-toggle-menu').click(function () {
                $('#mySplitter')[0].left.toggle();
            });
            $('#menuRegion').removeAttr('swipeable');

            var mySwiper = new Swiper('.swiper-container', {
                // Optional parameters
                direction: 'horizontal',
                loop: true,
                navigation: {
                    nextEl: '.swiper-button-next',
                    prevEl: '.swiper-button-prev',
                },
            })
        } else if (state == "show") {

        } else if (state == "destroy") {
            $('#menuRegion').attr('swipeable', '');
        }
    },
    getLocationView: function(page, state) {
        if (state == 'init') {
            $('#button-toggle-menu').click(function () {
                $('#mySplitter')[0].left.toggle();
            });
            $('#button-get').click(function() {
                // console.log(navigator.geolocation.getCurrentPosition(onSuccess, onError));
                // var options = {
                //     maximunAge: 3600000,
                //     timeout: 300, 
                //     enableHighAccuracy: true
                // }
                console.log('Starting get');
                navigator.geolocation.getCurrentPosition(onSuccess, onError);

                function onSuccess(position) {
                    // alert('Latitude: ' + position.coords.latitude + '\n' +
                    //     'Longitude: ' + position.coords.longitude + '\n');
                    console.log('Get');
                    $('#text-result').text(
                        'Latitude: ' + position.coords.latitude + ' ' +
                        'Longitude: ' + position.coords.longitude
                    )
                }
                function onError(error) {
                    console.log('Error');
                    alert('Error');
                }
            });
        }
    }
}

