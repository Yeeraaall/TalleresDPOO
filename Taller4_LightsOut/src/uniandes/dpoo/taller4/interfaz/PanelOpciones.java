package uniandes.dpoo.taller4.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.dpoo.taller4.modelo.*;


public class PanelOpciones extends JPanel implements ActionListener
{
	

private JButton btnNuevo;
private JButton btnReiniciar;
private JButton btnTop10;
private JButton btnCambiarJugador;

private PanelLuces panelLuces; 
private PanelDificultad panelDificultad; 
private PanelJugadas panelJugadas;
private VentanaLightsOut VentanaLuces;

public final static String NUEVO = "NUEVO";
public final static String REINICIAR = "REINICIAR";
public final static String TOP10 = "TOP-10";
public final static String CAMBIAR_JUGADOR = "CAMBIAR JUGADOR";
	



public PanelOpciones(VentanaLightsOut Lventana)
{
		
	setLayout(new BorderLayout());
	this.VentanaLuces = Lventana;

		
	JPanel arriba = new JPanel();
	arriba.setPreferredSize(new Dimension(150, 150));
	arriba.setBackground(Color.WHITE);
	JPanel abajo = new JPanel();
	abajo.setPreferredSize(new Dimension(150, 150));
	abajo.setBackground(Color.WHITE);
	JPanel centro = new JPanel();
	centro.setLayout(new GridLayout(4, 1, 0, 10));
	centro.setBackground(Color.WHITE);

		
	this.btnNuevo = new JButton(NUEVO);
	Color customColor1 = new Color(70,185,245);
	btnNuevo.addActionListener(this);
	btnNuevo.setActionCommand(NUEVO);
	btnNuevo.setBackground(customColor1);
	btnNuevo.setForeground(Color.WHITE);
	btnNuevo.setFont(btnNuevo.getFont().deriveFont(btnNuevo.getFont().getStyle() & ~Font.BOLD));
	centro.add(btnNuevo);

	this.btnReiniciar = new JButton(REINICIAR);
	btnReiniciar.addActionListener(this);
	btnReiniciar.setActionCommand(REINICIAR);
	btnReiniciar.setBackground(customColor1);
	btnReiniciar.setForeground(Color.WHITE);
	btnReiniciar.setFont(btnReiniciar.getFont().deriveFont(btnReiniciar.getFont().getStyle() & ~Font.BOLD));
	centro.add(btnReiniciar);

	this.btnTop10 = new JButton(TOP10);
	btnTop10.addActionListener(this);
	btnTop10.setActionCommand(TOP10);
	btnTop10.setBackground(customColor1);
	btnTop10.setForeground(Color.WHITE);
	btnTop10.setFont(btnTop10.getFont().deriveFont(btnTop10.getFont().getStyle() & ~Font.BOLD));
	centro.add(btnTop10);

	this.btnCambiarJugador = new JButton(CAMBIAR_JUGADOR);
	btnCambiarJugador.addActionListener(this);
	btnCambiarJugador.setActionCommand(CAMBIAR_JUGADOR);
	btnCambiarJugador.setBackground(customColor1);
	btnCambiarJugador.setForeground(Color.WHITE);
	btnCambiarJugador.setFont(btnCambiarJugador.getFont().deriveFont(btnCambiarJugador.getFont().getStyle() & ~Font.BOLD));
	centro.add(btnCambiarJugador);

		
	btnNuevo.setActionCommand(NUEVO);
	btnReiniciar.setActionCommand(REINICIAR);
	btnTop10.setActionCommand(TOP10);
	btnCambiarJugador.setActionCommand(CAMBIAR_JUGADOR);

	this.panelDificultad = new PanelDificultad();
	add(panelDificultad, BorderLayout.NORTH);
	this.panelLuces = new PanelLuces(this, 3); 
	this.panelLuces.setEnabled(false);
	JPanel panelMenu = new JPanel();
	panelMenu.setLayout(new BorderLayout());
	panelMenu.setBackground(Color.WHITE);
	this.panelJugadas = new PanelJugadas();
	add(panelJugadas, BorderLayout.SOUTH);
	panelMenu.add(arriba, BorderLayout.NORTH);
	panelMenu.add(abajo, BorderLayout.SOUTH);
	panelMenu.add(centro, BorderLayout.CENTER);

	add(panelLuces, BorderLayout.CENTER);
	add(panelMenu, BorderLayout.EAST);
}

public int getDimensiones()
{
 return panelDificultad.getTamanio();
}

public int getDificultad()
{
 return panelDificultad.getDificultad();
}

public PanelLuces getPanelTablero()
{
 return panelLuces;
}

public PanelJugadas getPanelDetalles()
{
 return panelJugadas;
}

public void actualizar()
{

	
	
 PanelJugadas detalles = getPanelDetalles();
 detalles.setJugadas(panelLuces.getTablero().darJugadas());
 boolean finJuego = panelLuces.estaCompletado();

 if (finJuego)
	{
	  String jugadas = getPanelDetalles().getJugadas();
	  int puntaje = panelLuces.getTablero().calcularPuntaje();

	  JOptionPane.showMessageDialog(this, "¡Ganaste con " + jugadas + " jugadas!", "GAME OVER :)",JOptionPane.INFORMATION_MESSAGE);
	  JOptionPane.showMessageDialog(this, "¡Has obtenido " + Integer.toString(puntaje) + " puntos!", "Puntaje",JOptionPane.INFORMATION_MESSAGE);
			if (panelJugadas.getJugador().length() > 0)
			{
				{
					RegistroTop10 registro = new RegistroTop10(panelJugadas.getJugador(), puntaje);
				}
			}
			int reiniciar = JOptionPane.showConfirmDialog(this, "¿Quieres iniciar un nuevo juego?", "Volver a jugar",JOptionPane.YES_NO_OPTION);

			if (reiniciar == JOptionPane.YES_OPTION)
			{
			  remove(panelLuces);
			  panelLuces = new PanelLuces(this, 3);
			  add(panelLuces, BorderLayout.CENTER);
			  updateUI();
			  this.panelLuces.setEnabled(false);
			}
			else
			{
			  this.setEnabled(false);
			  this.panelLuces.setEnabled(!finJuego);
			  JOptionPane.showMessageDialog(this, "Puede cerrar la ventana", "Fin del juego",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}


public void actionPerformed(ActionEvent pEvento)
{
	String comando = pEvento.getActionCommand();
	if (comando.equals(NUEVO))
	{
		int nuevoTamanio = getDimensiones();
		int nuevaDificultad = getDificultad();
		this.panelLuces.setEnabled(true);
		remove(panelLuces);
		panelLuces = new PanelLuces(this, nuevoTamanio);
		add(panelLuces, BorderLayout.CENTER);updateUI();
		Tablero tablero = getPanelTablero().getTablero();
		tablero.desordenar(nuevaDificultad);
		panelJugadas.setJugadas(0);
	} 
	else if (comando.equals(REINICIAR))
	{
		Tablero tablero = panelLuces.getTablero();
		tablero.reiniciar();
		panelJugadas.setJugadas(0);
	} 
	
	else if (comando.equals(CAMBIAR_JUGADOR))
	{
	  int cambiar = JOptionPane.showConfirmDialog(this, "¿Quieres cambiar de jugador?", "Cambiar Jugador",JOptionPane.YES_NO_OPTION);

	if (cambiar == JOptionPane.YES_OPTION)
	{
	   String strJugador = JOptionPane.showInputDialog(this, "Introduce el nombre del jugador:","Cambiar Jugador", JOptionPane.QUESTION_MESSAGE);
		 if (strJugador != null)
			{
				panelJugadas.setJugador(strJugador);
				Tablero tablero = panelLuces.getTablero();
				tablero.reiniciar();
			}
	}
		}
	}
}