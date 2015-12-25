package hdt.proyecto.cpp;

import android.app.Activity;

public class HDT extends Activity{

	private long handler;

	//Una vez que hemos obtenido el handle, podemos establecer los métodos que queremos utilizar

	private native long buscarNativo(String sujeto, String predicado, String objeto);

	public native void reiniciarHDT();


	public HDT(long handle){

		handler = handle;//Obtenemos el handle del fichero cargado
	}	


	public IteratorTriple buscar(String sujeto, String predicado, String objeto){

		long handle = buscarNativo(sujeto, predicado, objeto);		

		return new IteratorTriple(handle);

	}
}