package hdt.proyecto.cpp;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class Pantalla_resultado_director extends Activity {

	ArrayList<TripleString> peliculasDirigidas = new ArrayList<TripleString>();

	ArrayList<TripleString> nombrePeliculas = new ArrayList<TripleString>();

	ListView listaNomPel;

	AdapterResultadoDirector adapter;

	HDT hdt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pantalla_resultado_director);

		final ApplicationHDTC globalVariable = (ApplicationHDTC) getApplicationContext();


		hdt = globalVariable.getHDT();


		//Aqui recibimos los parámetros de la busqueda y la realizamos
		//lo adaptamos para mostrarlo en un list view

		String sujCad = getIntent().getExtras().getString("sujeto");

		String preCad = getIntent().getExtras().getString("predicado");

		String objCad = getIntent().getExtras().getString("objeto");

		//Recogemos la lista donde vamos a mostrar los resultados

		listaNomPel = (ListView) findViewById(R.id.listaResDirector);

		try{


			IteratorTriple its = hdt.buscar(sujCad,preCad, objCad);


			while(its.hasNext()) 
			{

				TripleString ts = its.next();

				//Rellenamos el arraylist con los resultados

				peliculasDirigidas.add(ts);

			}

			its.eliminarIterator();

			hdt.reiniciarHDT();

		}

		catch(Exception e){

			e.printStackTrace();
		}

		//Ahora que hemos obtenido las peliculas dirigidas por el director
		//que hemos seleccionado anteriormente, pasamos a mostrar el nombre
		//de dichas peliculas


		for(int i=0; i<peliculasDirigidas.size();i++)
		{

			//Utilizamos el identificador unico de la pelicula como sujeto, para asi
			//poder conseguir su nombre

			String sujeto = peliculasDirigidas.get(i).getObjeto();

			String predicado = "http://purl.org/dc/terms/title";

			try
			{

				IteratorTriple its = hdt.buscar(sujeto,predicado, "");


				while(its.hasNext()) 
				{

					TripleString ts = its.next();

					//Rellenamos el arraylist con los resultados

					nombrePeliculas.add(ts);

				}

				//Como no hay recolector de basura en C++ es necesario que llamemos a estos
				//dos metodos para eliminar los punteros a memoria que se han creado

				its.eliminarIterator();

				hdt.reiniciarHDT();

			}
			catch(Exception e){

				e.printStackTrace();
			}
		}//Fin del bucle for

		//Ahora que tenemos lleno el array, definimos el adaptador de los resultados

		adapter = new AdapterResultadoDirector (this,nombrePeliculas);

		//Incorporamos la informacion en el list view

		listaNomPel.setAdapter(adapter);
	}
}//Fin de la clase Pantalla_resultado_director