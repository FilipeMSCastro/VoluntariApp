package com.icmvoluntariap;

import com.icmvoluntariap.R;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentAjuda extends Fragment {

	public FragmentAjuda() {

	}


	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_ajuda, container, false);

		return view;
	}

	

	
}