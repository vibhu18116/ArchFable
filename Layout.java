import java.util.Random;
import java.util.ArrayList;

class Graph{

	int numNodes = 12;
	ArrayList<ArrayList<Node>> adjList = new ArrayList<ArrayList<Node>>(numNodes);
	Node nodes[];

	Graph(){
		nodes = new Node[numNodes];
		for (int i = 0; i<numNodes; i++){
			nodes[i] = new Node(i-1);
		}

		for (int i = 0; i<numNodes; i++){
			ArrayList<Node> arr = new ArrayList<Node>();
			adjList.add(arr);
		}
	}


	void addEdge(int u, int v){
		adjList.get(u).add(nodes[v]);
		adjList.get(v).add(nodes[u]);
	}

	void print(){

		for (int i = 0; i<12; i++){
			int k = i-1;
			System.out.print(k + " -> ");

			for (int j = 0; j<adjList.get(i).size(); j++){
				System.out.print(adjList.get(i).get(j) + " ");
			}

			System.out.println();
		}
	}


	ArrayList<Node> getNeighbours(int u){
		return adjList.get(u+1);
	}

}

class Layout{

	private Graph gr = new Graph();

	Layout(){

		gr.addEdge(0,1);
		gr.addEdge(0,4);
		gr.addEdge(0,7);
		gr.addEdge(1,2);
		gr.addEdge(1,5);
		gr.addEdge(1,10);
		gr.addEdge(2,5);
		gr.addEdge(3,6);
		gr.addEdge(3,7);
		gr.addEdge(3,10);
		gr.addEdge(4,5);
		gr.addEdge(5,8);
		gr.addEdge(6,8);
		gr.addEdge(8,9);
		gr.addEdge(8,11);

		// gr.print();
	}

	Graph getGraph(){
		return gr;
	}

	// public static void main(String[] args) {
	// 	Layout l = new Layout();
	// 	// Graph g = new Graph();
	// }


}



class RandomLayout{

	private int nodes;
	private Node[][] layout;

	RandomLayout(){

		nodes = this.numNodes(15, 25);
		layout = new Node[nodes][nodes];
	}


	private int numNodes(int min, int max){
		Random random = new Random();
		return random.nextInt(max-min) + min + 1;
	}


}


class Node{
	private Monster enemy;
	private int allocated_num;

	Node(){}

	Node(int allocated_num){
		this.allocated_num = allocated_num;
		this.setEnemy();
	}

	void setEnemy(){
		if (this.allocated_num == 10){
			this.enemy = new Lionfang();
			return;
		}

		Random random = new Random();
		int indicator = random.nextInt(3) + 1;

		if (indicator == 1){
			this.enemy = new Goblins();
		}else if(indicator == 2){
			this.enemy = new Zombies();
		}else{
			this.enemy = new Fiends();
		}
	}

	void setNum(int label){
		this.allocated_num = label;
	}

	Monster getEnemy(){
		return enemy;
	}

	int getLabel(){
		return this.allocated_num;
	}

	@Override
	public String toString(){
		// return Integer.toString(this.allocated_num);
		return "( " + this.allocated_num + " " + this.enemy + " )";
	}
}