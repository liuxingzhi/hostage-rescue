package maps;

public class Node {
	public int row;
	public int col;
	public int step;
	public Node father;
	public char dir;
	
	public Node() {
		
	}
	
	public Node(Node node) {
		super();
		this.row = node.row;
		this.col = node.col;
		this.step = node.step;
		this.father = node.father;
		this.dir = node.dir;
	}
	
	public Node(int row, int col, int step, Node father,char dir) {
		super();
		this.row = row;
		this.col = col;
		this.step = step;
		this.father = father;
		this.dir = dir;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Node [row=" + row + ", col=" + col + ", step=" + step + ", father=" + father + ", dir=" + dir + "]";
	}
}
