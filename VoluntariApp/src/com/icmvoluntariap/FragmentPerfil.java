package com.icmvoluntariap;

import com.icmvoluntariap.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentPerfil extends Fragment {

	public FragmentPerfil() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater
				.inflate(R.layout.fragment_perfil, container, false);

		return view;
	}

}