package uniandes.dpoo.taller4.interfaz;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class PanelDificultad extends JPanel implements ActionListener
{
	
public final static String FACIL = "2";
public final static String MEDIO = "5";
public final static String DIFICIL = "10";
private int valorDificultad = 2;
private int valorTamanio = 3;

	
public PanelDificultad()
{
   setLayout(new FlowLayout());
   setPreferredSize(new Dimension(0, 35));
   setBackground(new Color(42, 137, 224));
   JLabel tamanio = new JLabel("Tamaño:");
   JLabel dificultad = new JLabel("Dificultad:");
   tamanio.setForeground(Color.WHITE);
   dificultad.setForeground(Color.WHITE);
	
   
   
   String[] opciones = { "3x3", "4x4", "5x5", "6x6", "7x7", "8x8", "9x9", "10x10" };
   JComboBox<String> listaTamanios = new JComboBox<String>(opciones);
   listaTamanios.setFont(listaTamanios.getFont().deriveFont(listaTamanios.getFont().getStyle() & ~Font.BOLD));
   
   
   
   
   
   listaTamanios.addActionListener(new ActionListener()
   {
	 public void actionPerformed(ActionEvent e)
	 {
		 valorTamanio = Integer.valueOf(listaTamanios.getSelectedItem().toString().split("x")[0]);
	 }
	});

   
   JRadioButton BFacil = new JRadioButton("Fácil", true);
   JRadioButton BMedio = new JRadioButton("Medio", true);
   JRadioButton BDificil = new JRadioButton("Difícil", true);
   BFacil.addActionListener(this);
   BFacil.setActionCommand(FACIL);
   BFacil.setBackground(new Color(42, 137, 224));
   BFacil.setForeground(Color.WHITE);
   BFacil.setFont(BFacil.getFont().deriveFont(BFacil.getFont().getStyle() & ~Font.BOLD));

   BMedio.addActionListener(this);
   BMedio.setActionCommand(MEDIO);
   BMedio.setBackground(new Color(42, 137, 224));
   BMedio.setForeground(Color.WHITE);
   BMedio.setFont(BMedio.getFont().deriveFont(BMedio.getFont().getStyle() & ~Font.BOLD));

   
   BDificil.addActionListener(this);
   BDificil.setActionCommand(DIFICIL);
   BDificil.setBackground(new Color(42, 137, 224));
   BDificil.setForeground(Color.WHITE);
   BDificil.setFont(BDificil.getFont().deriveFont(BDificil.getFont().getStyle() & ~Font.BOLD));

   ButtonGroup btnGp = new ButtonGroup();
   btnGp.add(BFacil);
   btnGp.add(BMedio);
   btnGp.add(BDificil);

   add(tamanio);
   add(listaTamanios);
   add(dificultad);
   add(BFacil);
   add(BMedio);
   add(BDificil);
	
}

	
public int getDificultad()
{
		return valorDificultad;
}

public int getTamanio()
{
 return valorTamanio;
}

public void actionPerformed(ActionEvent pEvento)
{
	String comand = pEvento.getActionCommand();
		if (comand.equals(FACIL))
		{
		 valorDificultad = Integer.parseInt(FACIL);
		} 
		else if (comand.equals(MEDIO))
		{
		 valorDificultad = Integer.parseInt(MEDIO);
		} 
		else if (comand.equals(DIFICIL))
		{
		 valorDificultad = Integer.parseInt(DIFICIL);
		}
}

	
	
	
}
