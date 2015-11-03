package states;

import java.util.ArrayList;
import java.util.Iterator;

import algorithms.mazeGenerators.Position;
// TODO: Auto-generated Javadoc

/**
 * The Class MazeState.
 */
public class MazeState extends State
{
	
	/** The p. */
	private Position p;

	/**
	 * Gets the p.
	 *
	 * @return the p
	 */
	public Position getP() 
	{
		return p;
	}

	/**
	 * Instantiates a new maze state.
	 *
	 * @param p the p
	 */
	public MazeState(Position p)
	{
		super();
		this.p = new Position(p);
		this.toString();
	}


	/**
	 * Position to state.
	 *
	 * @param pos the pos
	 * @return the state
	 */
	public State positionToState(Position pos) 
	{
		this.p.copyPosition(pos);
		this.toString();
		return this;
	}

	/**
	 * Sets the p.
	 *
	 * @param p the new p
	 */
	public void setP(Position p) {
		this.p.copyPosition(p);;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString ()
	{
		Integer tempIntx = new Integer(p.getX());
		Integer tempIntz = new Integer(p.getZ());
		Integer tempInty = new Integer(p.getY());
		this.setstate((tempIntx.toString() + ',' + tempIntz.toString() + ',' +tempInty.toString()));
		return (this.getstate());
	}
	
	/**
	 * To position.
	 *
	 * @param posInString the pos in string
	 * @return the position
	 */
	public Position toPosition (String posInString)
	{
		String[] seperatedArray = posInString.split(",");
		this.p.setX(Integer.parseInt(seperatedArray[0]));
		this.p.setZ(Integer.parseInt(seperatedArray[1]));
		this.p.setY(Integer.parseInt(seperatedArray[2]));
		return this.getP();
	}
	
	/**
	 * Position arrayto state array.
	 *
	 * @param pArray the array
	 * @return the array list
	 */
	public ArrayList<State> positionArraytoStateArray (ArrayList<Position> pArray)
	{
		ArrayList<State> sArray = new ArrayList<State>();
		for (Iterator<Position> iterator = pArray.iterator(); iterator.hasNext();) 
		{
			Position position = (Position) iterator.next();
			State sTemp = new MazeState(position);
			sTemp.toString();
			sArray.add(sTemp);
		}
		return sArray;
	}
	
	/**
	 * Instantiates a new maze state.
	 *
	 * @param s the s
	 */
	public MazeState(State s) 
	{
		super();
		this.p = new Position(((MazeState) s).getP());
		this.toString();
		if (s.getcameFrom() != null) 
		{
			this.setCameFrom(s.getcameFrom());
		}
	}

	/**
	 * Copy maze state.
	 *
	 * @param s the s
	 */
	public void copyMazeState(State s) 
	{
		this.p.copyPosition(((MazeState) s).getP());
		this.toString();
		if (s.getcameFrom() != null) 
		{
			this.setCameFrom(s.getcameFrom());
		}
	}
}
