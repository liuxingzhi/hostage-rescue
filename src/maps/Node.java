package maps;

public class Node {
	public int row;
	public int col;
	public int step;
	public Node father;
	public Node() {
		
	}
	public Node(int row, int col, int step, Node father) {
		super();
		this.row = row;
		this.col = col;
		this.step = step;
		this.father = father;
	}
}
