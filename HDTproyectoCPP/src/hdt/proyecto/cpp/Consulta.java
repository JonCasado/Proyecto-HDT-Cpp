package hdt.proyecto.cpp;

import android.graphics.drawable.Drawable;

public class Consulta {
	
	protected Drawable foto;
	protected String textoCons;
	protected long id;
 
	public Consulta(Drawable foto, String _TextoCons) {
		super();
		this.foto = foto;
		this.textoCons = _TextoCons;
	}
 
	public Consulta(Drawable foto, String _TextoCons, long id) {
		super();
		this.foto = foto;
		this.textoCons = _TextoCons;
		this.id = id;
	}
 
	public Drawable getFoto() {
		return foto;
	}
 
	public void setFoto(Drawable foto) {
		this.foto = foto;
	}
 
	public String getTextoCons() {
		return textoCons;
	}
 
	public void setTextoCons(String _TextoCons) {
		this.textoCons = _TextoCons;
	}
 
	public long getId() {
		return id;
	}
 
	public void setId(long id) {
		this.id = id;
	}
}
