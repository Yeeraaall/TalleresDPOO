package Taller2_procesamiento;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Taller2_modelo.Producto;
import Taller2_modelo.ProductoMenu;
import Taller2_modelo.Combo;
import Taller2_modelo.Ingrediente;
import Taller2_modelo.Pedido;

public class Restaurante {
	
	public static ArrayList<Producto> menu = new ArrayList<Producto>();
	public static ArrayList<Combo> combos = new ArrayList<Combo>();
	public static ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
	public static Pedido pedidoActual = null;
	
	
	
	
	private void cargarMenu(File archivoMenu)
	{
		
		try (BufferedReader br = new BufferedReader(new FileReader(archivoMenu))) 
		{
			String linea = br.readLine();
			while (linea != null) 
			{
				String[] precios = linea.split(";");
				String nproducto = precios[0];
				
				int valorproducto = Integer.parseInt(precios[1]);
				
				ProductoMenu agregadoproducto = new ProductoMenu(nproducto, valorproducto);
				
				menu.add(agregadoproducto);
				
				linea = br.readLine();
			}
			
		} catch (IOException e) 
		{
			System.out.println("Uy, hemos tenido problemas para cargar nuestro menu, inténtalo de nuevo.");
			e.printStackTrace();
			
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void cargarCombos(File archivoCombos) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(archivoCombos))) {
			String linea = br.readLine();
			
			while (linea != null) 
			{
				
				String[] valores = linea.split(";");
				
				String nproducto = valores[0];
				
				String stringDescuento = valores[1].replace("%", "");
				
				double ndescuento = Double.parseDouble(stringDescuento);

				Combo comboAgregado = new Combo(ndescuento, nproducto);
				
				for(int i = 2; i<valores.length; i++) 
				{
					Producto productoParaCombo = buscarProductoMenuNombre(menu, valores[i]);
					comboAgregado.agregarItemACombo(productoParaCombo);
				}
				combos.add(comboAgregado);
				linea = br.readLine();
			}
			
		}
		
		catch (IOException e) 
		{
			System.out.println("Uy, hemos tenido problemas para cargar nuestros combos, inténtalo de nuevo.");
			e.printStackTrace();	
		} 
		
		catch (NumberFormatException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void cargarIngredientes(File archivoIngredientes) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes))) {
			String linea = br.readLine();
			
			while (linea != null) 
			{
				String[] valores = linea.split(";");
				String nproducto = valores[0];
				int valorproducto = Integer.parseInt(valores[1]);
				Ingrediente agregadoingrediente = new Ingrediente(nproducto, valorproducto);
				ingredientes.add(agregadoingrediente);
				linea = br.readLine();
				
			
			}
		}
		
		catch (IOException e) 
		{
			System.out.println("Uy, hemos tenido problemas para cargar nuestros ingredientes, inténtalo de nuevo.");
			e.printStackTrace();
		} 
		
		catch (NumberFormatException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Producto buscarProductoMenuNombre(ArrayList<Producto> listaProductos, String nombre) 
	{
		Producto elProducto = null;
		for(int i = 0; i < listaProductos.size() && elProducto == null ; i ++)
		{
			Producto unProducto = listaProductos.get(i);
			String unProductoName = unProducto.getNombre();

			if(unProductoName.equals(nombre))
			{
				elProducto = unProducto; 
			}
			
		}
	return elProducto;
	}
	
	
	public ArrayList<Producto> getMenuBase()
	{
		return Restaurante.menu;
	}
	
	
	
	
	public ArrayList<Combo> getCombos()
	{
		return Restaurante.combos;
	}
	
	
	
	
	public ArrayList<Ingrediente> getIngredientes()
	{
		return Restaurante.ingredientes;
	}
	
	
	
	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) 
	{
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
	}
	
	
	
	public void iniciarPedido(String nombreCliente, String direccionCliente) throws IOException 
	{
		pedidoActual = new Pedido(nombreCliente, direccionCliente);
	}
	
	
	
	
	public void cerrarYGuardarPedido() 
	{
		pedidoActual.guardarFactura();
		
	}
	
	
	
	
	public Pedido getPedidoEnCurso() 
	{
		return this.pedidoActual;
	}
	
	
	
	
	public Restaurante() 
	{
		
		ArrayList<Producto> menu = new ArrayList<Producto>();
		ArrayList<Combo> combos = new ArrayList<Combo>();	
		ingredientes = new ArrayList<Ingrediente>();
		pedidoActual = null;
	}

	
}
