/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $('#registerBtn').attr('disabled', 'disabled');
})
function checkPasswords() {
    password1 = $('#memberPassword').val();
    password2 = $('#memberRepeatPassword').val();
    if (password1 == password2) {
        $('#registerBtn').removeAttr('disabled')
    } else {
        $('#registerBtn').attr('disabled', 'disabled');
    }
}