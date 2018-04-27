function removeElement(id) {
    console.log('Delete ' + id);
    $(id).remove();
}

function editElement(id) {
    var textNote = $(`#note-text-${id}`).text();
    $('#myNavigator')[0].pushPage(myApp.pages.editNote, {
        data: { textNote: textNote, id : id }
    });
}