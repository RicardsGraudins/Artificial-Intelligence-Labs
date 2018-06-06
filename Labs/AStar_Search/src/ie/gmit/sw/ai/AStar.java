package ie.gmit.sw.ai;

import java.util.*;

public class AStar {
	PriorityQueue<Node> open = null;
	List<Node> closed = new ArrayList<Node>();
	HeuristicNodeComparator sorter = new HeuristicNodeComparator();
	static int nodecount;
	public void search(Node start){
		open = new PriorityQueue<Node>(20, sorter);
		start.setDistanceTravelled(0);
		open.add(start);
		while(!open.isEmpty()){
			Node node = open.poll();
			
			if (node.isGoalNode()){
				List<Node> path = new ArrayList<Node>();
				while(node.getParent() != null){
					path.add(node);
					node = node.getParent();
				}
				path.add(node);
				Collections.reverse(path);
				System.out.println("Path: " + path);
				System.exit(0);
			}
			
			pushSuccessors(node);
			closed.add(node);			
		}
	}
	
	public void pushSuccessors(Node node){
		Node[] children = node.children();
		for (int i = 0; i < children.length; i++) {
			nodecount++;
			Node child = children[i];
			float score = HeuristicCalculator.getHeuristicValue(node.getDistanceTravelled() + node.getDistanceToNode(child), child.getGoalDistance(), child.getTerrain(), child.getDanger());
			if ((open.contains(child) || closed.contains(child)) && child.getScore() < score){
				continue;
			}else{
				open.remove(child);
				closed.remove(child);
				child.setParent(node);		
				child.setDistanceTravelled(node.getDistanceTravelled() + node.getDistanceToNode(child));
				open.add(child);
			}
		}
	}
	
	public static void main(String[] args) {
		MiddleEarth me = new MiddleEarth();
		Node start = me.getStartNode();
		AStar astar = new AStar();
		astar.search(start);
	}
}
