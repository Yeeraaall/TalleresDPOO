package uniandes.dpoo.taller4.interfaz;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;

import uniandes.dpoo.taller4.modelo.*;


public class VentanaLightsOut extends JFrame
{
	
private Tablero tableroJuego;
private File archivoTop;
private PanelOpciones panelOpciones;
	
public VentanaLightsOut()
{
   setTitle("LightsOut"); 
   setSize(720, 600); 
   setResizable(false); 
   setDefaultCloseOperation(EXIT_ON_CLOSE); 
   setLocationRelativeTo(null);
   setLayout(new BorderLayout());
   this.panelOpciones = new PanelOpciones(this);
   add(panelOpciones, BorderLayout.CENTER);
   this.tableroJuego = new Tablero(panelOpciones.getDimensiones());
   this.archivoTop = new File("data/top10.csv");
   //top10.cargarRecords(archivoTop);
	
		
}
	
	
public static void main(String[] args)
{
  VentanaLightsOut ventana = new VentanaLightsOut();		
  ventana.setVisible(true);
  FlatLightLaf.install();
}
	
public Tablero getTablero() 
{
  return tableroJuego;
}
	
	
}
