package Taller2_modelo;

import java.util.ArrayList;

public class Combo implements Producto 
{

	
	 /*
	  * 
	  *    // ATRIBUTOS
	  * 
	  */
	
	private double descuento;
	private String nombreCombo;
	private ArrayList<Producto> productos = new ArrayList<Producto>();
	
	
	
	
	
	public Combo(double iddescuento, String idnombre) 
	{
		//Construye un combo
		descuento = iddescuento;
		nombreCombo = idnombre;
	}
	
	
	public void agregarItemACombo(Producto itemCombo) 
	{
		//Agrega un item al combo.
		if (itemCombo != null){
		productos.add(itemCombo);
		}
	}
	
	public int getPrecio() 
	{
		//Retorna el precio del combo recorriendo la lista de productios.
		int totalDescuentoCombo = 0;
		int totalCombo = 0;
		for(int x = 0; x<productos.size();x++) {
			
			double valorFloat = (productos.get(x).getPrecio())*descuento/100;
			
			double valorDouble = (productos.get(x).getPrecio());
			
			int valorDoub = (int)valorDouble;
			
			int valor = (int)valorFloat;
			
			totalDescuentoCombo += valor;
			
			totalCombo += valorDoub;
		}
		
		return totalCombo-totalDescuentoCombo;
	}
	
	
	

	public String getNombre() 
	{
		// Retorna el nombre del producto
		return nombreCombo;
	}

	
	
	public String generarTextoFactura() 
	{
		// Retorna una cadena con el texto para la factura
		return "$"+getPrecio()+" "+nombreCombo;
	}
	
	
	
	public String toString() 
	{
		return "Nombre: "+ nombreCombo +" Precio: "+ this.getPrecio()+ " Productos: "+productos;
	}
	
	

}
