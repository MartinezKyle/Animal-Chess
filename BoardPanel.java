import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
public class BoardPanel extends JPanel
{
	private ArrayList<Piece> pieces;
	private Cell[][] buttons;
	public BoardPanel(ArrayList<Piece> pieceList)
	{
		super();
		int i, j;
		pieces = pieceList;
		setLayout (new GridLayout(9, 7));
		buttons = new Cell[7][9];
		for (i = 0; i < 9; i++)
		{
			for (j = 0; j < 7; j++)
			{
				buttons[j][i] = new Cell();
				//buttons[j][i] = new Cell("(" + j + ", " + i +  ")");
				buttons[j][i].setBounds((75 * j ) + j, (75 * i ) + i, 75, 75);
				add(buttons[j][i]);
			}
		}
		
		assignCells();
	}
	/**
		assigns properties to special cells
	*/
	private void assignCells()
	{
		int i, j;
		for (i = 0; i < 9; i++)
		{
			for (j = 0; j < 7; j++)
			{
				if (((j > 0 && j < 3) || (j > 3 && j < 6)) && (i > 2 && i < 6))
					buttons[j][i].setRiver();
				else if ((j == 2 && i == 0) || (j == 3 && i == 1) || (j == 4 && i == 0))
					buttons[j][i].setTrap();
				else if ((j == 2 && i == 8) || (j == 3 && i == 7) || (j == 4 && i == 8))
				{
					buttons[j][i].setRed();
					buttons[j][i].setTrap();
				}
				else if (j == 3 && i == 0)
					buttons[j][i].setDen();
				else if (j == 3 && i == 8)
				{
					buttons[j][i].setRed();
					buttons[j][i].setDen();
				}
			}
		}
	}
	
	/**
		Sets the pieces Array List variable to pieceList
	*/
	public void setPieces(ArrayList<Piece> pieceList)
	{
		pieces = pieceList;
	}
	
	/**
		Paints the cells in button[][]
		@param g graphics object
	*/
	@Override
	public void paintComponent (Graphics g)
	{
		int i, j;
		for (i = 0; i < 9; i++)
		{
			for (j = 0; j < 7; j++)
			{
				Piece piece = searchPiece(new Coordinate(j, i));
				if (piece != null)
				{
					buttons[j][i].occupy(piece.getImage());
				}
				else
					buttons[j][i].free();
				buttons[j][i].repaint();
			}
		}
	}
	
	/**
		Gets the array of Cell buttons
		@return Cell[][] buttons array
	*/
	public Cell[][] getButtons()
	{
		return this.buttons;
	}
	
	/**
		Searches and returns the piece that is found in coordinate, null otherwise
		
		@param coords the coordinate of the piece to be found
		@return the Piece that is found in coords coordinates, null otherwise
	*/	
	public Piece searchPiece (Coordinate coords)
	{
		Piece found = null;
		int x = 0;
		do
		{
			if (pieces.get(x).getCoordinate().equals(coords) && pieces.get(x).isAlive())
			{
				found = pieces.get(x);
			}
			x++;
		}while(x < pieces.size() && found == null);
		return found;
	}
}