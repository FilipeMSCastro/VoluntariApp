package com.icmvoluntariap;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.icmvoluntariap.R;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Mapa extends Activity implements LocationListener {

	// private final LatLng LOCALIZACAO_ACTUAL = new
	// LatLng(40.629842,-8.654807);

	private GoogleMap map;
	private InstituicaoList listagemInstituicoes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.mapas);

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		// localização onde abre o mapa com o respectivo zoom
		// CameraUpdate update =
		// CameraUpdateFactory.newLatLngZoom(LOCALIZACAO_ACTUAL, 16);
		// map.animateCamera(update);

		// coloca a localização actual no mapa
		map.setMyLocationEnabled(true);
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

		// recebe as instituicoes e adiciona ao mapa
		recebeInstituicoes();

	}

	public void recebeInstituicoes() {

		// Recebe os extras do intent
		Bundle b = getIntent().getExtras();

		// Recebe a lista de instituições
		listagemInstituicoes = b.getParcelable("instituicoes");

		// adiciona as instituicoes ao mapa ⁽não está a funcionar)
		for (int i = 0; i <= listagemInstituicoes.size() - 1; i++) {

			map.addMarker(new MarkerOptions()
					.position(
							new LatLng(listagemInstituicoes.get(i)
									.getCoordenadasLat(), listagemInstituicoes
									.get(i).getCoordenadasLong()))
					.title(listagemInstituicoes.get(i).getNome())
					.snippet(
							listagemInstituicoes.get(i).getTelefone() + " - "
									+ listagemInstituicoes.get(i).getEmail()));
		}

	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

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
			Intent in = new Intent(Mapa.this, MainActivity.class);
			startActivity(in);

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
