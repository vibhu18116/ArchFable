import java.util.Random;

class Layout{

	Layout(){

		
	}


}


class RandomLayout{

	private int nodes;
	private Node[][] layout;

	RandomLayout(){

		nodes = this.numNodes(15, 25);
		layout = new Node[nodes][nodes];

	}


	private int numNodes(int min, int max){
		return Random.nextInt(max-min) + min + 1;
	}


}


class Node{
	private boolean edge;
	private int enemy;

	Node(){}

	Node(boolean edge, int enemy){
		this.edge = edge;
		this.enemy = enemy;
	}

	boolean getIfEdge(){
		return edge;
	}

	int getEnemy(){
		return enemy;
	}
}