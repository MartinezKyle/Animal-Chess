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
public class View
{
	private JFrame frame;
	private BoardPanel board;
	public View(AnimalChess game)
	{
		frame = new JFrame("Animal Chess");
		frame.setLayout(new BorderLayout());
		frame.setSize(593,749);
		frame.setResizable (false);
		board = new BoardPanel(game.getPieces());
		frame.add(board, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
		sets up the new board using a piecelist array lis
		@param pieces the ArrayList of pieces
	*/
	public void setBoard(ArrayList<Piece> pieceList)
	{
		board = new BoardPanel(pieceList);
	}
	
	/**
		Returns the board
		@return board (the board component of view)
	*/
	public BoardPanel getBoard()
	{
		return this.board;
	}
}