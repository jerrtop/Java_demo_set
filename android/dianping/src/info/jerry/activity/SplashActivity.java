package info.jerry.activity;

import info.jerry.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
/*
 * 欢迎页
 */
public class SplashActivity extends Activity {
	private final int TIME_UP = 1;
	
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what == TIME_UP){
				Intent intent = new Intent();
				intent.setClass(SplashActivity.this, MainActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.splash_screen_fade, R.anim.splash_screen_hold);//activity切换时动画效果
				SplashActivity.this.finish();
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen_view);
		
		new Thread(){
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				Message message = new Message();
				message.what = TIME_UP;
				handler.sendMessage(message);
			};
		}.start();
	}

}
