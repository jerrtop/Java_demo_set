package info.jerry.qqcategory.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewAdapter extends BaseExpandableListAdapter implements
		OnItemClickListener {
	public static final int itemHeight = 48;// 每项的高度
	public static final int paddingLeft = 36;// 每项的偏移
	private int myPaddingLeft = 0;
	private MyGridView toolbarGridView;
	private String menu_toolbar_name_array[] = { "存储卡", "我的下载", "图书导入", "系统备份",
			"系统恢复", "清除全部", "在线升级", "快速入门", "关于开卷", "退出系统", "在线升级", "快速入门",
			"关于开卷", "退出系统", "关于开卷", "退出系统", "关于开卷", "退出系统", "关于开卷", "退出系统" };
	private int menu_toolbar_image_array[] = { R.drawable.icon_sdcard,
			R.drawable.icon_sdcard, R.drawable.icon_sdcard,
			R.drawable.icon_sdcard, R.drawable.icon_sdcard,
			R.drawable.icon_sdcard, R.drawable.icon_sdcard,
			R.drawable.icon_sdcard, R.drawable.icon_sdcard,
			R.drawable.icon_sdcard, R.drawable.icon_sdcard,
			R.drawable.icon_sdcard, R.drawable.icon_sdcard,
			R.drawable.icon_sdcard, R.drawable.icon_sdcard,
			R.drawable.icon_sdcard, R.drawable.icon_sdcard,
			R.drawable.icon_sdcard, R.drawable.icon_sdcard,
			R.drawable.icon_sdcard };
	private List<TreeNode> treeNodes = new ArrayList<TreeNode>();
	private Context parentContext;

	private LayoutInflater layoutInflater;

	static public class TreeNode {
		Object parent;
		List<Object> childs = new ArrayList<Object>();
	}

	public ListViewAdapter(Context view, int myPaddingLeft) {
		parentContext = view;
		this.myPaddingLeft = myPaddingLeft;
	}

	public List<TreeNode> getTreeNodes() {
		return treeNodes;
	}

	public void UpdateTreeNode(List<TreeNode> nodes) {
		treeNodes = nodes;
	}

	public void RemoveAll() {
		treeNodes.clear();
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return treeNodes.get(groupPosition).childs.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	static public TextView getTextView(Context context) {
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT, itemHeight);
		TextView textView = new TextView(context);
		textView.setLayoutParams(lp);
		textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		return textView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		if (convertView == null) {
			layoutInflater = (LayoutInflater) parentContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.view, null);
			toolbarGridView = (MyGridView) convertView
					.findViewById(R.id.Grid_view_toolbar);
			toolbarGridView.setNumColumns(4);
			toolbarGridView.setGravity(Gravity.CENTER);
			toolbarGridView.setHorizontalSpacing(10);
			toolbarGridView.setAdapter(getMenuAdapter(menu_toolbar_name_array,
					menu_toolbar_image_array));
			toolbarGridView.setOnItemClickListener(this);

		}
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return treeNodes.get(groupPosition).childs.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return treeNodes.get(groupPosition).parent;
	}

	@Override
	public int getGroupCount() {
		return treeNodes.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	/**
	 * 可定义的list
	 * 
	 * @param groupPosition
	 * @param isExpanded
	 * @param convertView
	 * @param parent
	 * @return
	 */
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TextView textView = getTextView(parentContext);
		textView.setText(getGroup(groupPosition).toString());
		textView.setPadding(myPaddingLeft + paddingLeft, 0, 0, 0);
		return textView;
	}

	/**
	 * 构造菜单Adapter
	 * 
	 * @param menuNameArray
	 *            菜单名称
	 * @param imageResouceArray
	 *            菜单图标
	 * @return SimpleAdapter
	 */
	private SimpleAdapter getMenuAdapter(String[] menuNameArray,
			int[] imageResouceArray) {
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < menuNameArray.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", imageResouceArray[i]);
			map.put("itemText", menuNameArray[i]);
			data.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(parentContext, data,
				R.layout.item_menu, new String[] { "itemImage", "itemText" },
				new int[] { R.id.itemImage, R.id.itemText });
		return adapter;
	}

	/*
	 * 每个item中元素的id是否稳定，如果你用position当id ，那就是不稳定的
	 * 
	 * @see android.widget.ExpandableListAdapter#hasStableIds()
	 */
	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View iView, int position,
			long id) {
		Toast.makeText(parentContext, "当前选中的是：" + position, Toast.LENGTH_SHORT)
				.show();
	}

}
