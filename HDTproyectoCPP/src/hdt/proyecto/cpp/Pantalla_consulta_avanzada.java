package hdt.proyecto.cpp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Pantalla_consulta_avanzada extends Activity {

	Button boton;

	EditText suj, pre, obj;

	Editable _suj,_pre,_obj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consulta_avanzada);

		boton = (Button) findViewById(R.id.botonConsulta);

		suj = (EditText) findViewById(R.id.sujeto);

		pre = (EditText) findViewById(R.id.predicado);

		obj = (EditText) findViewById(R.id.objeto);

		_suj = suj.getText();

		_pre = pre.getText();

		_obj = obj.getText();

		//Iniciamos el proceso de escucha del boton

		boton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{					
				//Aqui mandamos los parametros de la busqueda al Activity Pantalla_resultado
				//Quien se encarga de realizar la busqueda y mostrar los resultados

				String sujCad = _suj.toString();

				String preCad = _pre.toString();

				String objCad = _obj.toString();

				//Comprobamos que el usuario no haya dejado todos los campos vacios, actualmente no somos
				//capaces de recuperar los 6 millones de triples del conjunto de datos de LinkedMDB

				if(sujCad == "" && preCad == "" && objCad == "" )
				{
					Toast toast = Toast.makeText(getApplicationContext(),
							"No es posible realizar esa búsqueda", Toast.LENGTH_SHORT);
					toast.show();
				}
				else
				{
					Intent intent = new Intent(Pantalla_consulta_avanzada.this, Pantalla_resultado.class);     
					intent.putExtra("sujeto", sujCad);
					intent.putExtra("predicado", preCad);
					intent.putExtra("objeto", objCad);
					startActivity(intent);
				}
			}				
		});
	}//Fin del método onCreate

}//Fin de la clase Pantalla_consulta_avanzada