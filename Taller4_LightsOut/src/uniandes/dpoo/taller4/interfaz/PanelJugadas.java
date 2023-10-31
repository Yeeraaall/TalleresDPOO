package uniandes.dpoo.taller4.interfaz;

import java.awt.*;
import javax.swing.*;




public class PanelJugadas extends JPanel
{
	private JTextField txtJugadas;
	private JTextField txtJugador;

	
public PanelJugadas()
	{
	  setLayout(new GridLayout(1, 4, 5, 0));
	  Color customColor = new Color(60,172,194);

	  JLabel jugadas = new JLabel("Jugadas:");
	  Font fontJugadas = jugadas.getFont();
	  jugadas.setBackground(customColor);
	  
	  jugadas.setFont(fontJugadas.deriveFont(fontJugadas.getStyle() & ~Font.BOLD));
	  this.txtJugadas = new JTextField();

	  JLabel jugador = new JLabel("Jugador:");
	  jugador.setBackground(customColor);
	  Font fontJugador = jugadas.getFont();
	  jugador.setFont(fontJugador.deriveFont(fontJugador.getStyle() & ~Font.BOLD));
	  this.txtJugador = new JTextField();

	  txtJugadas.setEditable(false);
	  txtJugador.setEditable(false);
		
	  add(jugadas);
	  add(txtJugadas);
	  add(jugador);
	  add(txtJugador);
	}


	public String getJugadas()
	{
	 return txtJugadas.getText();
	}
	
	
	
	
	public String getJugador()
	{
	 return txtJugador.getText();
	}
	
	
	
	public void setJugador(String nombre)
	{
	  String nombreJugador = nombre;
	  txtJugador.setText(nombreJugador);
	}
	
	
	
	public void setJugadas(int jugadas)
	{
	  txtJugadas.setText(Integer.toString(jugadas));
	}

}
