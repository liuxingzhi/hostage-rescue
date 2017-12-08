package maps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Console {
	public static void main(String[] args) {
		String file = "./src/maps/map1.txt";
		char[][] maze = readMaze(file);
		print(maze);
		int[][] next= {{0,1}, // right
					   {1,0}, // down
					   {0,-1},// left
					   {-1,0}};//up
		Deque que = new LinkedList();
		Node head;
		Node tail;
		boolean[][] book = new boolean[maze.length][maze[0].length];
		while(!que.isEmpty()) {
			
		}
	}
	public static void print(char[][] maze) {
		for(char[] line : maze) {
			for(char ch : line) {
				System.out.print(ch);
			}
			System.out.println();
		}
	}
	public static char[][] readMaze(String filename){
		String s = null;  
        StringBuffer sb = new StringBuffer();  
        File f = new File(filename);  
        if (f.exists()) {  
            try {  
                BufferedReader br =  
                    new BufferedReader(new InputStreamReader(new FileInputStream(f)));  
                while ((s = br.readLine()) != null) {  
                    sb.append(s);  
                    sb.append("\n");
                }  
//                System.out.println(sb);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        } else {  
            System.out.println("文件不存在!");  
        }  
//        System.out.println(sb);
        String[] strs = sb.toString().split("\n");
        char[][] maze = new char[strs.length][];
        for(int i = 0;i<maze.length;i++) {
        		maze[i] = strs[i].toCharArray();
        }
        return maze;
	}
}
