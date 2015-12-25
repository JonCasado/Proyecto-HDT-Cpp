package hdt.proyecto.cpp;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Pantalla_inicio extends Activity {

	//Cargamos la librería nativa que contiene la implementación del código nativo

	static {

		System.loadLibrary("hdt-jni");
	}

	//Lista de consultas que se podran realizar en la aplicacion,
	//será la primera imagen que vea el usuario

	ListView lista;

	//TextView que aparecerá en caso de que no se haya cargado correctamente el fichero HDT

	TextView error;

	//ArrayList que contendra los tipos de consulta que podra realizar nuestra aplicación

	ArrayList<Consulta> arrayCon = new ArrayList<Consulta>();

	//Objeto de la clase consulta
	Consulta cons;

	//Variable que comprueba si se ha cargado o no el fichero en formato HDT

	boolean cargarCorrecto;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pantalla_inicio);

		//Llamamos al método y comprobamos si el fichero se ha cargado correctamente o no y en 
		//funcion de ello mostramos el menu o un mensaje de error

		cargarCorrecto = cargarFicheroHDT();

		if(cargarCorrecto == false)
		{
			error = (TextView) findViewById(R.id.textoError);

			error.setText("Compruebe que el conjunto de datos se encuenta en la ruta (/mnt/sdcard/Download/) y que tiene como nombre linkedmdb2010.hdt");
		}
		else
		{
			//Encontramos la lista donde queremos mostrar los tipos de consulta

			lista = (ListView) findViewById(R.id.listaConsultas);

			// Introducimos la informacion de los elementos de la lista
			cons = new Consulta(getResources().getDrawable(R.drawable.pelicula), "Consulta Peliculas");
			arrayCon.add(cons);
			cons = new Consulta(getResources().getDrawable(R.drawable.christian_bale), "Consulta Actores");
			arrayCon.add(cons);
			cons = new Consulta(getResources().getDrawable(R.drawable.steven), "Consulta Directores");
			arrayCon.add(cons);
			cons = new Consulta(getResources().getDrawable(R.drawable.consulta_avz), "Consulta Avanzada");
			arrayCon.add(cons);
			cons = new Consulta(getResources().getDrawable(R.drawable.bateria), "Realizar Pruebas");
			arrayCon.add(cons);

			// Creamos un objeto del tipo adapter para 
			//personalizar la salida de nuestro listado de consultas

			AdapterConsulta adapter = new AdapterConsulta(this, arrayCon);

			// Lo aplicamos

			lista.setAdapter(adapter);

			//Establecemos que al pusar un item del list view nos lleve a otro activity donde
			//podamos ejecutar la consulta

			lista.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {

					switch( posicion )
					{
					case 0:  Intent newActivity0 = new Intent(Pantalla_inicio.this, Pantalla_pelicula.class);     
					startActivity(newActivity0);
					break;

					case 1:  Intent newActivity1 = new Intent(Pantalla_inicio.this, Pantalla_actor.class);     
					startActivity(newActivity1);
					break;

					case 2:  Intent newActivity2 = new Intent(Pantalla_inicio.this, Pantalla_director.class);     
					startActivity(newActivity2);
					break;

					case 3:  Intent newActivity3 = new Intent(Pantalla_inicio.this, Pantalla_consulta_avanzada.class);     
					startActivity(newActivity3);
					break;

					case 4:  Intent newActivity4 = new Intent(Pantalla_inicio.this, TestConsumo.class);     
					startActivity(newActivity4);
					break;

					}
				}
			});//Fin de la espera de esucha
		}

	}//Fin del método onCreate

	@SuppressLint("SdCardPath")
	private boolean cargarFicheroHDT(){

		/*Creamos un objeto de la clase aplication al que pasaremos el conjunto de datos cargado
		 *si lo encontramos en la memoria del movil*/

		final ApplicationHDTC globalVariable = (ApplicationHDTC) getApplicationContext();

		try{

			HDTManager loader = new HDTManager();

			HDT hdt = loader.cargarHDT("/mnt/sdcard/Download/linkedmdb2010.hdt");

			/*si llegamos hasta este punto significará que la aplicacion ha encontrado y cargado
			 *el fichero y por tanto podremos hacer un "set" del fichero cargado a la clase 
			 *ApplicationHDTC*/

			globalVariable.setHDT(hdt);

			return true;

		}
		catch(Exception noHayFichero){

			//Cuando entramos aqui implica que la aplicacion no ha encontrado el fichero
			//por lo que en vez de gestionar la exception retornamos un false para que envie un
			//mensaje de error

			Toast toast = Toast.makeText(getApplicationContext(),
					"¡Fichero NO cargado!", Toast.LENGTH_LONG);	    	 
			toast.show();

			return false;
		}	

	}//Fin del método cargarFicheroHDT

}//Fin de la clase Pantalla_inicio