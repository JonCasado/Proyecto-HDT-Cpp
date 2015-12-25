package hdt.proyecto.cpp;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Pantalla_director extends Activity {
	
	ArrayList<TripleString> arrayDirector = new ArrayList<TripleString>();

	ListView listaDir;	

	AdapterDirector adapter;
	
	HDT hdt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pantalla__director);

		final ApplicationHDTC globalVariable = (ApplicationHDTC) getApplicationContext();


		hdt = globalVariable.getHDT();


		listaDir = (ListView)findViewById(R.id.listaDirectores);

		//Antes de rellenar la lista comprobamos que este vacia,
		//si no lo esta, la vaciamos

		if(arrayDirector.isEmpty() == false)
		{
			arrayDirector.clear();
		}

		try{

			String preCad = "http://data.linkedmdb.org/resource/movie/director_name";		

			IteratorTriple its = hdt.buscar("",preCad, "");


			while(its.hasNext()) 
			{

				TripleString ts = its.next();

				//Rellenamos el arraylist con los resultados

				arrayDirector.add(ts);

			}

			//Como no hay recolector de basura en C++ es necesario que llamemos a estos
			//dos metodos para eliminar los punteros a memoria que se han creado

			its.eliminarIterator();

			hdt.reiniciarHDT();

		}

		catch(Exception e){

			e.printStackTrace();
		}

		//Ahora que tenemos lleno el array, definimos el adaptador de los resultados

		adapter = new AdapterDirector (this,arrayDirector);

		//Incorporamos la informacion en el list view

		listaDir.setAdapter(adapter);

		//Cuando el usuario pulse sobre un director, el resultado de la acción provocara
		//en la aplicación que esta muestre las peliculas que ha dirigido el director elegido


		listaDir.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {

				String sujetoDirector = arrayDirector.get(posicion).getSujeto();

				String predicadoDirector = "http://xmlns.com/foaf/0.1/made";

				Intent intent = new Intent(Pantalla_director.this, Pantalla_resultado_director.class);     
				intent.putExtra("sujeto", sujetoDirector);
				intent.putExtra("predicado", predicadoDirector);
				intent.putExtra("objeto", "");
				startActivity(intent);

			}
		});//Fin de la espera de esucha
	}
}//Fin de la clase Pantalla_director