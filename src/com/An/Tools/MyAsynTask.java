package com.An.Tools;

import android.os.AsyncTask;

public abstract class MyAsynTask extends AsyncTask<Void, Void, String>{

	@Override
	protected String doInBackground(Void... params) {
		// TODO �Զ����ɵķ������
		runCode();
		return null;
	}

	public abstract void runCode();

}
