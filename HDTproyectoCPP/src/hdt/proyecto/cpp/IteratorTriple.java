package hdt.proyecto.cpp;

import android.app.Activity;

public class IteratorTriple extends Activity {

	private long handler;

	public native boolean hasNextNativo();

	public native long nextNativo();

	public native void eliminarIterator();


	public IteratorTriple(long handle){//Constructor

		handler = handle;	
	}

	public boolean hasNext(){

		boolean var = hasNextNativo();

		return var;
	}

	public TripleString next(){

		long handle = nextNativo();

		return new TripleString(handle);
	}
}