package info.jerry.qqcategory.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
//GridView显示不完全的原因是因为,他的外层也套用了一个滑动的控件,
//解决的方法就是重写GridView,是控制GridView不能滚动
public class MyGridView extends GridView {

	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	/*
	 * 在控件的父元素正要放置它的子控件时调用
	 * @see android.widget.GridView#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//设置不滚动
		int expandSec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSec);
	}
}
