import java.util.*;

public class Elephant extends Piece
{
	public Elephant (Coordinate coord, boolean inRed, String side)
	{
		super (coord, inRed, "imagePieces/" + side + "_elephant.png", 8);
	}
	
	/**
		Searches and returns the piece that is found in coordinate, null otherwise
		
		@param pieces the ArrayList of pieces
		@param coords the coordinate of the piece to be found
		@return the Piece that is found in coords coordinates, null otherwise
	*/
	@Override
	public boolean equals (Object obj)
	{
		if (obj != null)
		{
			Piece otherPiece = (Piece) obj;
			return super.getCoordinate().equals((otherPiece.getCoordinate()));
		}
		else return false;
	}
}