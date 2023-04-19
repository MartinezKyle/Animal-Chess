import java.awt.event.*;
import java.awt.Component;
import java.awt.Component.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;

public class Controller
{
	private boolean chooseDone = false;
	private boolean bRedTurn = true;
	private AnimalChess game;
	private View view;
	private BoardPanel board;
	private Cell[][] cells;
	private Piece choice;
	
	public Controller (AnimalChess model, View view)
	{
		game =model;
		view = view;
		board  = view.getBoard();
		cells = board.getButtons();
		
		addEventListeners();
	}
	
	/**
		Adds ActionListeners to cell buttons
	*/
	
	private void addEventListeners()
	{
		int i, j;
		for (i = 0; i < 9; i++)
		{
			for (j = 0; j < 7; j++)
			{
				cells[j][i].addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							if((game.getRules().isWin(game.getPieces(), game.getRedAlivePieces(), game.getBlackAlivePieces()))==null)
								processSelection(e);
						}
					}
				);
			}
		}
	}
	
	/**
		processes the selection ActionEvent e
		@param e the ActionEvent to be processed
	*/
	private void processSelection(ActionEvent e)
	{
		Coordinate choiceCoord = null;
		Piece tempChoicePiece = null;
		int i, j;
		Object source = e.getSource();
		for (i = 0; i < 9; i++)
		{
			for (j = 0; j < 7; j++)
			{
				if (cells[j][i] == source)
				{
					choiceCoord = new Coordinate (j, i);
				}
			}
		}
		
		if (chooseDone == false)
		{
			tempChoicePiece = searchPiece (game.getPieces(), choiceCoord);
			if (tempChoicePiece!= null && tempChoicePiece.isInRed() == bRedTurn)
			{
				this.choice = tempChoicePiece;
				chooseDone = !chooseDone;
			}
		}
		else
		{
			if (game.getRules().isMoveValid(game.getPieces(), choice, choiceCoord, bRedTurn))
			{
				game.move(choice, choiceCoord);
				board.getButtons()[choice.getCoordinate().getX()][choice.getCoordinate().getX()].free();
				board.repaint();
				if (bRedTurn == true)
					bRedTurn = false;
				else
					bRedTurn = true;
			}
			chooseDone = !chooseDone;
			choice = null;
		}
		if((game.getRules().isWin(game.getPieces(), game.getRedAlivePieces(), game.getBlackAlivePieces()))!=null)
			winMessageWindow (game.getRules().isWin(game.getPieces(), game.getRedAlivePieces(), game.getBlackAlivePieces()));
	}
	
	/**
		Displays a winner message window when called
		@param winner The string containing the name of the side of the winner
	*/
	private void winMessageWindow (String winner)
	{
		JFrame winMessage = new JFrame ("Congratulations!");
		winMessage.setLayout(new BorderLayout());
		winMessage.setSize(300, 100);
		winMessage.setResizable (false);
		
		JTextArea messageArea = new JTextArea();
		messageArea.setText("Congratulations!\n" + winner + " wins!\n\n(Click Close Button to Exit Game)");
        messageArea.setEditable(false);
		
		winMessage.add(messageArea, BorderLayout.CENTER);
		winMessage.setVisible(true);
		winMessage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
		Searches and returns the piece that is found in coordinate, null otherwise
		
		@param pieces the ArrayList of pieces
		@param coords the coordinate of the piece to be found
		@return the Piece that is found in coords coordinates, null otherwise
	*/	
	private Piece searchPiece (ArrayList<Piece> pieces, Coordinate coords)
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