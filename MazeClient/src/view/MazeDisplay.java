package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import solution.Solution;
import algorithms.mazeGenerators.Moves;
import algorithms.mazeGenerators.Position;

// TODO: Auto-generated Javadoc
/**
 * The Class MazeDisplay.
 */
@SuppressWarnings("unused")
public class MazeDisplay extends CommonMazeDisplay 
{
	
	/** The troll. */
	GameCharacter troll;
	//FinishGesture win = new FinishGesture();

	/** The win. */
	Image win;
	
	/** The flag. */
	private boolean flag = false;
	
	/**
	 * Instantiates a new maze display.
	 *
	 * @param composite the composite
	 * @param style the style
	 */
	MazeDisplay(Composite composite, int style) 
	{
		super(composite, style);
		try {
			win= new Image(getDisplay(), new FileInputStream("./resources/win.png"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		addPaintListener(new PaintListener()
		{
			@Override
			public void paintControl(PaintEvent e)
			{
				int startFromX = 0;
				int startFromZ = 0;

				int min=0;

				
				if(getSize().y < getSize().x)
					min= getSize().y+2;
				else 
					min = getSize().x+2;

				if(getMyMaze() != null)
				{

					int levelSelected = getMyMaze().getCurrentPosition().getY();

					int cellSizeX= getSize().x/getMyMaze().getRows();
					int cellSizeZ= getSize().y/getMyMaze().getCols();
					
					
					e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_DARK_GRAY));
					e.gc.fillRectangle(0, 0, cellSizeX, cellSizeZ);

					for(int x = 0;x<getMyMaze().getRows();x++)
					{
						for (int z = 0; z < getMyMaze().getCols(); z++)
						{
							{
								startFromX = x*cellSizeX;
								startFromZ = z*cellSizeZ;
								e.gc.drawRectangle(startFromX, startFromZ, cellSizeX, cellSizeZ);

								if(getMyMaze().getValue(x, z, levelSelected) ==0)
								{
									e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_WHITE));
									e.gc.fillRectangle(startFromX, startFromZ, cellSizeX, cellSizeZ);
									if (levelSelected < (getMyMaze().getLevels()-1))
										if(getMyMaze().getValue(x, z, levelSelected+1)==0)
										{
											e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_GRAY));
											e.gc.fillRectangle(startFromX, startFromZ, cellSizeX, cellSizeZ);

										}

										else{}
									else{}
									if (levelSelected > 0)
										if(getMyMaze().getValue(x, z, levelSelected-1)==0)
										{
											e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_BLACK));
											e.gc.fillOval(startFromX, startFromZ, cellSizeX, cellSizeZ);
										}
										else{}
									else{}
									if ((levelSelected < (getMyMaze().getLevels()-1))&&(levelSelected > 0))
										if((getMyMaze().getValue(x, z, levelSelected-1)==0)&&(getMyMaze().getValue(x, z, levelSelected+1)==0))
										{
											e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_GRAY));
											e.gc.fillRectangle(startFromX, startFromZ, cellSizeX, cellSizeZ);
											e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_BLACK));
											e.gc.fillOval(startFromX, startFromZ, cellSizeX, cellSizeZ);

										}
										else{}
									else{}
								}
								else
								{
									e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_DARK_GRAY));
									e.gc.fillRectangle(startFromX+1, startFromZ+1, cellSizeX-1, cellSizeZ-1);
									e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_WHITE));
									e.gc.fillRectangle(startFromX+1, startFromZ+1, cellSizeX-3, cellSizeZ-3);
									e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_DARK_GRAY));
									e.gc.fillRectangle(startFromX+1, startFromZ+1, cellSizeX-5, cellSizeZ-5);
									e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_WHITE));
									e.gc.fillRectangle(startFromX+1, startFromZ+1, cellSizeX-7, cellSizeZ-7);
									e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_DARK_GRAY));
									e.gc.fillRectangle(startFromX+1, startFromZ+1, cellSizeX-9, cellSizeZ-9);
								}
							}
						}
					}
					if((getMyMaze().getStartPosition().getY()) == levelSelected)
					{
						e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_BLUE));
						e.gc.fillRectangle(getMyMaze().getStartPosition().getX()*cellSizeX, getMyMaze().getStartPosition().getZ()*cellSizeZ, cellSizeX, cellSizeZ);

					}
					if ((getMyMaze().getCurrentPosition().getY()) == levelSelected) 
					{
						e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_YELLOW));
						e.gc.fillOval(getMyMaze().getCurrentPosition().getX()*cellSizeX, getMyMaze().getCurrentPosition().getZ()*cellSizeZ, cellSizeX, cellSizeZ);

					}
					if ((getMyMaze().getGoalPosition().getY()) == levelSelected) 
					{
						e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_RED));
						e.gc.fillRectangle(getMyMaze().getGoalPosition().getX()*cellSizeX, getMyMaze().getGoalPosition().getZ()*cellSizeZ, cellSizeX, cellSizeZ);
					}

					if (((getMyMaze().getCurrentPosition().getX()) == (getMyMaze().getGoalPosition().getX()))&&((getMyMaze().getCurrentPosition().getZ()) == (getMyMaze().getGoalPosition().getZ()))&&((getMyMaze().getCurrentPosition().getY()) == (getMyMaze().getGoalPosition().getY())))
					{
						e.gc.drawImage(win, win.getBounds().x, win.getBounds().y);
					}

					setBackground(e.display.getSystemColor(SWT.COLOR_WHITE));
				}
			}
		});

		addListener(SWT.Resize ,new Listener(){

			@Override
			public void handleEvent(Event arg0) {

				redraw();
			}

		});
	}

	/* (non-Javadoc)
	 * @see view.CommonMazeDisplay#moveUp()
	 */
	@Override
	public void moveUp()
	{
		
		flag = getMyMaze().isAvailable(Moves.UP	,getMyMaze().getCurrentPosition());

		if (flag)
		{
			getMyMaze().movePosition(getMyMaze().getCurrentPosition(), Moves.UP);
			redraw();
		}
	}

	/* (non-Javadoc)
	 * @see view.CommonMazeDisplay#moveDown()
	 */
	@Override
	public void moveDown()
	{
		
		flag = getMyMaze().isAvailable(Moves.DOWN, getMyMaze().getCurrentPosition());

		if (flag)
		{
			getMyMaze().movePosition(getMyMaze().getCurrentPosition(), Moves.DOWN);
			redraw();
		}
	}

	/* (non-Javadoc)
	 * @see view.CommonMazeDisplay#moveLeft()
	 */
	@Override
	public void moveLeft()
	{
		

		flag = getMyMaze().isAvailable(Moves.LEFT,getMyMaze().getCurrentPosition());

		if (flag)
		{
			getMyMaze().movePosition(getMyMaze().getCurrentPosition(), Moves.LEFT);
			redraw();
		}

	}

	/* (non-Javadoc)
	 * @see view.CommonMazeDisplay#moveRight()
	 */
	@Override
	public void moveRight()
	{
		
		flag = getMyMaze().isAvailable(Moves.RIGHT, getMyMaze().getCurrentPosition());

		if (flag)
		{
			getMyMaze().movePosition(getMyMaze().getCurrentPosition(), Moves.RIGHT);
			redraw();
		}
	}

	/* (non-Javadoc)
	 * @see view.CommonMazeDisplay#moveForward()
	 */
	@Override
	public void moveForward() 
	{
		flag = getMyMaze().isAvailable(Moves.FORWARD, getMyMaze().getCurrentPosition());

		if (flag)
		{
			getMyMaze().movePosition(getMyMaze().getCurrentPosition(), Moves.FORWARD);
			redraw();
		}

	}

	/* (non-Javadoc)
	 * @see view.CommonMazeDisplay#moveBack()
	 */
	@Override
	public void moveBack() 
	{
		flag = getMyMaze().isAvailable(Moves.BACK, getMyMaze().getCurrentPosition());

		if (flag)
		{
			getMyMaze().movePosition(getMyMaze().getCurrentPosition(), Moves.BACK);
			redraw();
		}
	}

	/* (non-Javadoc)
	 * @see view.CommonMazeDisplay#updateCurrentPosition(algorithms.mazeGenerators.Position)
	 */
	@Override
	public void updateCurrentPosition(Position _p) 
	{
		getMyMaze().setCurrentPosition(_p.getX(), _p.getZ(), _p.getY());
		redraw();
	}
}
