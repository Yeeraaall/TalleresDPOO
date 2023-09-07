package Taller2_modelo;

public class Ingrediente {
	
	
	 /*
	  * 
	  *    // ATRIBUTOS
	  * 
	  */
	private String nombre;
	private int costoAdicional;
	
	
	
	
	
	
	public Ingrediente(String idnombre, int idcosto) 
	{
		nombre = idnombre;
		costoAdicional = idcosto;
		
	}
	
	
	public String getNombre() 
	{
		return nombre;
	}
	
	
	public int getCostoAdicional() 
	{
		return costoAdicional;
	}
	
	
	
	
}
