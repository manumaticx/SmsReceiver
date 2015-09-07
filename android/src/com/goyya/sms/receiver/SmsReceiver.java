package com.goyya.sms.receiver;

import org.appcelerator.titanium.TiApplication;
import org.appcelerator.kroll.common.Log;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.provider.Telephony.Sms.Intents;

public class SmsReceiver extends BroadcastReceiver {
	
	private static final String jsServiceName = "SmsReceiverService";
	
	@Override
    public void onReceive(Context context, Intent intent) {
			
				Log.d("com.goyya.sms.receiver", "received SMS");
				
				for (SmsMessage msg : Intents.getMessagesFromIntent(intent)){
					Intent serviceIntent = new Intent();
					Context ctx = TiApplication.getInstance().getApplicationContext();
	      	serviceIntent.setClassName(ctx, ctx.getPackageName() +'.'+ jsServiceName );
					
					serviceIntent.putExtra("messageBody", msg.getMessageBody());
					serviceIntent.putExtra("originatingAddress", msg.getOriginatingAddress());
					serviceIntent.putExtra("serviceCenterAddress", msg.getServiceCenterAddress());
					serviceIntent.putExtra("statusOnIcc", msg.getStatusOnIcc());
					serviceIntent.putExtra("timestampMillis", msg.getTimestampMillis());
					serviceIntent.putExtra("messageBody", msg.getMessageBody());
					
					context.startService(serviceIntent);
				}
    }
	
}