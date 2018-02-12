window.fn = {};

window.fn.open = function() {
  var menu = document.getElementById('menu');
  menu.open();
};

window.fn.load = function(page) {
  var content = document.getElementById('content');
  var menu = document.getElementById('menu');
  content.load(page)
    .then(menu.close.bind(menu));
};

// $(document).on('init', function (event) {
//     console.log("Init: " + event.target.id);

//     var page = event.target;
//     var id = event.target.id;

//     switch (id) {
//         case 'page1':

//             break;
//         case 'page2':
//             $('ons-toolbar .center').html(page.data.title);
//             break;
//         case 'page1_tab1':
//             $('#push-button').on('click', function () {
//                 console.log("Push page");
//                 $('#myNavigator')[0].pushPage('page2.html', {
//                         data: {
//                             title: 'Page 2'
//                         }
//                     })
//                     .then(function () {
//                         console.log('Page 2');
//                         // ons.notification.alert('Page pushed!');
//                     });
//             })
//             break;
//         case 'page1_tab2':
//             break;
//         default:
//             break;
//     }
// });

// $(document).on('show', function (event) {
//     console.log("Show: " + event.target.id);
// });

// $(document).on('hide', function (event) {
//     console.log("Hide: " + event.target.id);
// });

// $(document).on('destroy', function (event) {
//     console.log("Destroy: " + event.target.id);
// });