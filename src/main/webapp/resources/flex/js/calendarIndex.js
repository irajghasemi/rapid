var date = new Date();
var values = date.toDateString().split(" ");
$(".calendar-weekday").text(values[0]);
$(".calendar-monthday").text(values[2]);
$(".calendar-month").text(values[1]);
$(".calendar-year").text(values[3]);
$(".calendar-icon").attr("title",values[3]);