package maps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Console {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("which number of map would you like to use");
		int num = scan.nextInt();
		String file = "./src/maps/map" + num + ".txt";
		System.out.println("which kind of rescue play would you to use?");
		System.out.println("1. breadth first search");
		System.out.println("2. depth first search");
		Maze map = new Maze(readMaze(file));
		int method = scan.nextInt();
		scan.close();
		Node tail = null;
		switch (method) {
		case 1:
			System.out.println("breadth first search implemented by queue");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tail = map.bfs();
			System.out.println("this gives the shortest path");
			break;
		case 2:
			System.out.println("depth first search implemented by recursion");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tail = map.dfs();
			break;
		case 3:
			System.out.println("depth first search implemented by stack");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tail = map.stackdfs();
			break;
		}
		map.printPath(tail);
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
				br.close();
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
