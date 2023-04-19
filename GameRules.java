import java.util.*;

public class GameRules
{
	public GameRules()
	{
	}
	
	/**
		Checks if the a piece can be moved to a certain coordinate
		
		@param pieces the ArrayList of pieces
		@param choice the piece to be moved
		@param dest the Coordinate to move to
		@param redTurn boolean that tells if it is Red's turn
		@return if the pieece can be moved to the said coordinate
	*/
	public boolean isMoveValid(ArrayList<Piece> pieces, Piece choice, Coordinate dest, boolean redTurn)
	{
		boolean isValid = false;
		if (isOneDist(choice.getCoordinate(), dest))
		{
			if(searchPiece(pieces, dest) == null)
			{
				if (!isRiver (dest))
					isValid = true;
				else if (isRiver (dest) && choice instanceof Mouse)
					isValid = true;
			}
			else
			{
				if (searchPiece(pieces, dest).isInRed() != redTurn)
					isValid = isCaptureValid(pieces, choice, dest, redTurn);
			}
		}
		else
		{
			if ((choice instanceof Lion || choice instanceof Tiger)&&isPathRiver(choice.getCoordinate(), dest)&&isRiverPathEmpty(pieces, choice.getCoordinate(), dest))
			{
				if(searchPiece(pieces, dest) == null && !isRiver (dest))
				{
					isValid = true;
				}
				else
				{
					if (searchPiece(pieces, dest).isInRed() != redTurn)
						isValid = isCaptureValid(pieces, choice, dest, redTurn);
				}
			}
		}
		return isValid;
	}
	
	/**
		Checks if the distance between src and dest is exactly 1 cell
		
		@param src the Coordinate of the piece
		@param dest the Coordinate to move to
		@return if the distance between src and dest is exactly 1 cell
	*/
	private boolean isOneDist(Coordinate src, Coordinate dest)
	{
		boolean isOne = false;
		if (dest.getX() == src.getX())
		{
			if (dest.getY() == src.getY() + 1 || dest.getY() == src.getY() - 1 )
			{
				isOne = true;
			}
		}
		else if (dest.getY() == src.getY())
		{
			if (dest.getX() == src.getX() + 1 || dest.getX() == src.getX() - 1 )
			{
				isOne = true;
			}
		}
		return isOne;
	}
	
	/**
		Checks if the coordinates are found in river
		
		@param coords the Coordinate to check if it is river
		@return if the coordinate is a river coordinates
	*/
	private boolean isRiver (Coordinate coords)
	{
		return (((coords.getX() > 0 && coords.getX() < 3) || (coords.getX() > 3 && coords.getX() < 6)) && (coords.getY() > 2 && coords.getY() < 6));
	}
	
	/**
		Checks if the cells between src and dest are all rivers
		
		@param src the Coordinate of the piece
		@param dest the Coordinate to move to
		@return if the path is all river
	*/
	private boolean isPathRiver(Coordinate src, Coordinate dest)
	{
		boolean isValid = false;
		int i;
		if (dest.getX() == src.getX())
		{
			isValid = true;
			if (dest.getY() > src.getY())
			{
				i = 1;
				do
				{
					isValid = isRiver(new Coordinate (src.getX(), src.getY() + i));
					i++;
				}while (src.getY() + i !=  dest.getY() && isValid);
			}
			else if (dest.getY() < src.getY())
			{
				i = -1;
				do
				{
					isValid = isRiver(new Coordinate (src.getX(), src.getY() + i));
					i--;
				}while (src.getY() + i !=  dest.getY() && isValid);
			}
		}
		else if (dest.getY() == src.getY())
		{
			isValid = true;
			if (dest.getX() > src.getX())
			{
				i = 1;
				do
				{
					isValid = isRiver(new Coordinate (src.getX() + i, src.getY()));
					i++;
				}while (src.getX() + i !=  dest.getX() && isValid);
			}
			else if (dest.getX() < src.getX())
			{
				i = -1;
				do
				{
					isValid = isRiver(new Coordinate (src.getX() + i, src.getY()));
					i--;
				}while (src.getX() + i !=  dest.getX() && isValid);
			}
		}
		return isValid;
	}
	
