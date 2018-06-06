package ie.gmit.sw.ai;

import java.util.*;
public class StackDFS {
	private LinkedList<Node> queue = new LinkedList<Node>();
	
	public StackDFS(Maze maze) {
		Node start = maze.getStartNode();
		start.setVisited(true);
		queue.addFirst(start);
		search(start);
	}

	public void search(Node node){
		while(!queue.isEmpty()){
			if (node.isGoalNode()){
				System.out.println("Reached goal node " + node.getNodeName());
				System.exit(0);
			}else{
				Node[] children = node.children();
				for (int i = children.length - 1; i >= 0; i--) {
					if (!children[i].isVisited()){
						queue.addFirst(children[i]);
					}
				}
			}
			node = queue.getFirst();
			node.setVisited(true);
			queue.removeFirst();
		}

	}

	public static void main(String[] args) {
		Maze maze = Maze.getInstance();
		StackDFS search = new StackDFS(maze);
	}
}
