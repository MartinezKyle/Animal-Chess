import java.util.*;
import java.awt.Image;
import java.awt.Image.*;
import javax.swing.ImageIcon;

public class Piece
{
	private Coordinate objCoords;
	private int nRank;
	private boolean bInRed;
	private boolean bAlive;
	private ImageIcon image;
	
	public Piece (Coordinate coord, boolean inRed, String imageName, int rank)
	{
		objCoords = new Coordinate(coord.getX(), coord.getY());
		bInRed = inRed;
		image = new ImageIcon ((new ImageIcon(imageName).getImage()).getScaledInstance(75, 75, Image.SCALE_SMOOTH));
		nRank = rank;
		bAlive = true;
	}
	
	/**
		Returns the coordinate of the piece
		@return objCoords (the coordinate of the piece)
	*/
	public Coordinate getCoordinate()
	{
		return objCoords;
	}
	
	/**
		Returns the rank of the piece
		@return nRank (the rank of the piece)
	*/
	public int getRank ()
	{
		return nRank;
	}
	
	/**
		sets the coordinate of this piece to another coordinate
		@param coord the coordinate to replace the old coordinate
	*/
	public void setCoords (Coordinate coord) 
	{
		objCoords.setCoords(coord);
	}
	
	/**
		Sets the alive status of this piece false
	*/
	public void killThis()
	{
		bAlive = false;
	}
	
	/**
		Checks if piece is in Red's side
		@return bInRed (the boolean variable that tells if piece is in Red's side)
	*/
	public boolean isInRed ()
	{
		return bInRed;
	}
	
	/**
		Checks if piece is alive
		@return bAlive (the boolean variable that tells if piece is alive)
	*/
	public boolean isAlive ()
	{
		return bAlive;
	}
	
	/**
		Returns the ImageIcon corresponding to the piece
		@return image (the ImageIcon corresponding to the piece)
	*/
	public ImageIcon getImage ()
	{
		return image;
	}
	
	/**
		Returns if the object has the same coordinate with this piece
		@param obj the object to compare with this piece
		@return if the object has the same coordinate with this piece
	*/
	@Override
	public boolean equals (Object obj)
	{
		if (obj != null)
		{
			Piece otherPiece = (Piece) obj;
			return this.objCoords.equals((otherPiece.objCoords));
		}
		else return false;
	}
}