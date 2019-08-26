import java.util.Scanner;
import java.util.ArrayList;

class Game{

	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<User> _users = new ArrayList<>();

	Game(){
	}

	protected void start(){
		this.startMenu();
	}

	private void startMenu(){

		while (true){
			System.out.println("Welcome to ArchLegends");
			System.out.println("Choose your option");
			System.out.println("1) New User");
			System.out.println("2) Existing User");
			System.out.println("3) Exit");

			int option = sc.nextInt();

			if (option == 3){
				System.out.println("Exiting the game");
				break;
			}

			this.decision(option);

		}
	}


	private void decision(int option){

		switch (option){
			case 1:
				create_new_user();
				return;

			case 2:
				existing_user();
				return;
			default:
				System.out.println("Invalid query");
		}

	}

	private void create_new_user(){
		_users.add(new User());
	}

	private void existing_user(){
		System.out.println("Enter Username");
		String name = sc.next();

		for (int i = 0; i<_users.size(); i++){
			if (_users.get(i).getName().equals(name)){
				System.out.println("User found... Logging in");
				_users.get(i).logging();
				return;
			}
		}

		System.out.println("User not found");
	}

}