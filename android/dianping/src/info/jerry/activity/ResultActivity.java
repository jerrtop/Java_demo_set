package info.jerry.activity;

import info.jerry.R;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class ResultActivity extends Activity implements ServerListener,
		OnClickListener {
	private List<Map<String, Object>> mData;
	private List<Map<String, Object>> filterData;
	private ListView list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.searchresult);
		
		mData = PoiResult.getData();
		filterData = mData;
		list = (ListView) this.findViewById(R.id.resultlist);
		
	}
	
	@Override
	public void onClick(View v) {

	}

	@Override
	public void serverDataArrived(List list, boolean isEnd) {

	}
	
	public class PoiResultAdapter extends BaseAdapter{
		private LayoutInflater mInflater;

		public PoiResultAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}
		@Override
		public int getCount() {
			return filterData.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null){
				Log.v("is NULL","DF2" + position);
			}
			Log.v("ListViewLog","DF"+position);
			return null;
		}
		
	}

}
