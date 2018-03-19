var returnValue = {};
var id = 10;

myApp.controllers = {
    welcomeView: function (page, state) {
        if (state == "init") {
            $('#button-toggle-menu').click(function () {
                $('#mySplitter')[0].left.toggle();
            });
        }
    },
    listView: function (page, state) {
        if (state == "init") {
            if (!returnValue.action) {
                var data = [
                    'Hôm nay buồn ghê',
                    'Hôm nay vui quá hí hí!'
                ];
            } else if (returnValue.action == "ADD_NEW_NOTE") {
                console.log(returnValue);
                var item = `
            <ons-list-item id='note-${i}'>
                <ons-row>
                    <ons-col width="75%">${data[i]}</ons-col>
                    <ons-col>Image</ons-col>
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
                            <ons-col width="90%">${returnValue.value}</ons-col>
                            <ons-col style="margin: 0 auto;">
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
            }
        }
    },
    newNoteView: function (page, state) {
        if (state == "init") {
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
    }
}