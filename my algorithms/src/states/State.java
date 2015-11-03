package states;

import java.util.ArrayList;

import algorithms.mazeGenerators.Position;
// TODO: Auto-generated Javadoc

/**
 * The Class State.
 */
public class State 
{

	/** The state string. */
	private String state;   

	/** The cost. */
	private double cost;   			 

	/** The previous state. */
	private State cameFrom;  		

	/** The h. */
	private double g,h;					

	/**
	 * Instantiates a new state.
	 */
	public State() 
	{
		this.state = new String();
		this.cost = 0;
		this.g = 0;
		this.h = 0;
	}

	/**
	 * Instantiates a new state.
	 *
	 * @param s1 the s1
	 */
	public State(State s1) 
	{
		this.state = new String(s1.state);
		this.cost = s1.cost;
		{
			this.cameFrom = new State();
			cameFrom = s1.cameFrom;
		}
		this.g = s1.g;
		this.h = s1.h;

	}

	/**
	 * Gets the g.
	 *
	 * @return the g
	 */
	public double getG() {
		return g;
	}

	/**
	 * Sets the g.
	 *
	 * @param g the new g
	 */
	public void setG(double g)
	{
		this.g = g;
	}

	/**
	 * Gets the h.
	 *
	 * @return the h
	 */
	public double getH()
	{
		return h;
	}

	/**
	 * Sets the h.
	 *
	 * @param h the new h
	 */
	public void setH(double h) 
	{
		this.h = h;
	}

	/**
	 * Gets the state string.
	 *
	 * @return the state string
	 */
	public String getstate()
	{
		return state;
	}

	/**
	 * Sets the state string.
	 *
	 * @param state the new state string
	 */
	public void setstate(String state)
	{
		this.state = new String(state);
	}

	/**
	 * Instantiates a new state.
	 *
	 * @param state the state
	 */
	public State(String state)
	{      
		this.state = new String(state);
	}

	/**
	 * Prints the.
	 */
	public void print() 
	{
		System.out.println(this.getstate());
	}

	/**
	 * Gets the previous state.
	 *
	 * @return the previous state
	 */
	public State getcameFrom()
	{
		return cameFrom;
	}

	/**
	 * Sets the previous state.
	 *
	 * @param prevState the new previous state
	 */
	public void setcameFrom(State prevState)
	{
		this.cameFrom = new State();
		this.cameFrom = prevState;

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{ 
		return state.equals(((State)obj).state);
	}

	/**
	 * Gets the cost.
	 *
	 * @return the cost
	 */
	public double getCost() 
	{
		return cost;
	}

	/**
	 * Sets the cost.
	 *
	 * @param cost the new cost
	 */
	public void setCost(double cost)
	{
		this.cost = cost;
	}

	/**
	 * Sets the came from.
	 *
	 * @param n the new came from
	 */
	public void setCameFrom(State n)
	{
		this.cameFrom =n;

	}

	/**
	 * Calc cost.
	 */
	public void calcCost()
	{
		this.cost = this.g + this.h;
	}

	/**
	 * Calc g.
	 *
	 * @param g the g
	 * @param price the price
	 */
	public void calcG(double g, double price)
	{
		this.g = g+ price;
	}

	/**
	 * String arrayto state array.
	 *
	 * @param pArray the array
	 * @return the array list
	 */
	public ArrayList<State> stringArraytoStateArray (ArrayList<String> pArray)
	{
		
		ArrayList<State> sArray = new ArrayList<State>();
		for (int i = 0; i < pArray.size(); i++) {
		State s=new State(this);
		String str= pArray.get(i);
		switch (str){
		
		case "RIGHT":
			moveState(s, "RIGHT");
			break;
		case "LEFT":
			moveState(s, "LEFT");
			break;
		case "UP":
			moveState(s, "UP");
			break;
		case "DOWN":
			moveState(s, "DOWN");
			break;
		case "BACK":
			moveState(s, "BACK");
			break;
		case "FORWARD":
			moveState(s, "FORWARD");
			break;
		}
		sArray.add(s);
		}
		return sArray;
	}

	/**
	 * To position generic.
	 *
	 * @param posInString the pos in string
	 * @return the position
	 */
	public Position stateToPosition (String posInString)
	{
		Position p = new Position();
		String[] seperatedArray = posInString.split(",");
		p.setX(Integer.parseInt(seperatedArray[0]));
		p.setZ(Integer.parseInt(seperatedArray[1]));
		p.setY(Integer.parseInt(seperatedArray[2]));
		return p;
	}

	public void moveState(State s, String move){

		Position p= stateToPosition(s.getstate());
		switch(move){
		case "RIGHT":{
			p.setX(p.getX()+1);
			s.setstate(p.toString());
			break;
		}
		case "LEFT":{
			p.setX(p.getX()-1);
			s.setstate(p.toString());
			break;
		}		
		case "UP":{
			p.setY(p.getY()+1);
			s.setstate(p.toString());
			break;
		}
		case "DOWN":{
			p.setY(p.getY()-1);
			s.setstate(p.toString());
			break;
		}
		case "FORWARD":{
			p.setZ(p.getZ()+1);
			s.setstate(p.toString());
			break;
		}
		case "BACK":{
			p.setZ(p.getZ()-1);
			s.setstate(p.toString());
			break;
		}
		}		


	}
}
