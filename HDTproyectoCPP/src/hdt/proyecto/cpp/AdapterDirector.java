package hdt.proyecto.cpp;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class AdapterDirector extends BaseAdapter{

	protected Activity activity;
	protected ArrayList<TripleString> items;

	public AdapterDirector(Activity activity, ArrayList<TripleString> items) {
		this.activity = activity;
		this.items = items;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int arg0) {
		return items.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return items.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Generamos una convertView por motivos de eficiencia
		View v = convertView;

		//Asociamos el layout de la lista que hemos creado
		if(convertView == null){
			LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inf.inflate(R.layout.elemento_lista_director, null);
		}

		// Creamos un objeto TripleString
		TripleString ts = items.get(position);
		//Rellenamos el nombre del director
		TextView txtP = (TextView) v.findViewById(R.id.nombDirector);
		txtP.setText(ts.getObjeto());

		// Retornamos la vista
		return v;
	}
}