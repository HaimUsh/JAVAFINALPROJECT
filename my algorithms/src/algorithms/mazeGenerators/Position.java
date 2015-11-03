package algorithms.mazeGenerators;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Position.
 */
public class Position implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** The y. */
	int x,z,y;
	
	/**
	 * Instantiates a new position.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public Position(int x,int z,int y){
		this.x=x;
		this.z=z;
		this.y=y;
	}
	
	/**
	 * Instantiates a new position.
	 *
	 * @param p the p
	 */
	public Position(Position p) {
		this.x=p.x;
		this.z=p.z;
		this.y=p.y;
	}

	/**
	 * Instantiates a new position.
	 */
	public Position(){
		this.x=0;
		this.z=0;
		this.y=0;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		
		return this.x+","+this.z+","+this.y;
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the z.
	 *
	 * @return the z
	 */
	public int getZ() {
		return z;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Sets the z.
	 *
	 * @param z the new z
	 */
	public void setZ(int z) {
		this.z = z;
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Sets the positin.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public void setPositin(int x, int z, int y){
		this.x=x;
		this.z=z;
		this.y=y;
	}
	
	/**
	 * Copy position.
	 *
	 * @param p the p
	 */
	public void copyPosition(Position p){
		this.x=p.x;
		this.z=p.z;
		this.y=p.y;
	}

	public boolean equals(Position p) {
		if ((this.x == p.x) && (this.z == p.z) && (this.y == p.y))
		{
			return true;
		}
		return false;
	}


	

}
