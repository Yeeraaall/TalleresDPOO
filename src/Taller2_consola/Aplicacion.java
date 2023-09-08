package Taller2_consola;
import Taller2_modelo.Producto;
import Taller2_modelo.ProductoAjustado;
import Taller2_modelo.Combo;
import Taller2_modelo.Ingrediente;
import Taller2_procesamiento.Restaurante;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


 /*
  * 
  *    // ATRIBUTOS
  * 
  */

public class Aplicacion{
	
	static Restaurante restaurante = new Restaurante();
	
	static HashMap<Integer, Producto> menuProductos = new HashMap<Integer, Producto>();
	
	static HashMap<Integer, Ingrediente> menuIngredientes = new HashMap<Integer, Ingrediente>();
	
	
	
	public static void mostrarMenu()
	{
		
		System.out.println("1. Mostrar menú");
		System.out.println("2. Iniciar nuevo pedido");
		System.out.println("3. Agregar un elemento al pedido");
		System.out.println("4. Cerrar pedido y guardar la factura");
		System.out.println("5. Consultar factura con su id");
		System.out.println("6. Cerrar aplicacion");
		
	}
	
	public static String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	public static void ejecutarAplicacion()
	{
		System.out.println("¡Bienvenido querido cliente!");
		ejecutarCargaInformacion();
		boolean continuar = true;
		while (continuar)
		{
			
			try 
			{
				mostrarMenu();
				int opcion = 0;
				try 
				{
					opcion = Integer.parseInt(input("Ingresa una opción para continuar "));
				} catch (Exception e) {
					System.out.println("Por favor selecciona una opción válida");
					e.printStackTrace();
				}
				
				if (opcion == 1) 
				{
					ejecutarMostrarMenuRestaurante();
				}
				else if (opcion == 2)
				{
					ejecutarNuevoPedido();
					System.out.println("Se ha iniciado el pedido exitosamente");
				}
				else if (opcion == 3){
					ejecutarAgregarProducto();
				}
				else if (opcion == 4) {
					ejecutarCerrarYGuardarPedido();
				}
				else if (opcion == 5) {
					ejecutarConsultarFacturaId();
				}
				else if (opcion == 6) {
					continuar = false;
				}
				}catch (Exception e)
				{
					System.out.println("Termina de crear tu pedido, por favor");
				}}
				
		}
	
	public static void ejecutarCargaInformacion() {
		
		File archivoIngredientes = new File("./data/ingredientes.txt");
		
		File archivoMenu = new File("./data/menu.txt");
		
		File archivoCombo = new File("./data/combos.txt");
		
		restaurante.cargarInformacionRestaurante( archivoIngredientes, archivoMenu, archivoCombo);
		
		ArrayList<Producto> menuRestaurante = restaurante.getMenuBase();
		
		ArrayList<Combo> menuCombos = restaurante.getCombos();
		
		ArrayList<Ingrediente> menuIngrediente = restaurante.getIngredientes();
		
		for(int i = 0; i < menuIngrediente.size(); i++) 
		{
			Ingrediente unIngrediente = menuIngrediente.get(i);
			menuIngredientes.put(i, unIngrediente);
		}
		
		int conteoMenu = 1;
		for(int i = 0; i < menuRestaurante.size(); i++) 
		{
			Producto unProducto = menuRestaurante.get(i);
			menuProductos.put(conteoMenu ,unProducto);
			conteoMenu ++;
		}
		
		for(int i = 0; i < menuCombos.size(); i++) 
		{
			Combo unCombo = menuCombos.get(i);
			menuProductos.put(conteoMenu, unCombo);
			conteoMenu ++;
			}
		}
	
	
	
	
	//Ejecutar methods 
	
	public static void ejecutarMostrarMenuRestaurante() {
		
		menuProductos.size();
		
		int conteoMenu = 1;
		System.out.println("Menú");
		for(int i = 0; i < menuProductos.size(); i++) {
			Producto unProducto = menuProductos.get(i);
			if (unProducto != null) {
			String nombreProducto = unProducto.generarTextoFactura();
			
			System.out.println(conteoMenu+". " + nombreProducto);
			conteoMenu ++;}}
	}
	
	
	
	
	public static void ejecutarNuevoPedido() throws IOException {
		String nombreCliente = input("Ingresa el nombre del cliente: ");
		String direccionCliente = input("Ingresa la direccion del cliente");
		restaurante.iniciarPedido(nombreCliente, direccionCliente);
		}
	
	public static void ejecutarAgregarProducto() throws NumberFormatException {
		
		int ingreseProductoAgregar = Integer.parseInt(input("Ingresa el codigo del producto que deseas agregar : "));
		int deseaModificarProducto = Integer.parseInt(input("¿Deseas modificar tu producto?\n (1 Si - 0  No)"));
		
		Producto productoAAgregar = (Producto) menuProductos.get(ingreseProductoAgregar);
		if(deseaModificarProducto == 1 && deseaModificarProducto <= 22){
			ProductoAjustado productoModAAgregar = new ProductoAjustado(productoAAgregar);
			int modificarProducto = Integer.parseInt(input("Ingresa la opcion (1 Agregar - 2 Eliminar - 3 Finalizar)"));
			while(modificarProducto != 3) {
				
				for (int x = 0; x < menuIngredientes.size(); x++) {
					Ingrediente unIngrediente = (Ingrediente) menuIngredientes.get(x);
					System.out.println(x+". "+unIngrediente.getNombre());
				}
				
				if(modificarProducto == 1) {
					Ingrediente ingredienteMod = menuIngredientes.get(Integer.parseInt(input("Ingresa el ingrediente a agregar : ")));
					productoModAAgregar.agregarIngrediente(ingredienteMod);
				}
				else if(modificarProducto == 2) {
					Ingrediente ingredienteMod = menuIngredientes.get(Integer.parseInt(input("Ingresa el ingrediente a eliminar : ")));
					productoModAAgregar.eliminarIngrediente(ingredienteMod);
				}
				else {
					System.out.println("Ingresa una opcion válida por favor");
				}
				modificarProducto = Integer.parseInt(input("Ingrese la opcion (1 Agregar - 2 Eliminar - 3 Finalizar)"));
				}
			restaurante.getPedidoEnCurso().agregarProducto(productoModAAgregar);

		}else if(deseaModificarProducto == 0){
			restaurante.getPedidoEnCurso().agregarProducto(productoAAgregar);
		}else
		{
			System.out.println("Ingresa una opcion valida (Recuerda que nuestros combos no se pueden modificar)");
		}
	}
	
	public static void ejecutarCerrarYGuardarPedido() 
	{
		restaurante.cerrarYGuardarPedido();
	}
	
	public static void ejecutarConsultarFacturaId() throws IOException {
		
		String texto = "";
		
		String nombreArchivo = input("Ingresa el id de tu factura");
		
		File archivo = new File ("./data/Facturas/"+nombreArchivo+".txt");

		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			String linea = br.readLine();
			
			while (linea != null) {
				
				texto += linea +"\n";
				
				linea = br.readLine();
				
			}	
			System.out.println(texto);
				
		} catch (FileNotFoundException e) {
			System.out.println("No encontramos una factura con ese ID :( ");
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		ejecutarAplicacion();
	}
	
}
	