var service = Titanium.Android.currentService;
var serviceIntent = service.getIntent();
var message = serviceIntent.getStringExtra("messageBody");

console.log(message);

service.stop();
