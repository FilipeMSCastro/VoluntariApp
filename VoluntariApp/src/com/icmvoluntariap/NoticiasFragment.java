package com.icmvoluntariap;

import com.icmvoluntariap.R;
import com.parse.ParseObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NoticiasFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_noticias, container,
				false);
		
		insertPedido(1,"14:14","15:15","2/4/2014","Santa Casa","Procuram-se voluntários para a delegação da cruz vermelha de Aveiro. Procuram-se voluntários para participar no serviço de promoção de saúde nas escolas da região de Aveiro.");


		return rootView;
	}
	

	public void insertPedido(int id,String inicio, String fim, String data, String inst, String des){
		
	
	
		
		ParseObject ins=new ParseObject("Pedido");
		ins.put("idpedido",id);
		ins.put("hora_inicio", inicio);
		ins.put("hora_fim", fim);
		ins.put("date", data);
		ins.put("id_inst", inst);
		ins.put("descricao", des);
		
		ins.saveInBackground();

	}
	
}
