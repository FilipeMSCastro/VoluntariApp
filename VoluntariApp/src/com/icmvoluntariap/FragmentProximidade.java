package com.icmvoluntariap;

import com.icmvoluntariap.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

public class FragmentProximidade extends Fragment {

	public FragmentProximidade() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_proximidade, container,
				false);
		return view;
	}

}
