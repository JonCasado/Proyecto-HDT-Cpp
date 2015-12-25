package hdt.proyecto.cpp;

public class HDTManager {

	private long handler;//Sirve para decirle al handle que es esta clase quien va a utilizar sus propiedades para usar objetos entre Java y C++

	//Método nativo en el que la librería se cargará en C++ y esta devolverá un puntero (long) a Java con los parámetros cargados

	private native long cargarHDTNativo(String file);

	public HDTManager(){}//Constructor sin parametrizar

	//Este método es explicito para que la clase HDT posteriormente pueda utilizar el puntero (long) que nos devuelve el handle una vez cargada la librería
	public HDT cargarHDT (String file){

		long handle = cargarHDTNativo(file);

		return new HDT(handle);	

	}
}