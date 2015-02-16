$().ready(function(){
  // Instant Search
  $('#search_input').keyup(function(){
    $('.search_item').each(function(){
      var re = new RegExp($('#search_input').val(), 'i');
      if($(this).children('.search_text')[0].innerHTML.match(re)){
        $(this).show();
      }else{
        $(this).hide();
      };
    });
  });
});
$().ready(function(){
	// Instant Search
	$('#blocked').keyup(function(){
		$('.search_item_blocked').each(function(){
			var re = new RegExp($('#blocked').val(), 'i');
			if($(this).children('.search_text_blocked')[0].innerHTML.match(re)){
				$(this).show();
			}else{
				$(this).hide();
			};
		});
	});
});


