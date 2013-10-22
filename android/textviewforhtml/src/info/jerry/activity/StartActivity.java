package info.jerry.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartActivity extends Activity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		
		Button btnLocal = (Button) this.findViewById(R.id.btnLocal);
		btnLocal.setOnClickListener(this);
		
		Button btnResource= (Button) this.findViewById(R.id.btnResource);
		btnResource.setOnClickListener(this);
		
		Button btnInternet = (Button) this.findViewById(R.id.btnInternet);
		btnInternet.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.setClass(StartActivity.this, MainActivity.class);
		
		switch (v.getId()) {
			case R.id.btnLocal:{
				intent.putExtra("type", "local");
				break;
			}
			case R.id.btnResource:{
				intent.putExtra("type", "resource");
				break;
			}
			case R.id.btnInternet:{
				intent.putExtra("type", "internet");
				break;
			}
		}
		
		startActivity(intent);
	}
}
