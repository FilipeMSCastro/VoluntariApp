package com.icmvoluntariap;

import java.io.File;

import com.icmvoluntariap.R;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Parse.initialize(this, "OpmvDxp6nVkqfFqDuOgJ6yX2yxCn5fpYFnaS7Ub4",
				"s4zkRRFWYrLHVw2UZwtpFwwDF3jzHtVdUNnWMj7x");

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) {
	 * 
	 * // Inflate the menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.login, menu); return true; }
	 * 
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { // Handle
	 * action bar item clicks here. The action bar will // automatically handle
	 * clicks on the Home/Up button, so long // as you specify a parent activity
	 * in AndroidManifest.xml. int id = item.getItemId(); if (id ==
	 * R.id.action_settings) { return true; } return
	 * super.onOptionsItemSelected(item); }
	 */

	public void login(View v) {

		EditText user1 = (EditText) findViewById(R.id.email);
		final String username = user1.getText().toString();

		EditText pass = (EditText) findViewById(R.id.password);
		final String password = pass.getText().toString();

		ParseUser.logInInBackground(username, password, new LogInCallback() {

			@Override
			public void done(ParseUser user, ParseException e) {
				// TODO Auto-generated method stub
				if (user != null) {
					Toast toast = Toast.makeText(LoginActivity.this,
							"Login Efectuado!", Toast.LENGTH_SHORT);
					LinearLayout toastLayout = (LinearLayout) toast.getView();
					TextView toastTV = (TextView) toastLayout.getChildAt(0);
					toastTV.setTextSize(20);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();

					Intent in = new Intent(LoginActivity.this,
							MainActivity.class);
					startActivity(in);

				} else {
					Toast toast = Toast.makeText(LoginActivity.this,
							"Erro no Login!", Toast.LENGTH_SHORT);
					LinearLayout toastLayout = (LinearLayout) toast.getView();
					TextView toastTV = (TextView) toastLayout.getChildAt(0);
					toastTV.setTextSize(20);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				}
			}
		});

	}

	public void reg(View v) {
		Intent in = new Intent(LoginActivity.this, RegisterActivity.class);
		startActivity(in);
	}

	public void folder() {
		File folder = new File(Environment.getExternalStorageDirectory()
				+ "/VoluntariApp");
		if (!folder.exists()) {
			folder.mkdir();
		}

	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_login,
					container, false);
			return rootView;
		}
	}

}