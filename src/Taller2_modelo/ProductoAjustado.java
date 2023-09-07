package Taller2_modelo;

import java.util.ArrayList;

public class ProductoAjustado implements Producto{
	
	private int precioBase;
	
	private String nombre;
	
	private ArrayList<Ingrediente> Adiciones = new ArrayList<Ingrediente>();
	
	private ArrayList<Ingrediente> Eliminaciones = new ArrayList<Ingrediente>();
	
	public ProductoAjustado(Producto base) 
	{
		precioBase = base.getPrecio();
		nombre= base.getNombre() + " modificado ";
	}
	
	public void agregarIngrediente(Ingrediente adicion) 
	{
		
		if (Eliminaciones.contains(adicion)) 
		{
			int i = Eliminaciones.indexOf(adicion);
			Eliminaciones.remove(i);
			
		}
		else if (!Adiciones.contains(adicion)) 
		{
			
			Adiciones.add(adicion);			
		}

	}
	
	public void eliminarIngrediente(Ingrediente eliminacion) 
	{

		if (Adiciones.contains(eliminacion)) 
		{
			int i = Eliminaciones.indexOf(eliminacion);
			Eliminaciones.remove(i);
			
		}
		else if (!Eliminaciones.contains(eliminacion)) 
		{
			Eliminaciones.add(eliminacion);
		}
	}
	
	public int getPrecio()
	{
		//Suma las adiciones
		int precio = precioBase;
		for(int x=0; x<Adiciones.size(); x++) {
			precio += Adiciones.get(x).getCostoAdicional();
		}
		return precio;
	}
	
	
	
	
	
	

	@Override
	public String getNombre() 
	{
		//Retorna el nombre del producto.
		return nombre;
	}

	@Override
	
	
	
	
	public String generarTextoFactura() 
	{
		
		String Adi = "Adicion de ";
		String sinx = "Sin ";
		String final1 = "";
		if (Adiciones.size()>0) 
		{
			for(int x=0; x<Adiciones.size(); x++) 
			{
				Adi += (Adiciones.get(x).getNombre());
				if ((Adiciones.size()!= 1) || (Adiciones.size()!= x))
				{
					Adi+= ", ";
				}
			}
		}
		if (Eliminaciones.size()>0) {
			for(int x=0; x<Eliminaciones.size();x++) {
				sinx+= (Eliminaciones.get(x).getNombre());
				if ((Eliminaciones.size()!= 1) || (Eliminaciones.size()!= x)){
					sinx += ", ";
				}
			}
		}
		if ((Adiciones.size()>0) || (Eliminaciones.size()>0)) {
			
			final1 = Adi+"/"+ sinx;
		}
		else if(Adiciones.size()>0) {
			final1= Adi;
			
		}
		else {
			final1 = sinx;
		}
		
		return "$"+getPrecio()+" "+getNombre()+"("+final1+")";
	}
	
	
}
