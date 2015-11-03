package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import states.State;


// TODO: Auto-generated Javadoc
/**
 * The Class Maze3d.
 */
public class Maze3d implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The maze3d. */
	private int[][][] maze3d;

	/** The visit arr. */
	private boolean[][][] visitArr;

	/** The levels. */
	private int rows /*x*/,cols/*z*/,levels/*y*/;

	/** The start position. */
	private Position startPosition;

	/** The goal position. */
	private Position goalPosition;

	/** The current position. */
	private Position currentPosition;

	/**
	 * Instantiates a new maze3d.
	 *
	 * @param rows the rows
	 * @param cols the cols
	 * @param levels the levels
	 */
	public Maze3d(int rows,int cols,int levels){
		this.rows=rows;
		this.cols=cols;
		this.levels=levels;
		this.maze3d=new int[rows][cols][levels]; 
		this.startPosition= new Position(0, 0, 0);
		this.goalPosition= new Position(5, 7, 3);
		this.currentPosition = new Position(0,0,0);
		this.visitArr = new boolean[rows][cols][levels];
		for (int[][] square : maze3d){
			for (int[] line : square){
				Arrays.fill(line, 1);
			}
		}
	}

	/**
	 * Instantiates a new maze3d.
	 *
	 * @param m the m
	 */
	public Maze3d(Maze3d m)
	{
		this.rows = m.rows;
		this.cols = m.cols;
		this.levels = m.levels;
		this.goalPosition = new Position(m.goalPosition);
		this.startPosition = new Position(m.startPosition);
		this.currentPosition = new Position(m.currentPosition);
		this.maze3d = m.maze3d;
	}

	public Maze3d(byte[] b){
		
		
		int index = 0 ;
		int r = b[index];
		index++;
		int c = b[index];
		index++;
		int l = b[index];
		index++;
		setRows(r);
		setCols(c);
		setLevels(l);
		int startX=b[index];
		index++;
		int startZ=b[index];
		index++;
		int startY=b[index];
		index++;
		startPosition = new Position(startX, startZ, startY);
		int goalX = b[index];
		index++;
		int goalZ = b[index];
		index++;
		int goalY = b[index];
		index++;
		goalPosition = new Position(goalX, goalZ, goalY);
		currentPosition=new Position(0,0,0);
		maze3d = new int[rows][cols][levels];

		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++) 
			{
				for (int k = 0; k < levels; k++)
				{
					int tempValue = b[index];
					this.maze3d[i][j][k] = tempValue;
					index++;
				}
			}
		}
	}



	/**
	 * Gets the start position.
	 *
	 * @return the start position
	 */
	public Position getStartPosition() {
		return startPosition;
	}

	/**
	 * Gets the goal position.
	 *
	 * @return the goal position
	 */
	public Position getGoalPosition() {
		return goalPosition;
	}

	/**
	 * Sets the start position.
	 *
	 * @param x the x
	 * @param z the z
	 * @param y the y
	 */
	public void setStartPosition(int x,int z,int y) {
		this.startPosition.x =x;
		this.startPosition.z =z;
		this.startPosition.y =y;
	}

	/**
	 * Sets the goal position.
	 *
	 * @param x the x
	 * @param z the z
	 * @param y the y
	 */
	public void setGoalPosition(int x,int z,int y) {
		this.goalPosition.x =x;
		this.goalPosition.z =z;
		this.goalPosition.y =y;

	}

	/**
	 * Gets the maze3d.
	 *
	 * @return the maze3d
	 */
	public int[][][] getMaze3d() {
		return maze3d;
	}

	/**
	 * Gets the rows.
	 *
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Gets the cols.
	 *
	 * @return the cols
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * Gets the levels.
	 *
	 * @return the levels
	 */
	public int getLevels() {
		return levels;
	}

	/**
	 * Sets the maze3d.
	 *
	 * @param maze3d the new maze3d
	 */
	public void setMaze3d(int[][][] maze3d) {
		this.maze3d=maze3d;
	}

	/**
	 * Sets the rows.
	 *
	 * @param rows the new rows
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * Sets the cols.
	 *
	 * @param cols the new cols
	 */
	public void setCols(int cols) {
		this.cols = cols;
	}

	/**
	 * Sets the levels.
	 *
	 * @param levels the new levels
	 */
	public void setLevels(int levels) {
		this.levels = levels;
	}


	/**
	 * Gets the current position.
	 *
	 * @return the current position
	 */
	public Position getCurrentPosition() {
		return currentPosition;
	}


	/**
	 * Sets the current position.
	 *
	 * @param x the x
	 * @param z the z
	 * @param y the y
	 */
	public void setCurrentPosition(int x,int z,int y) {
		this.currentPosition.x =x;
		this.currentPosition.z =z;
		this.currentPosition.y =y;
	}

	/**
	 * Sets the value.
	 *
	 * @param x the x
	 * @param z the z
	 * @param y the y
	 * @param value the value
	 */
	public void setValue(int x,int z,int y,int value){
		this.maze3d[x][z][y]=value;
	}

	/**
	 * Gets the value.
	 *
	 * @param x the x
	 * @param z the z
	 * @param y the y
	 * @return the value
	 */
	public int getValue(int x,int z,int y){
		return maze3d[x][z][y];
	}

	/**
	 * Gets the value by position.
	 *
	 * @param p the p
	 * @return the value by position
	 */
	public int getValueByPosition(Position p)
	{
		return this.maze3d[p.getX()][p.getZ()][p.getY()];
	}

	/**
	 * Sets the value by position.
	 *
	 * @param p the p
	 * @param value the value
	 */
	public void setValueByPosition(Position p,int value)
	{
		this.maze3d[p.getX()][p.getZ()][p.getY()] = value;
	}

	/**
	 * Gets the possible moves.
	 *
	 * @param p the p
	 * @return the possible moves
	 */
	public String[] getPossibleMoves(Position p){

		String moves= new String();

		if (isAvailable(Moves.RIGHT, p)) {

			moves = moves + "RIGHT,";
		}
		if (isAvailable(Moves.LEFT, p)) {

			moves= moves+ "LEFT,";
		}
		if (isAvailable(Moves.FORWARD, p)) {

			moves= moves+ "FORWARD,";
		}
		if (isAvailable(Moves.BACK, p)) {

			moves= moves+ "BACK,";
		}
		if (isAvailable(Moves.UP, p)) {

			moves= moves+ "UP,";
		}
		if (isAvailable(Moves.DOWN, p)) {

			moves= moves+ "DOWN,";
		}
		return moves.split(",");
	}

	/**
	 * Checks if is in bound.
	 *
	 * @param move the move
	 * @param p the p
	 * @return true, if is in bound
	 */
	public boolean isInBound(Moves move, Position p){
		switch (move){
		case UP:{
			if (p.y +1 < this.getLevels() - 1)
			{
				return true;	
			}
			break;
		}
		case DOWN:{
			if (p.y -1 >= 0)
			{
				return true;	
			}
			break;
		}
		case RIGHT:{
			if (p.x +1 < this.getRows() - 1)
			{
				return true;	
			}
			break;
		}

		case LEFT:{
			if (p.x -1 >= 0)
			{
				return true;	
			}

			break;
		}

		case FORWARD:{
			if (p.z +1 < this.getCols() - 1)
			{
				return true;	
			}
			break;
		}

		case BACK:{
			if (p.z -1 >= 0)
			{
				return true;	
			}
			break;
		}


		}
		return false;

	}

	/**
	 * Checks if is out of bound.
	 *
	 * @param x the x
	 * @param z the z
	 * @param y the y
	 * @return true, if is out of bound
	 */
	public boolean isOutOfBound(int x, int z, int y) {
		if(x < 0 || x > this.rows)
			return true;
		if(z < 0 || z > this.cols)
			return true;
		if(y < 0 || y > this.levels)
			return true;
		return false;
	}


	/**
	 * Checks if is available.
	 *
	 * @param move the move
	 * @param p the p
	 * @return true, if is available
	 */
	public boolean isAvailable(Moves move, Position p)
	{
		switch (move)
		{
		case UP:
		if(isInBound(move,p))
			if(this.maze3d[p.x][p.z][p.y+1]==0)
				return true;
		break;
		case DOWN:
		if(isInBound(move,p))
			if(this.maze3d[p.x][p.z][p.y-1]==0)
				return true;
		break;

		case LEFT:
		if(isInBound(move,p))
			if(this.maze3d[p.x-1][p.z][p.y]==0)
				return true;
		break;

		case RIGHT:
		if(isInBound(move,p))
			if(this.maze3d[p.x+1][p.z][p.y]==0)
				return true;
		break;

		case FORWARD:
		if(isInBound(move,p))
			if(this.maze3d[p.x][p.z+1][p.y]==0)
				return true;
		break;

		case BACK:
		if(isInBound(move,p))
			if(this.maze3d[p.x][p.z-1][p.y]==0)
				return true;
		break;
		}
		return false;

	}

	/**
	 * Gets the cross section by x.
	 *
	 * @param x the x
	 * @return the cross section by x
	 */
	public int[][] getCrossSectionByX(int x) {
		int[][] m1 = new int[cols][levels];
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < levels; j++) {
				m1[i][j] = maze3d[x][i][j];
			}
		}
		return m1;
	}

	/**
	 * Gets the cross section by y.
	 *
	 * @param y the y
	 * @return the cross section by y
	 */
	public int[][] getCrossSectionByY(int y) {
		int[][] m2 = new int[rows][levels];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < levels; j++) {
				m2[i][j] = maze3d[y][i][j];
			}
		}
		return m2;
	}

	/**
	 * Gets the cross section by z.
	 *
	 * @param z the z
	 * @return the cross section by z
	 */
	public int[][] getCrossSectionByZ(int z) {
		int[][] m3 = new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				m3[i][j] = maze3d[z][i][j];
			}
		}
		return m3;


	}

	/**
	 * Checks if is visited.
	 *
	 * @param x the x
	 * @param z the z
	 * @param y the y
	 * @return true, if is visited
	 */
	public boolean isVisited(int x, int z, int y){
		return visitArr[x][z][y];
	}

	/**
	 * Sets the visited.
	 *
	 * @param x the x
	 * @param z the z
	 * @param y the y
	 */
	public void setVisited(int x, int z, int y){

		this.visitArr[x][z][y]=true;
	}

	/**
	 * Print2d maze.
	 *
	 * @param crossSection the cross section
	 */
	public void print2dMaze(int[][] crossSection)
	{
		for (int i = 0; i < crossSection.length; i++)
		{
			System.out.print("    ");
			for (int j = 0; j < crossSection.length; j++)
			{

				System.out.print(crossSection[j][crossSection.length-i-1]);
				if (j+1 < crossSection.length)
				{
					System.out.print(",");
				}
			}
			System.out.println();
		}
		System.out.println();
	}



	/**
	 * Move position.
	 *
	 * @param p the p
	 * @param m the m
	 */
	public void movePosition(Position p, Moves m){

		switch (m){
		case UP: 
			p.y=p.y+1;
			break;
		case DOWN: 
			p.y=p.y-1;
			break;
		case RIGHT: 
			p.x=p.x+1;
			break;
		case LEFT: 
			p.x=p.x-1;
			break;
		case FORWARD: 
			p.z=p.z+1;
			break;
		case BACK: 
			p.z=p.z-1;
			break;


		}
	}

	/**
	 * Gets the possible moves in array list.
	 *
	 * @param s1 the s1
	 * @return the possible moves in array list
	 */
	public ArrayList<String> getPossibleMovesInArrayList(State s1)
	{
		Position p2 = new Position();
		p2 = s1.stateToPosition(s1.getstate());
		ArrayList<String> movesArrayList = new ArrayList<String>();
		String[] stringSet = getPossibleMoves(p2);
		for (int i = 0; i < stringSet.length; i++)
		{
			movesArrayList.add(stringSet[i]);
		}
		return movesArrayList;
	} 

	public byte[] toByteArray(){
		
		
		byte[] mazeSettings = new byte[rows*cols*levels+10];

		mazeSettings[0] = (byte) this.rows;
		mazeSettings[1] = (byte) this.cols;
		mazeSettings[2] = (byte) this.levels;
		mazeSettings[3] = (byte) this.startPosition.x;
		mazeSettings[4] = (byte) this.startPosition.z;
		mazeSettings[5] = (byte) this.startPosition.y;
		mazeSettings[6] = (byte) this.goalPosition.x;
		mazeSettings[7] = (byte) this.goalPosition.z;
		mazeSettings[8] = (byte) this.goalPosition.y;

		int index= 9;
		for (int i = 0; i < rows; i++) 
		{
			for (int j = 0; j < cols; j++) 
			{
				for (int k = 0; k < levels; k++) 
				{
					mazeSettings[index] = (byte) this.maze3d[i][j][k];
					index++;
				}
			}
		}
		return mazeSettings;
	}


	public boolean equals(Maze3d m) {
			if(this.rows==m.rows && 
					this.cols==m.cols && 
					this.levels==m.levels && 
					this.startPosition.equals(m.startPosition) &&
					this.goalPosition.equals(m.goalPosition)&&
					this.maze3d.length==m.getMaze3d().length){
				return true;
			}
			else
				return false;
	}
	
	public void displayMaze(Maze3d maze) //prints the entire maze
	{
		int bound = maze.getLevels();
		System.out.print("Printing the maze by ");
		System.out.print((bound));
		System.out.println(" Z levels: ");
		for (int i = 0; i <= bound; i++)
		{
			try
			{
				int[][] maze2dZ = maze.getCrossSectionByZ(i);
				maze.print2dMaze(maze2dZ);
			} 
			catch (Exception e) 
			{
			}
		}	
	}
	
	
}
