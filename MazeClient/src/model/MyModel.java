package model;

import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import presenter.Properties;
import solution.Solution;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3DGenerator;
import algorithms.mazeGenerators.SimpleMaze3DGenerator;

// TODO: Auto-generated Javadoc
/**
 * The Class MyModel.
 */
public class MyModel extends Observable implements Model {

	/** The maze list. */
	private HashMap<String, Maze3d> mazeList;

	/** The solution list. */
	private HashMap<Maze3d, Solution> solutionList;

	/** The command data. */
	private HashMap<String, Object> commandData;

	/** The prop. */
	Properties prop=new Properties();

	/** The executer. */
	ExecutorService executer;
	
	/** The my server. */
	Socket myServer;

	/** The ois. */
	ObjectInputStream ois;
	
	/** The out to server. */
	PrintWriter outToServer;

	/**
	 * Instantiates a new my model.
	 *
	 * @param numOfThreads the num of threads
	 * @param geneAlgo the gene algo
	 * @param solveAlgo the solve algo
	 * @param gInterface the g interface
	 */
	public MyModel(int numOfThreads, String geneAlgo, String solveAlgo,String gInterface) {
		this.commandData=new HashMap<String, Object>();
		this.mazeList= new HashMap<String, Maze3d>();
		this.solutionList= new HashMap<Maze3d, Solution>();
		this.prop.setNumOfThreads(numOfThreads);
		this.prop.setGenerateAlgo(geneAlgo);
		this.prop.setSolveAlgo(solveAlgo);
		this.prop.setGameInterface(gInterface);
		this.prop.savePreferences();

		executer = Executors.newFixedThreadPool(numOfThreads);
		try 
		{
			loadZip();
			changeAndNotify("loadZip", "Mazes has been loaded from file");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}


	/* (non-Javadoc)
	 * @see model.Model#getFilesInDirectory(java.lang.String)
	 */
	@Override
	public void getFilesInDirectory(String path) {
		File f = new File(path);
		File[] fList = f.listFiles();
		String[] fileNames = new String[1];
		if (fList == null) 
		{
			fileNames[0] = "No Files in these directory";
			changeAndNotify("dir", fileNames);
			return;
		}
		else
		{
			fileNames = new String[fList.length];
			for (int i = 0; i < fList.length; i++)
			{
				fileNames[i] = fList[i].getName();	
			}
			changeAndNotify("dir", fileNames);
		}

	}

	/* (non-Javadoc)
	 * @see model.Model#generate3DMaze(java.lang.String, int)
	 */
	@Override
	public void generate3DMaze(String name, int size) {
		Future<Maze3d> myMaze = executer.submit(new Callable<Maze3d>()
				{
			@Override
			public Maze3d call() throws Exception
			{
				Maze3dGenerator mg;
				if(prop.getGenerateAlgo()=="simple"){
					mg = new SimpleMaze3DGenerator();
				}
				else
					mg=new MyMaze3DGenerator();
				Maze3d myMaze = mg.generate(size, size, size);
				return myMaze;
			}
				});
		try 
		{
			getMazeList().put(name, myMaze.get());


		}
		catch (InterruptedException | ExecutionException e) 
		{

			e.printStackTrace();
		}
		changeAndNotify("generated", name);
	}

	/* (non-Javadoc)
	 * @see model.Model#getCrossSection(char, int, java.lang.String)
	 */
	@Override
	public void getCrossSection(char xyz, int index, String name) {
		if(getMazeList().containsKey(name))
		{
			Maze3d myMaze = new Maze3d(getMazeList().get(name));
			if (xyz == 'x' || xyz == 'X') 
			{
				changeAndNotify("crossed", myMaze.getCrossSectionByX(index));
				return;
			}
			if (xyz == 'y' || xyz == 'Y') 
			{
				changeAndNotify("crossed", myMaze.getCrossSectionByY(index));
				return;
			}
			if (xyz == 'z' || xyz == 'Z') 
			{
				changeAndNotify("crossed", myMaze.getCrossSectionByZ(index));
				return;
			}
			else 
			{
				changeAndNotify("notify", "bad X/Y/Z cord");
			}
		}
		else
		{
			changeAndNotify("notify", "Bad Maze Name (m.getcross)");
			return;
		}

	}

	/* (non-Javadoc)
	 * @see model.Model#saveMaze(java.lang.String, java.lang.String)
	 */
	@Override
	public void saveMaze(String mazeName, String fileName) {
		Maze3d myMaze = new Maze3d(getMazeList().get(mazeName));
		try 
		{
			OutputStream out=new MyCompressorOutputStream( new FileOutputStream(fileName));
			out.write(myMaze.toByteArray());
			out.flush();
			out.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		changeAndNotify("saved", mazeName);
	}

	/* (non-Javadoc)
	 * @see model.Model#loadMaze(java.lang.String, java.lang.String)
	 */
	@Override
	public void loadMaze(String mazeName, String fileName) {
		InputStream in;
		try 
		{
			in = new MyDecompressorInputStream( new FileInputStream(fileName));
			byte[] b = new byte[((MyDecompressorInputStream) in).getLength()];
			in.read(b);
			in.close();
			this.mazeList.put(mazeName, new Maze3d(b));
			changeAndNotify("loaded", mazeName);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see model.Model#MazeSize(java.lang.String)
	 */
	@Override
	public void MazeSize(String name) {
		double size =-5;
		if(getMazeList().containsKey(name))
		{
			Maze3d myMaze = new Maze3d(getMazeList().get(name));
			size = myMaze.toByteArray().length;
			commandData.put("maze", name);
			changeAndNotify("calcedMazeSize", size);
		}
		else
		{
			changeAndNotify("notify", "Bad Maze Name (m.calcmazesize)");
		}

	}

	/* (non-Javadoc)
	 * @see model.Model#FileSize(java.lang.String)
	 */
	@Override
	public void FileSize(String name) {
		File f = new File(name);
		if (f.length() == 0L)
		{
			this.saveMaze(name, "tempFileName");
			f = new File("tempFileName");
		}
		commandData.put("maze", name);
		changeAndNotify("calcedFileSize", f.length());


	}

	/* (non-Javadoc)
	 * @see model.Model#solveMaze(java.lang.String)
	 */
	@Override
	public void solveMaze(String name) {
		String algorithm = prop.getSolveAlgo();

		String mazeName = name;

		try 
		{
			myServer = new Socket(this.prop.getIp(), this.prop.getPort());
			ObjectOutputStream output = new ObjectOutputStream(myServer.getOutputStream());

			ArrayList<Object> messageToServer = new ArrayList<Object>();
			messageToServer.add("solve");
			messageToServer.add(algorithm);
			messageToServer.add(mazeList.get(mazeName));
			output.writeObject(messageToServer);
			output.flush();

			ObjectInputStream input = new ObjectInputStream(myServer.getInputStream());
			Solution messageFromServer = (Solution)input.readObject();

			if (messageFromServer == null) 
			{
				changeAndNotify("notify", "Bad Maze Name (m.solve)");
				output.close();
				input.close();
				myServer.close();
				return;
			}
			solutionList.put(mazeList.get(mazeName), messageFromServer);
			
			changeAndNotify("solved", name);
			
			myServer.getInputStream().close();
			myServer.getOutputStream().close();
			output.close();
			input.close();
			myServer.close();
			
		} catch (Exception e) 
		{
			changeAndNotify("notify", "Server might be closed");
		}

	}



	/* (non-Javadoc)
	 * @see model.Model#getMazeList()
	 */
	@Override
	public HashMap<String, Maze3d> getMazeList() {
		return this.mazeList;
	}

	/* (non-Javadoc)
	 * @see model.Model#getSolutionList()
	 */
	@Override
	public HashMap<Maze3d, Solution> getSolutionList() {

		return this.solutionList;
	}

	/* (non-Javadoc)
	 * @see model.Model#getCommandData()
	 */
	@Override
	public HashMap<String, Object> getCommandData() {

		return this.commandData;
	}

	/**
	 * Change and notify.
	 *
	 * @param command the command
	 * @param obj the obj
	 */
	private void changeAndNotify(String command, Object obj)
	{
		if (obj != null) 
		{
			this.commandData.put(command, obj);
		}
		setChanged();
		notifyObservers(command);
	}

	/* (non-Javadoc)
	 * @see model.Model#officialExit()
	 */
	@Override
	public void officialExit() 
	{
		executer.shutdown();
		try 
		{
			saveZip();
			changeAndNotify("saveZip", "File has been saved");
			executer.awaitTermination(59, TimeUnit.SECONDS);
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		executer.shutdownNow();
		changeAndNotify("quit", "Official Exit");
	}

	/* (non-Javadoc)
	 * @see model.Model#saveZip()
	 */
	public void saveZip()
	{
		try
		{
			ObjectOutputStream zipMaze = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("mazeSolutionCache.gzip")));

			zipMaze.writeObject(mazeList);
			zipMaze.writeObject(solutionList);
			zipMaze.flush();
			zipMaze.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see model.Model#loadZip()
	 */
	@SuppressWarnings("unchecked")
	public void loadZip()
	{
		File myFile = new File("mazeSolutionCache.gzip");
		try
		{
			if(!myFile.exists()){
				myFile.createNewFile();
				this.solutionList = new HashMap<Maze3d, Solution>();
			}
			else
			{
				FileInputStream fis = new FileInputStream(myFile);

				GZIPInputStream gzis = new GZIPInputStream(fis);
				ObjectInputStream mazeZip = new ObjectInputStream(gzis);

				this.mazeList = (HashMap<String, Maze3d>) mazeZip.readObject();
				this.solutionList = (HashMap<Maze3d, Solution>) mazeZip.readObject();

				mazeZip.close();
			} 
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see model.Model#changeProp(java.lang.String[])
	 */
	@Override
	public void changeProp(String[] newProp) {
		prop.setNumOfThreads(Integer.parseInt(newProp[0]));
		prop.setGenerateAlgo(newProp[1]);
		prop.setSolveAlgo(newProp[2]);
		prop.setGameInterface(newProp[3]);
		prop.setPort(Integer.parseInt(newProp[4]));
		prop.setIp(newProp[5]);
		prop.savePreferences();
		
		
	}


}
