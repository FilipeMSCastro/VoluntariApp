package com.icmvoluntariap;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.icmvoluntariap.R;
import com.parse.ParseUser;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilActivity extends Activity {

	protected void onCreate(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfil);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

		TextView wordToGuess = (TextView) findViewById(R.id.idade);
		wordToGuess.setTextColor(Color.parseColor("#FFFFFF"));

		ParseUser currentUser = ParseUser.getCurrentUser();

		if (currentUser != null) {
			Toast toast = Toast.makeText(this.getApplication(), "",
					Toast.LENGTH_SHORT);
			LinearLayout toastLayout = (LinearLayout) toast.getView();
			TextView toastTV = (TextView) toastLayout.getChildAt(0);
			toastTV.setTextSize(20);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();

		} else {
			Toast toast = Toast.makeText(this.getApplication(),
					"No Login Efectuado!", Toast.LENGTH_SHORT);
			LinearLayout toastLayout = (LinearLayout) toast.getView();
			TextView toastTV = (TextView) toastLayout.getChildAt(0);
			toastTV.setTextSize(20);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.perfil, menu);
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

	public void set() {

		final TextView wordToGuess = (TextView) findViewById(R.id.profissao);
		try {
			wordToGuess.setText("OLO");
			setContentView(R.layout.activity_perfil);
		} catch (Exception e) {
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle("error");
			alertDialog.setMessage(e.toString());
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// here you can add functions
				}
			});
			alertDialog.show();
		}
	}

	public String getId(String user) {

		String retur = "";
		try {

			FileInputStream fstream = new FileInputStream("textfile.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				if (strLine.contains(user)) {
					retur = strLine;
				}
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Toast.makeText(this.getApplication(), retur, Toast.LENGTH_SHORT).show();

		return retur;

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
			View rootView = inflater.inflate(R.layout.fragment_perfil,
					container, false);
			return rootView;
		}
	}

}