import java.util.ArrayList;
import java.util.Scanner;

class GamePlay{

	private Layout layout;
	private Hero myHero;
	private Scanner sc = new Scanner(System.in);
	private int currentLocation = -1;

	GamePlay(Hero h){
		this.layout = new Layout();
		this.myHero = h;
	}

	void start(){
		System.out.println("You are at the starting location. Choose path:");
		ArrayList<Node> neighbours = layout.getGraph().getNeighbours(-1);

		int count = 0;

		for (int i = 0; i<neighbours.size(); i++){
			System.out.println(++count + ") Go to location " + neighbours.get(i).getLabel());
		}

		System.out.println("Enter -1 to exit.");
		int chosen = sc.nextInt();

		if (chosen > count || chosen<1 && chosen != -1){
			System.out.println("Invalid option. Exiting...");
			return;
		}

		if (chosen == -1){
			System.out.println("Exiting...");
			return;
		}

		System.out.println("Moving to location " + neighbours.get(chosen-1).getLabel());
		currentLocation = neighbours.get(chosen-1).getLabel();
		// System.out.println(currentLocation + " currentLocation");

		startFight(neighbours.get(chosen-1).getEnemy());
	}

	private void startFight(Monster mons){
		System.out.println("Fight Started. You are fighting a level " + mons.getLevel() + " Monster");
		int next = myHero.fightOptions(mons);

		while (true){
			if (next == 1){
				System.out.println("Proceed to next Location");
				System.out.println("If you would like to buy a sidekick, type yes. Else type no to upgrade level.");
				String answer = sc.nextLine();

				if (answer.equals("yes")){
					myHero.purchaseSideKick();
				}

				Monster success = possibleLocations(currentLocation, mons);
				if (success == null)
					break;
				else{
					System.out.println("Fight Started. You are fighting a level " + success.getLevel() + " Monster");
					myHero.fightOptions(success);
				}
			}else{
				return;
			}
		}

	}

	private Monster possibleLocations(int start, Monster m){

		ArrayList<Node> neighbours = layout.getGraph().getNeighbours(start);

		int count = 0;

		for (int i = 0; i<neighbours.size(); i++){
			int label = neighbours.get(i).getLabel();
			if (label!=-1)
				System.out.println(++count + ") Go to location " + label);
			else
				System.out.println(++count + ") Go to initial location");
		}

		++count;
		System.out.println(count + ") Go Back");

		System.out.println("Enter -1 to exit.");
		int chosen = sc.nextInt();

		if (chosen > count || chosen<1 && chosen != -1){
			System.out.println("Invalid option. Exiting...");
			return null;
		}

		if (chosen == -1){
			System.out.println("Exiting...");
			return null;
		}

		if (chosen == count){
			System.out.println("Moving Back");
			return m;
		}

		System.out.println("Moving to location " + neighbours.get(chosen-1).getLabel());
		currentLocation = neighbours.get(chosen-1).getLabel();
		return neighbours.get(chosen-1).getEnemy();
	}
}