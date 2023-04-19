import java.util.*;

public class Coordinate
{
	private int nX;
	private int nY;
	
	public Coordinate (int x, int y)
	{
		nX = x;
		nY = y;
	}
	
	/**
		Returns the x variable of the coordinate
		@return nX (the y variable of the coordinate)
	*/
	public int getX()
	{
		return nX;
	}
	
	/**
		Returns the y variable of the coordinate
		@return nY (the y variable of the coordinate)
	*/
	public int getY()
	{
		return nY;
	}
	
	/**
		sets this coordinate to another coordinate
		@param coord the coordinate to replace the old coordinate
	*/
	public void setCoords(Coordinate coord)
	{
		nX = coord.getX();
		nY = coord.getY();
	}
	
	
	/**
		Returns if the object is the same coordinate with this coordinate
		@param obj the object to compare with this coordinate
		@return if the object is the same coordinate with this coordinate
	*/
	@Override
	public boolean equals (Object obj)
	{
		if (obj != null)
		{
			Coordinate otherCoord = (Coordinate) obj;
			return (nX == otherCoord.getX() && nY == otherCoord.getY());
		}
		else return false;
	}
}