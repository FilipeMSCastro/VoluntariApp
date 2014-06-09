package com.icmvoluntariap;

import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Instituicao {

	private String nome;
	private String telefone;
	private String email;
	private double coordenadasLat;
	private double coordenadasLong;
	private String distrito;

	public Instituicao() {

	}

	public Instituicao(String nome, String telefone, String email,
			double coordenadasLat, double coordenadasLong, String distrito) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.coordenadasLat = coordenadasLat;
		this.coordenadasLong = coordenadasLong;
		this.distrito = distrito;
		
		ParseObject ins=new ParseObject("Instituicao");
		ins.put("nome",nome);
		ins.put("telefone",telefone);
		ins.put("email", email);
		ins.put("coordLat", coordenadasLat);
		ins.put("coordLong", coordenadasLong);
		ins.put("distrito", distrito);
		
		ins.saveInBackground();
		
	}
	
	public void getObject(){
		
		
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public double getCoordenadasLat() {
		return coordenadasLat;
	}

	public double getCoordenadasLong() {
		return coordenadasLong;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCoordenadasLat(double coordenadasLat) {
		this.coordenadasLat = coordenadasLat;
	}

	public void setCoordenadasLong(double coordenadasLong) {
		this.coordenadasLong = coordenadasLong;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

}
