package info.jerry.activity;

import info.jerry.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
/*
 * 分类栏目
 */
public class CategoryActivity extends Activity {
	private List<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
	private View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		view = this.getLayoutInflater().inflate(R.layout.category, null);
		setContentView(view);
		mData = CategoryData.getData();
		
		LayoutInflater inflater = LayoutInflater.from(this);
		LinearLayout header = (LinearLayout) inflater.inflate(R.layout.categoryheader, null);
		
		ListView list = (ListView) this.findViewById(R.id.categorylist);
		list.addHeaderView(header);
		list.setOnItemClickListener(mOnItemClickListener);
		ListAdapter adapter = new MyAdapter(this);
		list.setAdapter(adapter);
	}
	private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent intent = new Intent();
			intent.setClass(CategoryActivity.this, ResultActivity.class);
			startActivity(intent);
		}
	};
    public final class ViewHolder {
		public ImageView img;
		public TextView title;
	}
    
    public class MyAdapter extends BaseAdapter{
    	private LayoutInflater inflater;
    	public MyAdapter(Context context){
    		inflater = LayoutInflater.from(context);
    	}
		@Override
		public int getCount() {
			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if(convertView == null){
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.categoryitem, null);
				convertView.setMinimumHeight(100);
				holder.img = (ImageView) convertView.findViewById(R.id.category_icon);
				holder.title = (TextView) convertView.findViewById(R.id.category_name);
				convertView.setTag(holder);
			}else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.img.setBackgroundResource((Integer)mData.get(position).get("img"));
			holder.title.setText((String)mData.get(position).get("title"));
			
			return convertView;
		}
    	
    }
    
    boolean isBack;
    @Override
    protected void onPause() {
    	if(isBack){
    		isBack = false;
    		overridePendingTransition(R.anim.back_enter, R.anim.back_exit);
    	}
    	super.onPause();
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
    	if(keyCode == KeyEvent.KEYCODE_BACK){
    		isBack = true;
    	}
    	return super.onKeyUp(keyCode, event);
    }
}
