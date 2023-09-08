package Taller2_modelo;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Pedido {
	
	/*
	  * 
	  *    // ATRIBUTOS
	  * 
	  */
	private int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido = new ArrayList<Producto>();
	
	
	
	
	public Pedido(String idnombreCliente, String iddireccionCliente) throws IOException 
	{
		nombreCliente = idnombreCliente;
		direccionCliente = iddireccionCliente;
		BufferedReader br = new BufferedReader(new FileReader("./data/nPedidos.txt"));
		String linea = br.readLine(); 
		numeroPedidos = Integer.parseInt(linea);
		idPedido = numeroPedidos+1;
		
	}
	
	

	public int getIdPedido() 
	{
		return this.idPedido;
	}
	
	public void agregarProducto(Producto nuevoItem) 
	{
		itemsPedido.add(nuevoItem);
	}
	
	
	private int getPrecioNetoPedido() 
	{
		int totalneto = 0;
		for(int i=0; i<itemsPedido.size(); i++)
		{
			totalneto += itemsPedido.get(i).getPrecio();
		}
		return totalneto;
	}
	
	
	private int getPrecioIVAPedido() 
	{
		
		int precioIVA = getPrecioNetoPedido()*19/100;
		
		return precioIVA;
	}
	
	private int getPrecioTotalPedido() 
	{
		
		int totalpedido = getPrecioIVAPedido()+ getPrecioNetoPedido();
		
		return totalpedido;
     }
	
	
	
	private ArrayList<String> generarTextoFactura() 
	{
		
		ArrayList<String> texto = new ArrayList<String>();
		texto.add("Restaurante\n");
		texto.add("Pedido: "+getIdPedido()+"\n");
		texto.add("Cliente: "+this.nombreCliente+"\n");
		texto.add("Direccion: "+this.direccionCliente+"\n");
		texto.add("--------Productos--------\n");
		for(int i=0; i<itemsPedido.size(); i++) {
			texto.add(itemsPedido.get(i).generarTextoFactura()+"\n");
			}
		texto.add("Total neto: "+getPrecioNetoPedido()+"\n");
		texto.add("IVA: "+getPrecioIVAPedido()+"\n");
		texto.add("Total: "+getPrecioTotalPedido()+"\n");
		return texto;
	}
	
	
	
	public void guardarFactura()
	{
		try{
		File archivo = new File("./data/Facturas/"+getIdPedido()+".txt");
		FileWriter escritor = new FileWriter(archivo);
		
		ArrayList<String> texto = generarTextoFactura();
		
		for(int x = 0; x < texto.size(); x++) {
		escritor.write(texto.get(x));
		}
		escritor.close();
		File conteo = new File("./data/nPedidos.txt");
		FileWriter escritor2 = new FileWriter(conteo);
		escritor2.write(this.idPedido);
		escritor2.close();
		}
		catch(IOException e) {
			System.out.println("No hemos podido guardar tu factura :( ");
			e.printStackTrace();
		}
		
	 }
	
	
	
	
	public String toString() 
	{
		return idPedido +" "+ nombreCliente +" "+ direccionCliente +" "+ itemsPedido;
	}
		
}
	