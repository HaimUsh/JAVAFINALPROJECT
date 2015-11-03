package view;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;

import solution.Solution;
import states.State;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;


// TODO: Auto-generated Javadoc
/**
 * The Class MazeWindow.
 */
public class MazeWindow extends BasicWindow implements Closeable {


	/** The properties file path. */
	private String propertiesFilePath;
	
	/** The msg. */
	private MessageBox msg;
	
	/** The maze display. */
	private CommonMazeDisplay mazeDisplay;
	
	/** The maze. */
	private Maze3d maze;
	
	/** The maze name. */
	private String mazeName;
	
	/** The Solution. */
	private Solution Solution;
	
	/** The verify data1. */
	private boolean verifyData1 = false;
	
	/** The show sol. */
	public boolean showSol = true;
	
	/** The time. */
	private Timer time;
	
	/** The my task. */
	private TimerTask myTask;
	
	/** The back image. */
	Image backImage; 
	

	/**
	 * Instantiates a new maze window.
	 *
	 * @param title the title
	 * @param width the width
	 * @param height the height
	 */
	public MazeWindow(String title, int width, int height)
	{
		super(title, width, height);
		this.mazeName = new String(" No Maze");

	}

	/* (non-Javadoc)
	 * @see view.View#start()
	 */
	@Override
	public void start()
	{
		run();
	}

	/* (non-Javadoc)
	 * @see view.View#display(java.lang.String)
	 */
	@Override
	public void display(String userInput){} 
	
	/* (non-Javadoc)
	 * @see view.View#displayDirectory(java.lang.String[])
	 */
	@Override
	public void displayDirectory(String[] path){}
	
	/* (non-Javadoc)
	 * @see view.View#displayMenu()
	 */
	@Override
	public void displayMenu(){}
	
	/* (non-Javadoc)
	 * @see view.View#displayCrossSection(int[][])
	 */
	@Override
	public void displayCrossSection(int[][] crossSection){}
	
	/* (non-Javadoc)
	 * @see view.View#displaySize(java.lang.String, double)
	 */
	@Override
	public void displaySize(String name, double Size) {}
	
	/* (non-Javadoc)
	 * @see view.View#displayMaze(algorithms.mazeGenerators.Maze3d)
	 */
	@Override
	public void displayMaze(Maze3d maze)
	{
		displayMazeGUI(maze);
	}
	
	/* (non-Javadoc)
	 * @see view.View#displayMazeGUI(algorithms.mazeGenerators.Maze3d)
	 */
	public void displayMazeGUI(Maze3d maze)
	{
		if (maze != null) 
		{
			setMyViewMaze(maze);
			mazeDisplay.setMyMaze(maze);
			mazeDisplay.redraw();
			mazeDisplay.forceFocus();
		}
	}
	
	/* (non-Javadoc)
	 * @see view.View#setShowSol(boolean)
	 */
	@Override
	public void setShowSol(boolean solved){
		 this.showSol=solved;
	}


	/* (non-Javadoc)
	 * @see view.View#displaySolution(solution.Solution)
	 */
	@Override
	public void displaySolution(Solution s){
		Solution.print();
	}
	
