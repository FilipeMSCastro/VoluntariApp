package com.icmvoluntariap;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class EditarPerfilActivity extends Activity {

	int cont;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_perfil);


		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

		ParseQuery<ParseUser> query = ParseUser.getQuery();
		query.whereEqualTo("username", "oi");

		query.findInBackground(new FindCallback<ParseUser>() {
			
			

			@Override
			public void done(List<ParseUser> util, ParseException e) {
				
				if (util != null) {
					for (int i = 0; i < util.size() && i < 5; i++) {
						cont = util.size();
						
						ParseUser obj=util.get(i);
						
						//nome
						EditText editTextNome = (EditText) findViewById(R.id.nome);
						String nome = obj.getString("nome_completo");
						editTextNome.setText(nome);
						//email
						EditText editText = (EditText) findViewById(R.id.email);
						String email = obj.getEmail();
						editText.setText(email);
						//idade
						EditText editTextIdade = (EditText) findViewById(R.id.idade);
						int idade = obj.getInt("idade");
						editTextIdade.setText(""+idade);
						//profissao
						EditText editTextProfissao = (EditText) findViewById(R.id.profissao);
						String profissao = obj.getString("Profissao");
						editTextProfissao.setText(profissao);
						//telefone
						EditText editTextTel = (EditText) findViewById(R.id.phone);
						String phone = obj.getString("phone");
						editTextTel.setText(phone);
						//distrito
						EditText editTextDis = (EditText) findViewById(R.id.distrito);
						String dis = obj.getString("distrito");
						editTextDis.setText(dis);
				
			}

		}
				}});

		
	}
	
	public void chageUser(View v) {
		
	

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

		ParseUser user = ParseUser.getCurrentUser();
		user.setEmail(email);

		// other fields can be set just like with ParseObject
		user.put("phone", phone);
		user.put("idade", Integer.parseInt(idade));
		user.put("nome_completo", nome);
		user.put("Profissao", profissao);
		user.put("distrito", distrito);

		try {
			user.save();
			Toast.makeText(EditarPerfilActivity.this,
					"Utilizador Actualizado!",
					Toast.LENGTH_SHORT).show();
					finish();
		} catch (ParseException e) {
			Toast.makeText(EditarPerfilActivity.this,
					"Erro a Actualizar!",
					Toast.LENGTH_SHORT).show();

			e.printStackTrace();
		}
	
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.editar_perfil, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_editar_perfil,
					container, false);
			return rootView;
		}
	}

}
