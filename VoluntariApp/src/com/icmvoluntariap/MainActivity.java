package com.icmvoluntariap;

import java.util.ArrayList;
import java.util.List;

import com.icmvoluntariap.R;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MainActivity extends Activity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	CustomDrawerAdapter adapter;
	

	List<DrawerItem> dataList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initializing
		dataList = new ArrayList<DrawerItem>();
		mTitle = mDrawerTitle = getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		// Add Drawer Item to dataList
		// Add Drawer Item to dataList

		dataList.add(new DrawerItem("Inicio", R.drawable.btn1));
		dataList.add(new DrawerItem("Feed", R.drawable.ic_action_group));
		dataList.add(new DrawerItem("Perfil", R.drawable.icone_perfil_mdpi));
		// dataList.add(new
		// DrawerItem("Comunicar Causa",R.drawable.icone_comunicar_causa_mdpi));
		dataList.add(new DrawerItem("Proximidade", R.drawable.icone_localizac));
		dataList.add(new DrawerItem("Confirmar Ajuda",
				R.drawable.icone_sobre_alternativa_mdpi));
		// dataList.add(new DrawerItem("Proximidade",
		// R.drawable.ic_action_settings));
		dataList.add(new DrawerItem("Sobre", R.drawable.icone_sobre_mdpi));

		adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
				dataList);

		mDrawerList.setAdapter(adapter);

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			SelectItem(0);
		}

		// if (savedInstanceState == null) {
		// getFragmentManager().beginTransaction()
		// .add(R.id.container, new PlaceholderFragment()).commit();
		// }
	}

	public void ua(View v){
		Intent browserIntent = new Intent("android.intent.action.VIEW",Uri.parse("http://www.ua.pt"));

		startActivity(browserIntent);
	}

	public void deca(View v){
		Intent browserIntent = new Intent("android.intent.action.VIEW",Uri.parse("http://www.ua.pt/deca/"));

		startActivity(browserIntent);
	}
	
	public void deti(View v){
		Intent browserIntent = new Intent("android.intent.action.VIEW",Uri.parse("http://www.ua.pt/deti/"));

		startActivity(browserIntent);
	}
	public void SelectItem(int possition) {

		Fragment fragment = null;
		Bundle args = new Bundle();
		switch (possition) {
		case 0:
			fragment = new FragmentInicio();
			break;
		case 1:
			Intent in = new Intent(MainActivity.this, FeedActivity.class);
			startActivity(in);
		case 2:
			fragment = new FragmentPerfil();
			break;
		case 3:
			fragment = new FragmentProximidade();
			break;
		case 4:
			fragment = new FragmentAjuda();
			break;

		case 5:
			fragment = new FragmentSobre();
			break;

		default:
			break;
		}

		fragment.setArguments(args);
		FragmentManager frgManager = getFragmentManager();
		frgManager.beginTransaction().replace(R.id.content_frame, fragment)
				.commit();

		mDrawerList.setItemChecked(possition, true);
		setTitle(dataList.get(possition).getItemName());
		mDrawerLayout.closeDrawer(mDrawerList);

	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		int id = item.getItemId();
		if (id == R.id.action_perfil) {
			Intent in = new Intent(this, EditarPerfilActivity.class);
			startActivity(in);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			SelectItem(position);

		}
	}

	// Mapas
	int cont = 0;

	public void enviaLocalizacao(View v) {

		InstituicaoList listagemInstituicoes = new InstituicaoList();

		/*
		 * ParseQuery<ParseObject> query = ParseQuery.getQuery("Instituicao");
		 * query.findInBackground(new FindCallback<ParseObject>() {
		 * 
		 * @Override public void done(List<ParseObject> util, ParseException e)
		 * { // TODO Auto-generated method stub if (util != null) { for (int i =
		 * 0; i < util.size() && i < 5; i++) { cont = util.size(); ParseObject
		 * test=util.get(i);
		 * 
		 * } } }
		 * 
		 * });
		 */

		// cria instituições de exemplo (falta ir buscar a base de dados online)
		Instituicao i1 = new Instituicao("abracos", "9666666666",
				"aaa@ppp.com", 40.629842, -8.654867, "Aveiro");
		Instituicao i2 = new Instituicao("abracos2", "9666666666",
				"aaa@ppp.com", 40.629042, -8.654867, "Aveiro");

		// adiciona as instituições criadas a lista
		// listagemInstituicoes.add(i1);
		
		int distancia = 50;
		
		check_and_add(i1,listagemInstituicoes,distancia);
		check_and_add(i2,listagemInstituicoes,distancia);


		Bundle b = new Bundle();

		// Como a lista de instituições é Parcelable podemos colucar assim com a
		// chave instituicoes
		b.putParcelable("instituicoes", listagemInstituicoes);

		// Cria o intent, adiciona o bundle e inicia a activity que tem o mapa
		Intent intent = new Intent(this, Mapa.class);
		intent.putExtras(b);
		startActivity(intent);
	}
	
	private boolean check_and_add(Instituicao i, InstituicaoList lista, int vall){
	
		boolean flag=false;
		
		LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 
		Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		double longitude = location.getLongitude();
		double latitude = location.getLatitude();
		
		if(distance(i.getCoordenadasLat(),i.getCoordenadasLong(),latitude,longitude)<vall){
			lista.add(i);
			flag = true;
		}
		
		return flag;
		
	}
	
	private double distance(double lat1, double lon1, double lat2, double lon2) {
	      double theta = lon1 - lon2;
	      double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	      dist = Math.acos(dist);
	      dist = rad2deg(dist);
	      dist = dist * 60 * 1.1515;
	      
	      dist = dist * 1.609344;

	      return (dist);
	    }

	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    /*::  This function converts decimal degrees to radians             :*/
	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    private double deg2rad(double deg) {
	      return (deg * Math.PI / 180.0);
	    }

	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    /*::  This function converts radians to decimal degrees             :*/
	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    private double rad2deg(double rad) {
	      return (rad * 180.0 / Math.PI);
	    }

	public void lerQrcode(View v) {

		Intent intent = new Intent(this, CaptureQrCode.class);
		startActivityForResult(intent, 0);
	}

	long tStart;

	public void setStart() {
		tStart = System.currentTimeMillis();
	}

	public double getTime() {
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		return elapsedSeconds;
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {

		if (requestCode == 0) {
			if (resultCode == this.RESULT_OK) {

				String contents = intent.getStringExtra("qrCode");

				if (contents.contains("INICIO")) {
					TextView mensagem = (TextView) findViewById(R.id.mensagem);
					mensagem.setText("Bem-vindo!");
					setStart();
				} else if (contents.contains("FIM")) {

					TextView mensagem = (TextView) findViewById(R.id.mensagem);
					mensagem.setText("Obrigado pela Ajuda!");

				} else {
					TextView mensagem = (TextView) findViewById(R.id.mensagem);
					mensagem.setText("ERRO NA LEITURA DO QR-CODE!");
				}

			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	// public static class PlaceholderFragment extends Fragment {

	// public PlaceholderFragment() {
	// }

	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState) {
	// View rootView = inflater.inflate(R.layout.fragment_main, container,
	// false);
	// return rootView;
	// }
	// }

}