	/* (non-Javadoc)
	 * @see view.BasicWindow#initWidgets()
	 */
	@Override
	void initWidgets()
	{
		Color color = new Color(display, 55, 45, 35);
		shell.setLayout(new GridLayout(1, true));
		shell.setBackground(color);
	
		shell.addListener(SWT.Close, new Listener() 
		{
			@Override
			public void handleEvent(Event arg0)
			{
				int style = SWT.APPLICATION_MODAL | SWT.YES | SWT.NO;
				setChanged();
				notifyObservers("exit");
				msg = new MessageBox(shell, style);
				msg.setText("Information");
				msg.setMessage("Are you sure?");
				arg0.doit = msg.open() == SWT.YES;
			}
		});

		Menu menuBar = new Menu(shell, SWT.BAR);
		Menu fileMenu = new Menu(menuBar);

		MenuItem fileItem = new MenuItem(menuBar, SWT.CASCADE);
		fileItem.setText("File");
		fileItem.setMenu(fileMenu);

		MenuItem newItem = new MenuItem(fileMenu, SWT.CASCADE);
		newItem.setText("New");

		Menu popupMenu = new Menu(newItem);
		newItem.setMenu(popupMenu);

		Menu newMenu = new Menu(popupMenu);
		newItem.setMenu(newMenu);

		MenuItem maze3DItem = new MenuItem(newMenu, SWT.NONE);
		maze3DItem.setText("Generate 3D Maze");

		MenuItem propItem = new MenuItem(fileMenu, SWT.CASCADE);
		propItem.setText("Properties");

		Menu popupMenu2 = new Menu(propItem);
		propItem.setMenu(popupMenu2);

		Menu openMenu = new Menu(popupMenu2);
		propItem.setMenu(openMenu);

		MenuItem openItem = new MenuItem(openMenu, SWT.NONE);
		openItem.setText("Open properties");

		MenuItem setPItem = new MenuItem(openMenu, SWT.NONE);
		setPItem.setText("Set Properties");

		MenuItem saveItem = new MenuItem(fileMenu, SWT.NONE);
		saveItem.setText("Save");

		MenuItem exitItem = new MenuItem(fileMenu, SWT.NONE);
		exitItem.setText("Exit");

		Menu solveMenu = new Menu(menuBar);
		MenuItem solveItem = new MenuItem(menuBar, SWT.CASCADE);
		solveItem.setText("Solve Maze");
		solveItem.setMenu(solveMenu);

		MenuItem m3DSolveItem = new MenuItem(solveMenu , SWT.NONE);
		m3DSolveItem.setText("Solve Maze");
		m3DSolveItem.setEnabled(false);

		MenuItem m3DHintItem = new MenuItem(solveMenu , SWT.NONE);
		m3DHintItem.setText("Get A Hint");
		m3DHintItem.setEnabled(false);

		MenuItem showSolution = new MenuItem(solveMenu, SWT.NONE);
		showSolution.setText("show solution");
		showSolution.setEnabled(false);

		shell.setMenuBar(menuBar);
		try {
			backImage = new Image(null, new FileInputStream("resources/background.png"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mazeDisplay = new MazeDisplay(shell, SWT.BORDER | SWT.DOUBLE_BUFFERED);
		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		PaintListener pl = new PaintListener(){

			@Override
			public void paintControl(PaintEvent e) {
				e.gc.drawImage(backImage,0,0, backImage.getBounds().width, backImage.getBounds().height, 
						0, 0, shell.getSize().x, shell.getSize().y);
			}

		};
		mazeDisplay.addPaintListener(pl);
		mazeDisplay.setMyMaze(maze);
		mazeDisplay.redraw();
		mazeDisplay.forceFocus();

		maze3DItem.addSelectionListener(new SelectionListener()
		{
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0){}
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				MazeSettings fd=new MazeSettings(shell);
				fd.setText("open");
				String newValue = new String(fd.open());
				String line = new String("generate");
				line = line+" "+newValue;
				String [] splittedLine = line.split(" ");
				int num = splittedLine.length;
				boolean flag = true;
				if (num == 3) 
				{
					if (!splittedLine[1].equals("unNamed Maze")) 
					{
						if (splittedLine[1] == null)
						{
							flag = false;
						}
						if (Integer.parseInt(splittedLine[2]) < 1) 
						{
							flag = false;
						}
						if (flag == true)
						{
							mazeDisplay.removePaintListener(pl);
							setChanged();
							notifyObservers(line);
							mazeDisplay.redraw();
							m3DSolveItem.setEnabled(true);
							m3DHintItem.setEnabled(true);	
						}
					}
				}
			}
		});

		openItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog dlg = new FileDialog(shell, SWT.OPEN);
				@SuppressWarnings("unused")
				String fileName = dlg.open();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}

		});

		setPItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				MazeProperties mp = new MazeProperties(shell);
				mp.setText("open");

				String newValue = new String(mp.open());
				String line = new String("changeProp ");
				line = line+newValue;
				if (line.split(" ").length == 5)
				{
					setChanged();
					notifyObservers(line);
					msg = new MessageBox(shell);
					msg.setText("NOTICE");
					msg.setMessage("You can't set new properties!");
					msg.open();
				}


			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});


		saveItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fd=new FileDialog(shell,SWT.SAVE);
				fd.setText("Save");
				fd.setFilterPath("");

				String[] filterExt = { "*.maz", "*.MAZ", "*.*" };
				fd.setFilterExtensions(filterExt);
				propertiesFilePath = fd.open();
				if (propertiesFilePath != null)
				{
					String line = new String("save "+mazeName);
					line = line+" "+propertiesFilePath;
					setChanged();
					notifyObservers(line);
				}
				System.out.println("Save to: " + fd.open());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		exitItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {

				setChanged();
				notifyObservers("exit");
				shell.getDisplay().dispose();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		m3DHintItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (verifyData1) 

					if (Solution.getSolutionList().size() > 1)
					{
						String move = Solution.getSolutionList().get(1);
						State tempState = new State();
						Position tempPos = tempState.stateToPosition(move);
						mazeDisplay.updateCurrentPosition(tempPos);
						Solution.getSolutionList().remove(0);
						mazeDisplay.forceFocus();
					}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});


		m3DSolveItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {

				String line = new String("solve");
				line = line+" "+mazeName;
				setChanged();
				notifyObservers(line);
				showSolution.setEnabled(showSol);

			}});


		showSolution.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {

	
				time = new Timer();
				myTask = new TimerTask() {

					@Override
					public void run() {
						display.asyncExec(new Runnable() {

							@Override
							public void run() {
								if (Solution.getSolutionList().size() > 1)
								{
									String move = Solution.getSolutionList().get(1);
									State tempState = new State();
									Position tempPos = tempState.stateToPosition(move);
									mazeDisplay.updateCurrentPosition(tempPos);
									Solution.getSolutionList().remove(0);
									mazeDisplay.forceFocus();
								}
								else
									time.cancel();

							}
						});

					}
				};
				time.scheduleAtFixedRate(myTask, 0, 2000);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		mazeDisplay.addKeyListener(new KeyListener()
		{

			@Override
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyPressed(KeyEvent e) 
			{
				if (e.keyCode == SWT.ARROW_UP) 
				{
					mazeDisplay.moveBack();
					mazeDisplay.redraw();
				}
				else if (e.keyCode == SWT.ARROW_DOWN) 
				{
					mazeDisplay.moveForward();
					mazeDisplay.redraw();
				}
				else if (e.keyCode == SWT.ARROW_RIGHT)
				{
					mazeDisplay.moveRight();
					mazeDisplay.redraw();
				}
				else if (e.keyCode == SWT.ARROW_LEFT) 
				{
					mazeDisplay.moveLeft();
					mazeDisplay.redraw();
				} 
				else if (e.keyCode == SWT.PAGE_UP)
				{
					mazeDisplay.moveUp();
					mazeDisplay.redraw();
				}
				else if (e.keyCode == SWT.PAGE_DOWN) 
				{
					mazeDisplay.moveDown();
					mazeDisplay.redraw();
				}
			}
		});
	}

	/* (non-Javadoc)
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() throws IOException
	{
		setChanged();
		notifyObservers("exit");
		if (display!=null&&(!display.isDisposed()))
		{
			display.dispose();
		}

		if(shell!=null&&(!shell.isDisposed()))
		{
			shell.dispose();
		}
	}

	/* (non-Javadoc)
	 * @see view.View#setMazeName(java.lang.String)
	 */
	@Override
	public void setMazeName(String name)
	{
		this.mazeName = new String (name);
	}
	
	/* (non-Javadoc)
	 * @see view.View#setSolution(solution.Solution)
	 */
	public void setSolution(Solution solution)
	{
		if (solution != null)
		{
			this.setMySolution(solution);	
			this.verifyData1 = true;;	
		}
		else
			System.out.println("NO SOLUTION");
	}
	
	/**
	 * Sets the my view maze.
	 *
	 * @param maze the new my view maze
	 */
	public void setMyViewMaze(Maze3d maze)
	{
		this.maze = maze;

	}
	
	/**
	 * Gets the my solution.
	 *
	 * @return the my solution
	 */
	public Solution getMySolution() 
	{
		return Solution;
	}
	
	/**
	 * Sets the my solution.
	 *
	 * @param mySolution the new my solution
	 */
	public void setMySolution(Solution mySolution) 
	{
		this.Solution = mySolution;
	}
}
