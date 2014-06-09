package com.icmvoluntariap;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class InstituicaoList extends ArrayList<Instituicao> implements
		Parcelable {

	// tutorial
	// http://www.anddev.org/novice-tutorials-f8/simple-tutorial-passing-arraylist-across-activities-t9996.html

	private static final long serialVersionUID = 663585476779879096L;

	public InstituicaoList() {

	}

	public InstituicaoList(Parcel in) {
		readFromParcel(in);
	}

	@SuppressWarnings("unchecked")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

		public InstituicaoList createFromParcel(Parcel in) {

			return new InstituicaoList(in);

		}

		public Object[] newArray(int arg0) {

			return null;

		}

	};

	private void readFromParcel(Parcel in) {

		this.clear();

		// First we have to read the list size

		int size = in.readInt();

		// Reading remember that we wrote first the Name and later the Phone
		// Number.

		// Order is fundamental

		for (int i = 0; i < size; i++) {

			Instituicao c = new Instituicao();

			c.setNome(in.readString());
			c.setTelefone(in.readString());
			c.setEmail(in.readString());
			c.setCoordenadasLat(in.readDouble());
			c.setCoordenadasLong(in.readDouble());
			c.setDistrito(in.readString());

			this.add(c);

		}

	}

	public int describeContents() {

		return 0;

	}

	public void writeToParcel(Parcel dest, int flags) {

		int size = this.size();

		// We have to write the list size, we need him recreating the list

		dest.writeInt(size);

		// We decided arbitrarily to write first the Name and later the Phone
		// Number.

		for (int i = 0; i < size; i++) {

			Instituicao c = this.get(i);

			dest.writeString(c.getNome());
			dest.writeString(c.getTelefone());
			dest.writeString(c.getEmail());
			dest.writeDouble(c.getCoordenadasLat());
			dest.writeDouble(c.getCoordenadasLong());
			dest.writeString(c.getDistrito());

		}

	}

}
