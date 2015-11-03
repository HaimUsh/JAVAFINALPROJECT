package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

// TODO: Auto-generated Javadoc
/**
 * The Class MazeBoard.
 */
public class MazeBoard extends Canvas {

	

		/** The maze. */
		private Maze3d maze3d;

		/** The maze data. */
		private int[][][] mazeData;

		/** The character p. */
		private Position characterP;

		/** The character. */


		/** The is done. */
		private boolean isDone;

		/** The is solve. */
		private boolean isSolve;

		/** The new game. */
		private boolean newGame;

		/** The start p. */
		private Position startP;

		/**
		 * Instantiates a new maze2d displayer. Paint listener will paint it all
		 * over again when something has changed
		 *
		 * @param parent            the parent
		 * @param style            the style
		 */
		MazeBoard(Composite parent, int style) 
		{
			super(parent, style);	
		}

		/**
		 * Gets the maze data.
		 *
		 * @return the maze data
		 */
		public int[][][] getMazeData() {
			return mazeData;
		}

		/**
		 * Sets the maze data.
		 *
		 * @param mazeData the new maze data
		 */
		public void setMazeData(int[][][] mazeData) {
			this.mazeData = mazeData;
		}

		/**
		 * Checks if is done.
		 *
		 * @return true, if is done
		 */
		public boolean isDone() {
			return isDone;
		}

		/**
		 * Sets the done.
		 *
		 * @param isDone the new done
		 */
		public void setDone(boolean isDone) {
			this.isDone = isDone;
		}

		/**
		 * Checks if is solve.
		 *
		 * @return true, if is solve
		 */
		public boolean isSolve() {
			return isSolve;
		}

		/**
		 * Sets the solve.
		 *
		 * @param isSolve the new solve
		 */
		public void setSolve(boolean isSolve) {
			this.isSolve = isSolve;
		}

		/**
		 * Checks if is new game.
		 *
		 * @return true, if is new game
		 */
		public boolean isNewGame() {
			return newGame;
		}

		/**
		 * Sets the new game.
		 *
		 * @param newGame the new new game
		 */
		public void setNewGame(boolean newGame) {
			this.newGame = newGame;
		}

		/**
		 * Gets the start p.
		 *
		 * @return the start p
		 */
		public Position getStartP() {
			return startP;
		}

		/**
		 * Sets the start p.
		 *
		 * @param startP the new start p
		 */
		public void setStartP(Position startP) {
			this.startP = startP;
		}

		/**
		 * Sets the character p.
		 *
		 * @param characterP the new character p
		 */
		public void setCharacterP(Position characterP) {
			this.characterP = characterP;
		}

		/**
		 * Get character position.
		 *
		 * @return the character p
		 */
		public Position getCharacterP() {
			return this.characterP;
		}

		/**
		 * Gets the maze. 
		 * @return the maze
		 */
		public Maze3d getMaze() {
			return maze3d;
		}

		/**
		 * Sets the maze. 
		 * @param maze
		 * the new maze
		 */
		public void setMaze(Maze3d maze) {
			this.newGame = true;
			this.isDone = false;
			this.maze3d = maze;

			getDisplay().syncExec(new Runnable() {

				@Override
				public void run() {
					redraw();
				}
			});
		}

		/**
		 * Redraw character.
		 */
		private void moveCharacter() {
			getDisplay().syncExec(new Runnable() {
				@Override
				public void run() {
					redraw();
				}
			});
		}

		/**
		 * Move character position.
		 * @param p
		 *  the p
		 */
		public void setCharacterPosition(Position p) {
			characterP.setX(p.getX());
			characterP.setZ(p.getZ());
			characterP.setY(p.getY());
			moveCharacter();
		
		}

		/**
		 * Show exception box.
		 * @param e
		 * the exception
		 */
		public void showExceptionBox(Exception e) {
			MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_ERROR | SWT.OK);
			messageBox.setMessage(e.getMessage());
			messageBox.setText("Error");
			messageBox.open();
		}


		
}