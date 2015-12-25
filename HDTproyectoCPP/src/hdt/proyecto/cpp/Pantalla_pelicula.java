package hdt.proyecto.cpp;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Pantalla_pelicula extends Activity {

	ArrayList<TripleString> arrayPel = new ArrayList<TripleString>();

	ListView listaPel;	

	AdapterPelicula adapter;

	HDT hdt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pantalla_peliculas);

		final ApplicationHDTC globalVariable = (ApplicationHDTC) getApplicationContext();


		hdt = globalVariable.getHDT();


		listaPel = (ListView)findViewById(R.id.listaPeliculas);

		//Antes de rellenar la lista comprobamos que este vacia,
		//si no lo esta, la vaciamos

		//Hay que cambiarlo

		if(arrayPel.isEmpty() == false)
		{
			arrayPel.clear();
		}

		try{

			String preCad = "http://purl.org/dc/terms/title";		

			IteratorTriple its = hdt.buscar("",preCad, "");


			while(its.hasNext()) 
			{

				TripleString ts = its.next();

				//Rellenamos el arraylist con los resultados

				arrayPel.add(ts);

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

		adapter = new AdapterPelicula (this,arrayPel);

		//Incorporamos la informacion en el list view

		listaPel.setAdapter(adapter);

		//Cuando el usuario pulsa sobre una pelicula que aparece en la lista, se enviará 
		//la pelicula elegida a la clase Pantalla_resultado y saldra por pantalla toda
		//la informacion que rodea a dicha pelicula

		listaPel.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {

				String sujetoPeli = arrayPel.get(posicion).getSujeto(); 

				Intent intent = new Intent(Pantalla_pelicula.this, Pantalla_resultado.class);     
				intent.putExtra("sujeto", sujetoPeli);
				intent.putExtra("predicado", "");
				intent.putExtra("objeto", "");
				startActivity(intent);

			}
		});//Fin de la espera de esucha
	}
}//Fin de la clase Pantalla_pelicula