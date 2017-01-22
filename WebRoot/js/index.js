$(function () {
  $('input, textarea').each(function() {
        $(this).on('focus', function() {
            $(this).parent('.input').addClass('active');
        });

        $(this).on('blur', function() {
            if ($(this).val().length == 0) {
                $(this).parent('.input').removeClass('active');
            }
        });
        if ($(this).val() != '') $(this).parent('.input').addClass('active');
    });
});
$(".visitOthers").live("click",function(){
	var input=$(this).text();
	alert(input);
})