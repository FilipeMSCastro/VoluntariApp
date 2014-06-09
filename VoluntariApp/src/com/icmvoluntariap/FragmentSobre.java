package com.icmvoluntariap;

import com.icmvoluntariap.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentSobre extends Fragment {

	public FragmentSobre() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_sobre, container, false);

		return view;
	}

}