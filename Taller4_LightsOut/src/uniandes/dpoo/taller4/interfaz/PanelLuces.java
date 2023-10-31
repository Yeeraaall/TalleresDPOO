package uniandes.dpoo.taller4.interfaz;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import uniandes.dpoo.taller4.modelo.*;



public class PanelLuces extends JPanel implements MouseListener
{
	
	
private boolean completado;
private PanelOpciones panelOpciones;
private Tablero tableroDeJuego;
private Image imagenLuz;



	public PanelLuces (PanelOpciones panelOpciones, int tamanioTablero)
	{
		setLayout(new BorderLayout());
		this.addMouseListener(this);
		this.tableroDeJuego = new Tablero(tamanioTablero);
		try
		{
			this.imagenLuz = ImageIO.read(new File("data/luz.png"));
		} catch (IOException e)
		{
		}
		this.completado = false;
		this.panelOpciones = panelOpciones;
	}

	public Tablero getTablero()
	{
		return tableroDeJuego;
	}
	
	public boolean estaCompletado()
	{
		if (tableroDeJuego.tableroIluminado() == true)
		{
			completado = true;
		}
		return completado;
	}
	
	public int getJugadas()
	{
		return tableroDeJuego.darJugadas();
	}

	private int[] convertirCoordenadasACasilla(int x, int y)
	{
		int ladoTablero = tableroDeJuego.darTablero().length;
		int altoPanelTablero = getHeight();
		int anchoPanelTablero = getWidth();
		int altoCasilla = altoPanelTablero / ladoTablero;
		int anchoCasilla = anchoPanelTablero / ladoTablero;
		int fila = (int) (y / altoCasilla);
		int columna = (int) (x / anchoCasilla);
		return new int[] {fila, columna};
	}
	
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		
		int contadorFilas = 0;
		for (boolean[] i : tableroDeJuego.darTablero())
		{
			int contadorColumnas = 0;
			for (boolean j : i)
			{
				double a = getWidth() / tableroDeJuego.darTablero().length;
				double b = getHeight() / tableroDeJuego.darTablero().length;
				g2d.setColor(Color.WHITE);
				RoundRectangle2D.Double rect = new RoundRectangle2D.Double((contadorColumnas * a) + 1,
						(contadorFilas * b) + 1, a - 1, b - 1, 5, 5);
				g2d.draw(rect);
				if (j == true)
				{
					Color customColor = new Color(43,243,245);
					Color customColor1 = new Color(27,207,244);
					g2d.setPaint(new GradientPaint((int) (contadorColumnas * a) + 1, (int) (contadorFilas * b) + 1,
							(customColor), (int) ((contadorColumnas * a) + 1 + a), (int) ((contadorFilas * b) + 1 + b),
							(customColor1)));
					g2d.fill(rect);
				} else
				{
					Color customColor = new Color(45,50,51);
					Color customColor1 = new Color(65,87,92);
					
					g2d.setPaint(new GradientPaint((int) (contadorColumnas * a) + 1, (int) (contadorFilas * b) + 1,
							(customColor), (int) ((contadorColumnas * a) + 1 + a), (int) ((contadorFilas * b) + 1 + b),
							(customColor1)));
					;
					g2d.fill(rect);
				}
				g2d.drawImage(imagenLuz, (int) Math.round((contadorColumnas * a) + a / 6),
						(int) Math.round((contadorFilas * b) + b / 6), (int) (a / 1.5), (int) (b / 1.5), this);
				contadorColumnas++;
			}
			contadorFilas++;
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (!isEnabled())
		{
			return ;
		}
		int click_x = e.getX();
		int click_y = e.getY();
		int[] casilla = convertirCoordenadasACasilla(click_x, click_y);
		tableroDeJuego.jugar(casilla[0], casilla[1]);
		repaint();
		panelOpciones.actualizar();
	}


	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	
	
	
	
	
	
	
}
