package maps;

import java.util.Deque;
import java.util.LinkedList;

public class Maze {
	private int startCol = 0;
	private int startRow = 0;
	private int endCol = 0;
	private int endRow = 0;
	private int height = 0;
	private int len = 0;
	private Node path = null;

	//directions
	private final int[][] next = 
			{ { 0, 1 }, // right
			{ 1, 0 }, // down
			{ 0, -1 }, // left
			{ -1, 0 } };// up
	private final char[] dir = { '>', 'V', '<', '^' };

	private char[][] maze;
	private char[][] copy; // back up of maze

	public Maze(char[][] src) {
		height = src.length;
		len = src[0].length;
		maze = new char[height][len];
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				maze[i][j] = src[i][j];
				if (maze[i][j] == 'S') {
					startRow = i;
					startCol = j;
				}
				if (maze[i][j] == 'P') {
					endRow = i;
					endCol = j;
				}
			}
		}
		copy = Maze.copy(src);
	}

	/**depth first search, wrapper method (recursion)*/
	public Node dfs() {
		Node head = new Node(startRow, startCol, 0, null, 'S');
		dfs(head);
		return new Node(path);
	}

	/**inner method*/
	private void dfs(Node father) {
		if (path != null) {
			return;
		}
		int nextRow;
		int nextCol;
		for (int k = 0; k <= 3; k++) { // enumerate 4 directions
			if (path != null) {
				break;
			}
			nextRow = father.row + next[k][0];
			nextCol = father.col + next[k][1];
			if (nextRow == endRow && nextCol == endCol) { // reach terminal
				Node temp = new Node(nextRow, nextCol, father.step + 1, father, 'P');
				path = temp;
				// throw new Exception("have already reach teminal");
			}
			// boundary check
			if (nextRow < 1 || nextRow >= height - 1 || nextCol < 1 || nextCol >= len - 1) {
				continue;
			}
			if (maze[nextRow][nextCol] == ' ' && path == null) {
				maze[nextRow][nextCol] = dir[k];
				Node temp = new Node(nextRow, nextCol, father.step + 1, father, dir[k]);
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				print();
				dfs(temp);
				if (path != null) {
					break;
				}
				maze[nextRow][nextCol] = ' ';
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				print();
			}
		}
		return;
	}

	/**depth first search implemented by stack*/
	public Node stackdfs() {
		Deque<Node> stack = new LinkedList<Node>();
		Node head = new Node(startRow, startCol, 0, null, 'S');
		stack.add(head);
		int nextCol;
		int nextRow;
		char[][] book = copy(copy);
		label: while (!stack.isEmpty()) {
			boolean findWay = false;
			Node father = stack.getLast();
			for (int k = 0; k <= 3; k++) { // enumerate 4 directions
				nextRow = father.row + next[k][0];
				nextCol = father.col + next[k][1];
				if (nextRow == endRow && nextCol == endCol) { // reach terminal
					Node temp = new Node(nextRow, nextCol, father.step + 1, father, 'P');
					stack.add(temp);
					break label;
				}
				// boundary check
				if (nextRow < 1 || nextRow >= height - 1 || nextCol < 1 || nextCol >= len - 1) {
					continue;
				}
				if (maze[nextRow][nextCol] == ' ' && book[nextRow][nextCol] != 'V') {
					maze[nextRow][nextCol] = dir[k];
					Node temp = new Node(nextRow, nextCol, father.step + 1, father, dir[k]);
					stack.add(temp);
					findWay = true;
					book[nextRow][nextCol] = 'V'; // have visited this point
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				print();
			}
			if (!findWay) {
				Node n = stack.pollLast();
				maze[n.row][n.col] = ' ';
			}

		}
		return path = stack.getLast();
	}

	/**breadth first search implemented by queue*/
	public Node bfs() {
		// breath first search
		Deque<Node> que = new LinkedList<Node>();
		Node head = new Node(startRow, startCol, 0, null, 'S');
		que.add(head);
		Node previousFather = null; // record previous father of temp
		// boolean[][] book = new boolean[maze.length][maze[0].length];
		int nextCol;
		int nextRow;
		label: while (!que.isEmpty()) {
			boolean queExtended = false;
			for (int k = 0; k <= 3; k++) { // enumerate 4 directions
				Node father = que.peekFirst();
				nextRow = father.row + next[k][0];
				nextCol = father.col + next[k][1];
				// boundary check
				if (nextRow < 1 || nextRow >= height - 1 || nextCol < 1 || nextCol >= len - 1) {
					continue;
				}
				if (maze[nextRow][nextCol] == ' ') {
					maze[nextRow][nextCol] = dir[k];
					Node temp = new Node(nextRow, nextCol, father.step + 1, father, dir[k]);
					que.add(temp);
					queExtended = true;
				}
				if (nextRow == endRow && nextCol == endCol) { // reach terminal
					Node temp = new Node(nextRow, nextCol, father.step + 1, father, 'P');
					que.add(temp);
					break label;
				}
			}
			// breath queue extension finished
			// pop out father node
			Node newFather = que.pop();
			if (previousFather != newFather && queExtended) {
				previousFather = newFather;
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				print();
			}
		}
		Node tail = que.getLast();
		return path = tail;
	}

	/**print the path to find hostage*/
	public void printPath() {
		Node tail = new Node(path);
		System.out.println("number of steps to find hostage: " + tail.step);
		int pRow = tail.row;
		int pCol = tail.col;
		char[][] mazePath = copy(copy);
		mazePath[pRow][pCol] = tail.dir;
		while (tail.father != null) {
			tail = tail.father;
			pRow = tail.row;
			pCol = tail.col;
			mazePath[pRow][pCol] = tail.dir;
		}
		System.out.println("The path is:");
		print(mazePath);
	}

	/**print the path to find hostage*/
	public static void printPath(final Node node,char[][] copy) {
		Node tail = new Node(node);
		System.out.println("number of steps to find hostage: " + tail.step);
		int pRow = tail.row;
		int pCol = tail.col;
		char[][] mazePath = copy(copy);
		mazePath[pRow][pCol] = tail.dir;
		while (tail.father != null) {
			tail = tail.father;
			pRow = tail.row;
			pCol = tail.col;
			mazePath[pRow][pCol] = tail.dir;
		}
		System.out.println("The path is:");
		Maze.print(mazePath);
	}

	public void init() {
		maze = Maze.copy(copy);
	}
	
	/**copy the original map before search*/
	public char[][] copy() {
		char[][] maze = new char[copy.length][copy[0].length];
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				maze[i][j] = copy[i][j];
			}
		}
		return maze;
	}
	
	/**copy a char array*/
	public static char[][] copy(char[][] src) {
		char[][] maze = new char[src.length][src[0].length];
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				maze[i][j] = src[i][j];
			}
		}
		return maze;
	}

	/**print the maze status, test use only*/
	private void print() {
		for (char[] line : maze) {
			for (char ch : line) {
				System.out.print(ch);
			}
			System.out.println();
		}
		System.out.println();
	}

	/**print a 2d char array*/
	public static void print(char[][] maze) {
		for (char[] line : maze) {
			for (char ch : line) {
				System.out.print(ch);
			}
			System.out.println();
		}
		System.out.println();
	}
}
