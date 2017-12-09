package maps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class Console {

	public static void main(String[] args) {
		String file = "./src/maps/map4.txt";
		Maze m1 = new Maze(readMaze(file));
		System.out.println(m1.path);
		m1.print();	
		Node tail = m1.dfs();
		m1.printPath();
	}

	public static char[][] copy(char[][] copy) {
		char[][] maze = new char[copy.length][copy[0].length];
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				maze[i][j] = copy[i][j];
			}
		}
		return maze;
	}

	public static void print(char[][] maze) {
		for (char[] line : maze) {
			for (char ch : line) {
				System.out.print(ch);
			}
			System.out.println();
		}
	}

	public static char[][] readMaze(String filename) {
		String s = null;
		StringBuffer sb = new StringBuffer();
		File f = new File(filename);
		if (f.exists()) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
				while ((s = br.readLine()) != null) {
					sb.append(s);
					sb.append("\n");
				}
				// System.out.println(sb);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("file does not exist!");
		}
		// System.out.println(sb);
		String[] strs = sb.toString().split("\n");
		char[][] maze = new char[strs.length][];
		for (int i = 0; i < maze.length; i++) {
			maze[i] = strs[i].toCharArray();
		}
		return maze;
	}
}
