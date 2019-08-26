import java.util.Scanner;

class User{

	private final String name;
	private static Scanner sc = new Scanner(System.in);
	private Hero currentHero;
	private GamePlay gp;
	
	User(){
		this.name = this.getUserDetails();
		this.currentHero = this.assignHero();
		System.out.println("User Creation done. Username: "
		 		+ this.name + ". Hero type: " + 
		 		this.currentHero +
		 		 ". Log in to play the game. Exiting");
	}

	public String getName(){
		return this.name;
	}

	private String getUserDetails(){
		System.out.println("Enter Username");
		String name = sc.next();
		return name;
	}

	private Hero assignHero(){
		System.out.println("Choose a Hero");
		System.out.println("1) Warrior");
		System.out.println("2) Thief");
		System.out.println("3) Mage");
		System.out.println("4) Healer");

		int choice = sc.nextInt();

		switch(choice){
			case 1:
			return new Warrior();
			case 2:
			return new Thief();
			case 3:
			return new Mage();
			case 4:
			return new Healer();
			default:
			System.out.println("Invalid option");
			return null;
		}
	}

	void logging(){
		System.out.println("Welcome " + this.name);

		if (gp == null){
			GamePlay gp = new GamePlay(this.currentHero);
			gp.start();
		}
	}
}