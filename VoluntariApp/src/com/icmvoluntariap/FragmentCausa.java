package com.icmvoluntariap;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentCausa extends Fragment {

	public FragmentCausa() {


			}

	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_causa, container, false);

	
		
		return view;
	}

	


	
}
