import java.awt.event.*;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.*;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Icon;
import javax.swing.ImageIcon;
public class Cell extends JButton
{
	private boolean bOccupied = false;
	private boolean bRiver = false;
	private boolean bDen = false;
	private boolean bTrap = false;
	private boolean bRed = false;
	private ImageIcon icon;
	public Cell()
	{
		super();
	}
	
	/**
		Paints the Cell
		@param g graphics object
	*/
	@Override
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		setOpaque(true);
		if (bOccupied)
		{
			setIcon(icon);
		}
		else
		{
			setIcon(null);
			if (bRiver)
				setBackground(Color.BLUE);
			else if (bDen)
			{
				if (bRed == true)
					setBackground(Color.RED);
				else
					setBackground(Color.BLACK);
			}
			else
				setBackground(Color.WHITE);
		}
		if (bRiver == true)
		{
			setBorderPainted(true);
            setBorder(BorderFactory.createLineBorder(Color.BLUE,3));
            paintBorder(g);
		}
		else if (bDen == true || bTrap == true)
		{
			if (bRed == true)
			{
				setBorderPainted(true);
				setBorder(BorderFactory.createLineBorder(Color.RED,3));
				paintBorder(g);
			}
			else
			{
				setBorderPainted(true);
				setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
				paintBorder(g);
			}
		}
		else
		{
			setBorderPainted(true);
            setBorder(BorderFactory.createLineBorder(Color.GRAY,3));
            paintBorder(g);
		}
		setVisible(true);
	}
	
	/**
		Sets the ImageIcon of the cell ti the ImageIcon of the piece
		occupying the coordinate associated with the cell. Also sets
		occupied to true
		
		@param occupier the ImageIcon of the occupying piece
	*/
	public void occupy(ImageIcon occupier)
	{
		icon = occupier;
		bOccupied = true;
	}
	
	/**
		Sets occupied to null and removes the occupier ImageIcon
	*/
	public void free ()
	{
		icon = null;
		bOccupied = false;		
	}
	
	/**
		Sets the cell to be a special red cell
	*/
	public void setRed()
	{
		bRed = true;
	}
	
	/**
		Sets the cell to be a river cell
	*/
	public void setRiver()
	{
		bRiver = true;
	}
	
	/**
		Sets the cell to be a den cell
	*/
	public void setDen()
	{
		bDen = true;
	}
	
	/**
		Sets the cell to be a trap cell
	*/
	public void setTrap()
	{
		bTrap = true;
	}
}