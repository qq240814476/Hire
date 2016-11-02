package com.An.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.An.hire.R;
import com.An.hire.ToSearchActivity;


public class Home extends Fragment{
	private ImageButton search;
	private View mView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mView= inflater.inflate(R.layout.fragment_home, container, false);
		return mView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		search=(ImageButton) mView.findViewById(R.id.search);
		search.setOnClickListener(l);
	}
	OnClickListener l=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			switch (v.getId()) {
			case R.id.search:
				startActivity(new Intent(getActivity(),ToSearchActivity.class));
				
				break;

			default:
				break;
			}
		}
	};
}

