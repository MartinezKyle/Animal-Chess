import java.util.*;
import java.util.Scanner;

public class AnimalChess
{
	private GameRules rules;
	private int redAlivePieces;
	private int blackAlivePieces;
	private boolean bRedTurn;
	private ArrayList<Piece> pieces;
	
	public AnimalChess()
	{
		rules = new GameRules();
		redAlivePieces = 8;
		blackAlivePieces = 8;
		pieces = new ArrayList<Piece>();
		bRedTurn = true;
		initializePieces();
	}
	
	/**
		Declares/initializes/sets the required pieces and initial 
		required values of attributes of the pieces
	*/
	private void initializePieces()
	{
		pieces.add(new Elephant (new Coordinate (0, 6), true, "red"));
		pieces.add(new Lion (new Coordinate (6, 8), true, "red"));
		pieces.add(new Tiger (new Coordinate (0, 8), true, "red"));
		pieces.add(new Leopard (new Coordinate (4, 6), true, "red"));
		pieces.add(new Dog (new Coordinate (5, 7), true, "red"));
		pieces.add(new Wolf (new Coordinate (2, 6), true, "red"));
		pieces.add(new Cat (new Coordinate (1, 7), true, "red"));
		pieces.add(new Mouse (new Coordinate (6, 6), true, "red")); //6 6
		pieces.add(new Elephant (new Coordinate (6, 2), false, "black"));
		pieces.add(new Lion (new Coordinate (0, 0), false, "black")); //0 0 
		pieces.add(new Tiger (new Coordinate (6, 0), false, "black"));
		pieces.add(new Leopard (new Coordinate (2, 2), false, "black"));
		pieces.add(new Dog (new Coordinate (1, 1), false, "black"));
		pieces.add(new Wolf (new Coordinate (4, 2), false, "black"));
		pieces.add(new Cat (new Coordinate (5, 1), false, "black"));
		pieces.add(new Mouse (new Coordinate (0, 2), false, "black"));
	}
	
	/**
		Moves the piece to a coordinate
		@param choice the piece to be moved
		@param dest the coodinate to move to
	*/
	public void move (Piece choice, Coordinate dest)
	{
		if (searchPiece(dest) != null)
			kill(searchPiece(dest));
		choice.setCoords(dest);
	}
	
	/**
		Returns rules
		@return rules
	*/
	public GameRules getRules()
	{
		return this.rules;
	}
	
	/**
		Kills the piece passes
		@param target the piece to be killed
	*/
	private void kill (Piece target)
	{
		target.killThis();
	}
	
	/**
		Returns the array list of pieces
		@return ArrayList of pieces
	*/
	public ArrayList<Piece> getPieces()
	{
		return pieces;
	}
	
	/**
		Returns the number of alive pieces of Red
		@return redAlivePieces (the number of alive pieces of Red)
	*/
	public int getRedAlivePieces()
	{
		return redAlivePieces;
	}
	
	/**
		Returns the number of alive pieces of Black
		@return blackAlivePieces (the number of alive pieces of Black)
	*/
	public int getBlackAlivePieces()
	{
		return blackAlivePieces;
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
