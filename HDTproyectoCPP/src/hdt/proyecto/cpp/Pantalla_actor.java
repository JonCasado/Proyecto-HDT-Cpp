package hdt.proyecto.cpp;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class Pantalla_actor extends Activity {

	ArrayList<TripleString> arrayAct = new ArrayList<TripleString>();

	ListView listaAct;

	AdapterActor adapter;

	HDT hdt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pantalla_actor);

		final ApplicationHDTC globalVariable = (ApplicationHDTC) getApplicationContext();


		hdt = globalVariable.getHDT();

		//Recogemos la lista donde vamos a mostrar los resultados

		listaAct = (ListView) findViewById(R.id.listaActores);

		//Definimos el adaptador de los resultados

		adapter = new AdapterActor (this,arrayAct);

		//Antes de rellenar la lista comprobamos que este vacia,
		//si no lo esta, la vaciamos

		if(adapter.isEmpty() == false)
		{
			adapter.clear();
		}


		//Antes de comenzar con la busqueda, comprobamos que el iterador no contenga los datos
		//de una busqueda anterior, de no ser asi lo eliminamos, debido a que como se trata  
		//de C++ el recolector de basura no es automatico, y por lo tanto si deseamos  
		//realizar otra consulta debemos hacerlo.


		try{

			String preCad = "http://data.linkedmdb.org/resource/movie/actor_name";

			IteratorTriple its = hdt.buscar("",preCad, "");


			while(its.hasNext()) 
			{

				TripleString ts = its.next();

				//Rellenamos el arraylist con los resultados

				adapter.add(ts);

			}

			//Incorporamos la informacion en el list view

			listaAct.setAdapter(adapter);

			//Como no hay recolector de basura en C++ es necesario que llamemos a estos
			//dos metodos para eliminar los punteros a memoria que se han creado

			its.eliminarIterator();

			hdt.reiniciarHDT();

		}

		catch(Exception e){

			e.printStackTrace();
		}
	}//Fin del método onCreate
}//Fin de la clase Pantalla_actor