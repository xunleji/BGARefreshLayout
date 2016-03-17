package cn.bingoogolapple.refreshlayout.demo.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TestAdapter extends BaseAdapter {

	private Context context;
	private int size;

	public TestAdapter(Context context) {
		super();
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView tv = new TextView(context);
		tv.setText("position==" + position);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
		tv.setPadding(0, 50, 0, 50);
		return tv;
	}

	public void updateSize(int sizes) {
		this.size = sizes;
		notifyDataSetChanged();
	}

}
