package com.example.test;

import com.example.demolist.R;
import com.example.test.BGARefreshLayout.BGARefreshLayoutDelegate;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

public class TestActivity extends Activity implements BGARefreshLayoutDelegate {

	private BGARefreshLayout mRefreshLayout;
	private ListView mDataLv;
	private TestAdapter mAdapter;
	private int size = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_listview_refresh);
		mRefreshLayout = (BGARefreshLayout) findViewById(R.id.rl_listview_refresh);
		mDataLv = (ListView) findViewById(R.id.lv_listview_data);
		setListener();
		processLogic(savedInstanceState);
		onUserVisible();
	}

	protected void setListener() {
		mRefreshLayout.setDelegate(this);
		mAdapter = new TestAdapter(this);
	}

	protected void processLogic(Bundle savedInstanceState) {
		mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(
				this, true));
		mDataLv.setAdapter(mAdapter);
	}

	protected void onUserVisible() {
		mAdapter.updateSize(size);
	}

	@Override
	public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected void onPreExecute() {
			}

			@Override
			protected Void doInBackground(Void... params) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void aVoid) {
				mRefreshLayout.endRefreshing();
				size = 2;
				mAdapter.updateSize(size);
			}
		}.execute();
	}

	@Override
	public boolean onBGARefreshLayoutBeginLoadingMore(
			BGARefreshLayout refreshLayout) {
		// TODO Auto-generated method stub
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected void onPreExecute() {
			}

			@Override
			protected Void doInBackground(Void... params) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void aVoid) {
				mRefreshLayout.endLoadingMore();
				size += 10;
				mAdapter.updateSize(size);
			}
		}.execute();
		return true;
	}

}
