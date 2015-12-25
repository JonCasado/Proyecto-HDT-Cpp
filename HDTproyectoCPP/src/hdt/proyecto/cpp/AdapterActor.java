package hdt.proyecto.cpp;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterActor extends ArrayAdapter<TripleString> {

	public AdapterActor(Context context, ArrayList<TripleString> ts) {
		super(context, 0, ts);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Recogemos la posición de un determinado objeto TripleString

		TripleString ts = getItem(position);    

		// Comprobamos si ya existe alguna vista para reutilizarla, en caso contrario creamos una nueva

		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.elemento_lista_actor, parent, false);
		}

		// Recogemos los TextView donde vamos a mostrar la informacion del TripleString
		
		TextView sujeto = (TextView) convertView.findViewById(R.id.resSujeto);
		TextView objeto = (TextView) convertView.findViewById(R.id.resObjeto);

		// Introducimos la información desde el objeto al TextView
		
		sujeto.setText(ts.getSujeto());
		objeto.setText(ts.getObjeto());

		// Devolvemos la vista completa para que se muestre en pantalla
		return convertView;
	}
}