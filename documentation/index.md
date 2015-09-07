# Android SMS Receiver for Titanium [![gittio](http://img.shields.io/badge/gittio-1.0.0-00B4CC.svg)](http://gitt.io/component/com.goyya.sms.receiver)
> BroadcastReceiver for running a JavaScript service in Titanium when a SMS was received

## Description

This module allows you to detect incoming SMS messages even when your app is not running. You can create a JavaScript service in Titanium that will be started when a SMS was received.

## Quick Start

### Installation 
Download the latest distribution ZIP-file and consult the [Titanium Documentation](http://docs.appcelerator.com/titanium/latest/#!/guide/Using_a_Module) on how install it, or simply use the [gitTio CLI](http://gitt.io/cli):

`$ gittio install com.goyya.sms.receiver`

### Usage

First, create your [Service](http://docs.appcelerator.com/platform/latest/#!/api/Titanium.Android.Service) as described in the [Android Services guide](http://docs.appcelerator.com/platform/latest/#!/guide/Android_Services). The name of the service should be __SmsReceiver__.

_SmsReceiver.js_
```js
var service = Titanium.Android.currentService;
var serviceIntent = service.getIntent();
var message = serviceIntent.getStringExtra("messageBody");
console.log(message);
service.stop();
```

Add the service to your `tiapp.xml` within the `<android>` part:
```xml
<android xmlns:android="http://schemas.android.com/apk/res/android">
  <services>
      <service type="interval" url="SmsReceiver.js"/>
  </services>
</android>
```
Next, define the BroadcastReceiver for `com.goyya.sms.receiver.SmsReceiver` in the Android Manifest section of your `tiapp.xml`. You can add a custom `intent-filter` for it.

```xml
<receiver android:name="com.goyya.sms.receiver.SmsReceiver"> 
  <intent-filter> 
      <action android:name=
        "android.provider.Telephony.SMS_RECEIVED" /> 
  </intent-filter>
</receiver>
```

The Android section of the `tiapp.xml` should now look like this:
```xml
<android xmlns:android="http://schemas.android.com/apk/res/android">
  <manifest>
    <application>
      
      <receiver android:name="com.goyya.sms.receiver.SmsReceiver"> 
          <intent-filter> 
              <action android:name=
                  "android.provider.Telephony.SMS_RECEIVED" /> 
          </intent-filter> 
      </receiver>
      
    </application>
  </manifest>
  <services>
      <service type="interval" url="SmsReceiver.js"/>
  </services>
</android>
```

That's it. The service code should now run when an incoming SMS is detected.

Going further, you can modify your intent-filter to only listen to a specific port like this:
```xml
<receiver android:name="com.goyya.sms.receiver.SmsReceiver"> 
  <intent-filter>
    <action android:name="android.intent.action.DATA_SMS_RECEIVED" /> 
    <data android:scheme="sms" /> 
    <data android:port="8901" /> 
  </intent-filter> 
</receiver> 
```

## License

[The MIT License (MIT)](LICENSE)