package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Moves;

// TODO: Auto-generated Javadoc
/**
 * The Class GameCharacter.
 */
public class GameCharacter implements BasicCharacter {


	/** The y. */
	int x, y;

	/** The char right. */
	Image charFront, charBack, charLeft, charRight;

	/** The moves. */
	Moves moves;

	/**
	 * Instantiates a new game character.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public GameCharacter(int x, int y) {
		this.x=x;
		this.y=y;

		try {
			charFront= new Image(null, new FileInputStream("resources/front.png"));
			charBack= new Image(null, new FileInputStream("resources/back.png"));
			charLeft= new Image(null, new FileInputStream("resources/left.png"));
			charRight= new Image(null, new FileInputStream("resources/right.png"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}




	}

	/* (non-Javadoc)
	 * @see view.BasicCharacter#moveChar(org.eclipse.swt.graphics.GC, int, int, int, int)
	 */
	@Override
	public void moveChar(GC gc, int pointX, int pointZ, int width, int height) 
	{

		gc.drawImage(charFront, 0, 0, 256, 256, pointX, pointZ, width, height);
		


	}



	/**
	 * Move char2.
	 *
	 * @param gc the gc
	 * @param pointX the point x
	 * @param pointZ the point z
	 * @param width the width
	 * @param height the height
	 */
	public void moveChar2(GC gc, int pointX, int pointZ, int width, int height) {
		switch(moves){

		case FORWARD: 

			gc.drawImage(charFront, 0, 0, 256, 256, pointX, pointZ, width, height);
			break;

		case BACK: 

			gc.drawImage(charFront, 0, 0, 256, 256, pointX, pointZ, width, height);
			break;

		case LEFT: 

			gc.drawImage(charFront, 0, 0, 256, 256, pointX, pointZ, width, height);
			break;

		case RIGHT: 

			gc.drawImage(charFront, 0, 0, 256, 256, pointX, pointZ, width, height);
			break;
		default:
			break;

		}

	}




}


