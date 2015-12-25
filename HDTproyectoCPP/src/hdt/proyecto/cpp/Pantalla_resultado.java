package hdt.proyecto.cpp;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class Pantalla_resultado extends Activity {

	//Creamos un Array List capaz de almacenar los triples provinientes de una consulta, 
	//los cuales mostraremos a traves de un ListView

	ArrayList<TripleString> arrayRes = new ArrayList<TripleString>();

	ListView listaRes;

	AdapterResultado adapter;

	HDT hdt;

	TextView errorBusq;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pantalla_resultado);

		final ApplicationHDTC globalVariable = (ApplicationHDTC) getApplicationContext();


		hdt = globalVariable.getHDT();	

		//Aqui recibimos los parámetros de la busqueda

		String sujCad = getIntent().getExtras().getString("sujeto");

		String preCad = getIntent().getExtras().getString("predicado");

		String objCad = getIntent().getExtras().getString("objeto");

		//Recogemos la lista donde vamos a mostrar los resultados

		listaRes = (ListView) findViewById(R.id.listaResultados);

		//Definimos el adaptador de los resultados

		adapter = new AdapterResultado (this,arrayRes);

		//Antes de rellenar la lista comprobamos que este vacia,
		//si no lo esta, la vaciamos

		if(adapter.isEmpty() == false)
		{
			adapter.clear();
		}			

		try{


			IteratorTriple its = hdt.buscar(sujCad,preCad, objCad);


			while(its.hasNext()) 
			{

				TripleString ts = its.next();

				//Rellenamos el arraylist con los resultados

				adapter.add(ts);

			}

			//Si el listview no esta vacio lo mostramos

			if(adapter.isEmpty() == false)
			{

				//Incorporamos la informacion en el list view

				listaRes.setAdapter(adapter);
			}
			//En caso contrario mostramos un mensaje de error
			else
			{
				errorBusq = (TextView) findViewById(R.id.errorBusqueda);

				errorBusq.setText("Los parámetros que ha introducido no producen ningún resultado.");
			}

			//Como no hay recolector de basura en C++ es necesario que llamemos a estos
			//dos metodos para eliminar los punteros a memoria que se han creado

			its.eliminarIterator();

			hdt.reiniciarHDT();		
		}	
		catch(Exception e){

			errorBusq = (TextView) findViewById(R.id.errorBusqueda);

			errorBusq.setText("Los parámetros introducidos no son válidos.");

			e.printStackTrace();
		}	
	}
}//Fin de la clase Pantalla_resultado