	/**
		Checks if there is a mouse on the way
		
		@param pieces the ArrayList of pieces
		@param src the Coordinate of the piece
		@param dest the Coordinate to move to
		@return if there is a mouse found on the river toward the piece's direction
	*/
	private boolean isRiverPathEmpty (ArrayList<Piece> pieces, Coordinate src, Coordinate dest)
	{
		boolean isValid = false;
		int i;
		if (dest.getX() == src.getX())
		{
			isValid = true;
			if (dest.getY() > src.getY())
			{
				i = 1;
				do
				{
					isValid = (searchPiece(pieces, new Coordinate (src.getX(), src.getY() + i)) == null);
					i++;
				}while (src.getY() + i !=  dest.getY() && isValid);
			}
			else if (dest.getY() < src.getY())
			{
				i = -1;
				do
				{
					isValid = (searchPiece(pieces, new Coordinate (src.getX(), src.getY() + i)) == null);
					i--;
				}while (src.getY() + i !=  dest.getY() && isValid);
			}
		}
		else if (dest.getY() == src.getY())
		{
			isValid = true;
			if (dest.getX() > src.getX())
			{
				i = 1;
				do
				{
					isValid = (searchPiece(pieces, new Coordinate (src.getX() + i, src.getY())) == null);
					i++;
				}while (src.getX() + i !=  dest.getX() && isValid);
			}
			else if (dest.getX() < src.getX())
			{
				i = -1;
				do
				{
					isValid = (searchPiece(pieces, new Coordinate (src.getX() + i, src.getY())) == null); 
					i--;
				}while (src.getX() + i !=  dest.getX() && isValid);
			}
		}
		return isValid;
	}
	
	/**
		Checks if an attempted capture is valid or not
		
		@param pieces the ArrayList of pieces
		@param choice the piece to be moved
		@param dest the Coordinate to move to
		@param redTurn boolean that tells if it is Red's turn
		@return if the pieece can be moved to the said coordinate
	*/
	private boolean isCaptureValid (ArrayList<Piece> pieces, Piece choice, Coordinate dest, boolean redTurn)
	{
		boolean isValid = false;
		Piece targetPiece = searchPiece (pieces, dest);
		if (redTurn && isRedTrap(dest))
			isValid = true;
		else if (!redTurn && isBlackTrap (dest))
			isValid = true;
		else if (choice.getRank() >= targetPiece.getRank() && !(choice instanceof Elephant && targetPiece instanceof Mouse) && !(choice instanceof Mouse))
			isValid = true;
		else if (choice instanceof Mouse)
		{
			if (targetPiece instanceof Mouse && !(isRiver(choice.getCoordinate())) && !(isRiver(targetPiece.getCoordinate())))
				isValid = true;
			else if (targetPiece instanceof Mouse && isRiver(choice.getCoordinate()) && isRiver(targetPiece.getCoordinate()))
				isValid = true;
			else if (targetPiece instanceof Elephant && !(isRiver(choice.getCoordinate())) && !(isRiver(targetPiece.getCoordinate())))
				isValid = true;
		}
		return isValid;
	}
	
	/**
		Checks if the coordinates are found in Red's traps
		
		@param coords the Coordinate to check if it is in Red's traps
		@return if the coordinate is a river coordinates
	*/
	private boolean isRedTrap (Coordinate coords)
	{
		return (coords.getX() == 2 && coords.getY() == 8) || (coords.getX() == 3 && coords.getY() == 7) || (coords.getX() == 4 && coords.getY() == 8);
	}
	
	/**
		Checks if the coordinates are found in Black's traps
		
		@param coords the Coordinate to check if it is in Black's traps 
		@return if the coordinate is a river coordinates
	*/
	private boolean isBlackTrap (Coordinate coords)
	{
		return (coords.getX() == 2 && coords.getY() == 0) || (coords.getX() == 3 && coords.getY() == 1) || (coords.getX() == 4 && coords.getY() == 0);
	}
	
	/**
		Checks if the game has a winner already
		
		@param pieces the ArrayList of pieces
		@param redAlive the number of alive red pieces
		@param blackAlive the number of alive black pieces
		@return the Piece that is found in x and y coordinates, null otherwise
	*/	
	public String isWin (ArrayList<Piece> pieces, int redAlive, int blackAlive)
	{
		String result = null;
		if ((searchPiece(pieces, new Coordinate (3, 0))!= null && searchPiece(pieces, new Coordinate (3, 0)).isInRed())||blackAlive == 0)
			result = "Red";
		else if ((searchPiece(pieces, new Coordinate (3, 8)) != null && !(searchPiece(pieces, new Coordinate (3, 8)).isInRed()))||redAlive == 0)
			result = "Black";
		return result;
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


