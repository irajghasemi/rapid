$(function(){
  var speed = 300;
  $('#nav button').on('click',function(){
    if ( $(this).hasClass('signup') ) {
      $('#login').hide();
      $('#signup').fadeIn(speed);
    } else if ( $(this).hasClass('login') ) {
      $('#signup').hide();
      $('#login').fadeIn(speed);
    }
  });
  $('.close').on('click',function(){
    $('.inset').fadeOut(speed);
  });
});

function confirmEmail() {
  var email = document.getElementById('email').value,
      confirmemail = document.getElementById('confirmemail').value;
  if ( email != confirmemail ) {
    $('#confirmemail').addClass('fail');
  }
}
function confirmPassword() {
  var password = document.getElementById('password').value,
      confirmpassword = document.getElementById('confirmpassword').value;
  if ( password != confirmpassword ) {
    $('#confirmpassword').addClass('fail');
  }
}




CanvasImage = function (e, t) {
  this.image = t;
  this.element = e;
  e.width = t.width;
  e.height = t.height;
  this.context = e.getContext("2d");
  this.context.drawImage(t, 0, 0);
};
CanvasImage.prototype = {
  blur:function(e) {
    this.context.globalAlpha = 0.5;
    for(var t = -e; t <= e; t += 3) {
      for(var n = -e; n <= e; n += 3) {
        this.context.drawImage(this.element, n, t);
        var blob = n >= 0 && t >= 0 && this.context.drawImage(this.element, -(n-1), -(t-1));
      }
    }
  }
};
$(function() {
  var image, canvasImage, canvas;
  $('#blur').each(function() {
    canvas = this;
    var blur = $(canvas).data('blur');
    image = new Image();
    image.onload = function() {
      canvasImage = new CanvasImage(canvas, this);
      canvasImage.blur(blur);
    };
    image.src = $(canvas).data('src');
  });
});