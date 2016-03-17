package cn.bingoogolapple.refreshlayout.demo.ui.activity;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout.BGARefreshLayoutDelegate;
import cn.bingoogolapple.refreshlayout.demo.R;
import cn.bingoogolapple.refreshlayout.demo.model.RefreshModel;
import cn.bingoogolapple.refreshlayout.demo.ui.fragment.TestAdapter;
import cn.bingoogolapple.refreshlayout.demo.util.ThreadUtil;
import android.annotation.SuppressLint;
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
					Thread.sleep(MainActivity.LOADING_DURATION);
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
					Thread.sleep(MainActivity.LOADING_DURATION);
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
