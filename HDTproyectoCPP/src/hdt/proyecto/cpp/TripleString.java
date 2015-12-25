package hdt.proyecto.cpp;

import android.app.Activity;

public class TripleString extends Activity{

	private long handler;

	String sujeto,predicado,objeto;

	public native String getSujetoNativo();

	public native String getPredicadoNativo();

	public native String getObjetoNativo();

	//La siguiente variable sirve para encontrar un determinado triple en el ListView

	protected long id;

	//Constructor sin parametrizar

	public TripleString(){

		this.sujeto = "";

		this.predicado = "";

		this.objeto = "";

	}

	//Constructor especifico para poder obtener los resultados de los métodos nativos

	public TripleString(long handle){

		handler = handle;

		//De la siguiente forma, cuando creemos un objeto TripleString de Java, se asginaran
		//los sujetos, predicados y objetos nativos de forma automatica a las variables de java

		this.sujeto = getSujetoNativo();

		this.predicado = getPredicadoNativo();

		this.objeto = getObjetoNativo();

	}

	public void setSujeto(String sujeto) {
		this.sujeto = sujeto;
	}

	public String getSujeto() {
		return sujeto;
	}

	public void setPredicado(String predicado) {
		this.predicado = predicado;
	}

	public String getPredicado() {
		return predicado;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	public String getObjeto() {
		return objeto;
	}	

	//Los siguientes métodos sirven para poder encontrar un determinado triple en el ListView

	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
}