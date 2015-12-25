package hdt.proyecto.cpp;

import android.app.Application;

public class ApplicationHDTC extends Application{
	
	HDT hdt;
	
	public void setHDT(HDT biblio){
		
		this.hdt = biblio;
		
	}
	
	public HDT getHDT(){
		
		return this.hdt;
	}
}
