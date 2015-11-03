package algorithms.mazeGenerators;

import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Run {

	public static void main(String[] args) throws IOException {
	
		Maze3dGenerator mg= new MyMaze3DGenerator();
		
		Maze3d maze=mg.generate(4, 4, 4); 
		// save it to a file
		OutputStream out=new MyCompressorOutputStream(new FileOutputStream("1.maz"));
		out.write(maze.toByteArray());
		out.flush();
		out.close();
		InputStream in=new MyDecompressorInputStream(new FileInputStream("1.maz"));
		byte b[]=new byte[maze.toByteArray().length];
		in.read(b);
		in.close();
		Maze3d loaded=new Maze3d(b);
		System.out.println(loaded.equals(maze));

	}

}
