package com.icmvoluntariap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.icmvoluntariap.R;
import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.net.ParseException;
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
import android.os.Build;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void newUser(View v) {

		Parse.initialize(this, "OpmvDxp6nVkqfFqDuOgJ6yX2yxCn5fpYFnaS7Ub4",
				"s4zkRRFWYrLHVw2UZwtpFwwDF3jzHtVdUNnWMj7x");

		EditText userid = (EditText) findViewById(R.id.user);
		final String username = userid.getText().toString();

		EditText pass = (EditText) findViewById(R.id.password);
		String password = pass.getText().toString();

		EditText emailid = (EditText) findViewById(R.id.email);
		String email = emailid.getText().toString();

		EditText phoneid = (EditText) findViewById(R.id.phone);
		String phone = phoneid.getText().toString();

		EditText idadeid = (EditText) findViewById(R.id.idade);
		String idade = idadeid.getText().toString();

		EditText nomeid = (EditText) findViewById(R.id.nome);
		String nome = nomeid.getText().toString();

		EditText profissaoid = (EditText) findViewById(R.id.profissao);
		String profissao = profissaoid.getText().toString();

		EditText distritoid = (EditText) findViewById(R.id.distrito);
		String distrito = distritoid.getText().toString();

		final ParseUser user = new ParseUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);

		// other fields can be set just like with ParseObject
		user.put("phone", phone);
		user.put("idade", Integer.parseInt(idade));
		user.put("nome_completo", nome);
		user.put("Profissao", profissao);
		user.put("distrito", distrito);

		user.signUpInBackground(new SignUpCallback() {

			@Override
			public void done(com.parse.ParseException e) {
				// TODO Auto-generated method stub
				if (e == null) {
					Toast toast = Toast.makeText(RegisterActivity.this,
							"Utilizador Adicionado!", Toast.LENGTH_SHORT);
					LinearLayout toastLayout = (LinearLayout) toast.getView();
					TextView toastTV = (TextView) toastLayout.getChildAt(0);
					toastTV.setTextSize(30);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();

					Intent in = new Intent(RegisterActivity.this,
							FeedActivity.class);
					startActivity(in);
				} else {
					Toast toast = Toast.makeText(RegisterActivity.this,
							"Erro a adicionar utilizador tente outra vez!",
							Toast.LENGTH_SHORT);
					LinearLayout toastLayout = (LinearLayout) toast.getView();
					TextView toastTV = (TextView) toastLayout.getChildAt(0);
					toastTV.setTextSize(30);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				}
			}
		});

	}

	public void folder(String pasta) {
		File folder = new File(Environment.getExternalStorageDirectory()
				+ "/VoluntariApp/" + pasta);
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
			View rootView = inflater.inflate(R.layout.fragment_register,
					container, false);
			return rootView;
		}
	}

}