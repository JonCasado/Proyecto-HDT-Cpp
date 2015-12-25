package hdt.proyecto.cpp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class TestConsumo extends Activity {

	public static final String[] nombreBoton = { "Consultas SPO", "Consultas SP?", "Consultas S?O", "Consultas S??"};

	TextView numCons, tiempoConsulta, tiempoTotal, totalEjecuciones;

	InputStream flujo = null;

	BufferedReader lector;

	HDT hdt;

	ArrayList<TripleString> arrayConsultas = new ArrayList<TripleString>();

	int numEjecuciones=0;

	long tTotalConsultas=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_consumo);

		//Obtenemos el fichero HDT cargado en memoria

		final ApplicationHDTC globalVariable = (ApplicationHDTC) getApplicationContext();

		hdt = globalVariable.getHDT();

		numCons = (TextView) findViewById(R.id.numConsultas);

		tiempoConsulta = (TextView) findViewById(R.id.tiempoConsulta);

		tiempoTotal = (TextView) findViewById(R.id.tiempoTotal);

		totalEjecuciones = (TextView) findViewById(R.id.totEje);

		//Asociamos el Gridview definido en nuestro layout
		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new AdapterTest(this));	

	}//Fin del método onCreate


	public class AdapterTest extends BaseAdapter {


		private Context mContext;

		// Se recoge el contexto en el que estamos actualmente
		public AdapterTest(Context c) {

			mContext = c;

		}

		// Numero de objetos que contiene el array de nombres de los botones
		public int getCount() {

			return nombreBoton.length;

		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			Button btn;

			if (convertView == null) {
				// Si no reciclamos la vista, inicializamos algunos atributos
				btn = new Button(mContext);

				btn.setPadding(8, 8, 8, 8);

			} else {
				btn = (Button) convertView;
			}
			// nombreBoton es un array con los nombres de los botones
			btn.setText(nombreBoton[position]);

			// definimos diferentes atributos para los botones
			btn.setTextSize(16);
			btn.setAlpha(1);
			btn.setId(position);
			//asociamos un listener a cada boton para recibir los clicks
			btn.setOnClickListener(new MyOnClickListener(position));

			return btn;
		}
	}

	//Clase listener necesaria para recoger cual ha sido el boton pulsado

	class MyOnClickListener implements OnClickListener {

		private final int position;

		public MyOnClickListener(int position) {
			this.position = position;
		}

		//A continuacion definiremos las acciones que se realizaran cuando se pulse
		//algun boton

		public void onClick(View v) {

			if (this.position==0){

				//Una vez recogemos cual es el boton pulsado, 
				//indicamos cual es el fichero que queremos leer

				flujo = getResources().openRawResource(R.raw.linkedmdb_spo_500);

				/*Y se lo enviamos al metodo leerFichero, lo hacemos asi para
				 *No ensuciar el tiempo que se tarda en realizar la consulta añadiendo 
				 *evitando el tiempo de lectura de disco que se realiza cuando leemos el fichero
				 *de esta forma obtenemos unos tiempos mas reales 
				 */		
				leerFichero(flujo);

				//Ahora que el array de consultas ya esta lleno, podemos ejecutar las pruebas

				ejecutarPruebas();

				numEjecuciones = numEjecuciones + 1;

				totalEjecuciones.setText("Numero total de ejecuciones: " + numEjecuciones);

			}
			else if (this.position==1)
			{
				flujo = getResources().openRawResource(R.raw.linkedmdb_spv_500);

				leerFichero(flujo);

				ejecutarPruebas();

				numEjecuciones = numEjecuciones + 1;

				totalEjecuciones.setText("Numero total de ejecuciones: " + numEjecuciones);

			}
			else if (this.position==2)
			{
				flujo = getResources().openRawResource(R.raw.linkedmdb_svo_500);

				leerFichero(flujo);

				ejecutarPruebas();

				numEjecuciones = numEjecuciones + 1;

				totalEjecuciones.setText("Numero total de ejecuciones: " + numEjecuciones);

			}
			else if (this.position==3)
			{
				flujo = getResources().openRawResource(R.raw.linkedmdb_svv_500);

				leerFichero(flujo);

				ejecutarPruebas();

				numEjecuciones = numEjecuciones + 1;

				totalEjecuciones.setText("Numero total de ejecuciones: " + numEjecuciones);

			}

		}//Fin del método onClick

	}//Fin de la clase MyOnClickListener	

	public void leerFichero(InputStream flujo){

		try
		{
			lector = new BufferedReader(new InputStreamReader(flujo));

			String sujeto="", predicado="", objeto="";

			//Leemos la primera linea del fichero, con lo que iniciamos el proceso

			String texto = lector.readLine();

			while(texto!=null)
			{		
				/*Creamos un objeto de este tipo para ser capaces de dividir las lineas 
				 * del codigo respecto a los delimitadores que contiene el fichero de consultas
				 */

				StringTokenizer delimitador = new StringTokenizer(texto,";");

				sujeto = delimitador.nextToken().trim();

				predicado = delimitador.nextToken().trim();

				objeto = delimitador.nextToken().trim();

				//Una vez hemos dividido la linea que se acaba de leer, comprobamos que ninguno de
				//los parametros sea cero

				if(sujeto.equals("0"))
				{
					sujeto = "";
				}
				if(predicado.equals("0"))
				{
					predicado = "";
				}
				if(objeto.equals("0"))
				{
					objeto = "";
				}


				//Una vez realizado esto, podemos crar un nuevo objeto tipo Triple String, en el
				//almacenamos el sujeto, el predicado y el objeto en un objeto de tipo TripleString
				//Y guardamos sus datos en el ArrayList para leerlo posteriormente

				try{

					TripleString ts = new TripleString();

					//Almacenamos en este nuevo TripleString los valores leidos

					ts.setSujeto(sujeto);

					ts.setPredicado(predicado);

					ts.setObjeto(objeto);

					arrayConsultas.add(ts);

				}
				catch(Exception e){

					e.printStackTrace();
				}			            

				//Una vez añadido el objeto al array, pasamos a la siguiente línea y 
				//el programa la leera si existe 


				texto=lector.readLine();


			}

		}//Fin del bucle de lectura

		catch (Exception ex)

		{
			ex.printStackTrace();

		}    
		finally
		{
			try {
				if(flujo!=null)
				{
					flujo.close();
				}
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}	

	}//Fin del método leerFichero


	@SuppressWarnings("unused")
	public void ejecutarPruebas(){

		//Definimos las variables que mediran el tiempo que tardaran en 
		//realizarse las consultas y los Strings que recogeran la informacion 
		//del TripleString

		long time_start, time_end, tiempoCons;

		String sujetoConsulta = "", predicadoConsulta = "", objetoConsulta = "";

		//Una vez leido el ArrayList esta lleno con todas las consultas a realizar
		//A continuación tenemos que recorrerlo e ir realizando las consultas


		//Comenzamos a medir el tiempo con la lectura del primer objeto del Array
		time_start = System.currentTimeMillis();

		//Variable con la que contaremos el numero de veces que realizamos una consulta
		int contador = 0;

		for(int i = 0;i < arrayConsultas.size();i++)
		{
			sujetoConsulta = arrayConsultas.get(i).getSujeto();

			predicadoConsulta = arrayConsultas.get(i).getPredicado();

			objetoConsulta = arrayConsultas.get(i).getObjeto();

			try{

				IteratorTriple its = hdt.buscar(sujetoConsulta,predicadoConsulta, objetoConsulta);

				while(its.hasNext()) 
				{

					TripleString ts = its.next();

					contador++;

				}

				its.eliminarIterator();

				hdt.reiniciarHDT();

			}
			catch(Exception e){
				e.printStackTrace();
			}

		}

		//Paramos el tiempo y recogemos el resultado

		time_end = System.currentTimeMillis();

		//Calculamos el tiempo en milisegundos

		tiempoCons = time_end - time_start;

		//Mostramos los resultados

		numCons.setText(contador + " consultas realizadas");

		tiempoConsulta.setText(tiempoCons + " milisegundos");

		tTotalConsultas = tTotalConsultas + tiempoCons;

		tiempoTotal.setText("Tiempo total de todas las consultas: " + tTotalConsultas + " ms");

		//Para finalizar con este método vaciamos el arrayConsultas y de esta forma evitamos 
		//que se mezclen consultas y generamos tiempos mas reales 

		arrayConsultas.clear();

	}//Fin del metodo ejecutarPruebas

}//Fin de la clase TestConsumo