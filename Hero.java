import java.util.Scanner;

interface Hero_powers{

	public void specialAttack(Monster enemy);
}

class Hero{

	protected int num_moves = 0;
	private int HP = 100;
	private int XP = 0;
	private int currentLevel = 1;
	private int attack;
	private int defense;
	private boolean specialAttack = false;
	private Scanner sc = new Scanner(System.in);

	protected Hero(int attack, int defense){
		this.attack = attack;
		this.defense = defense;
	}

	protected int attack(){
		return attack;
	}

	protected int defense(){
		return defense;
	}

	protected int fightOptions(Monster enemy){

		while (true){
			System.out.println("Choose move:");
			int count = 0;
			System.out.println(++count + ") Attack");
			System.out.println(++count + ") Defense");

			if (specialAttack){
				System.out.println(++count + ") Special Attack");
			}

			num_moves++;

			if (num_moves%4 == 0 && !specialAttack){
				num_moves = 0;
				specialAttack = true;
			}

			int chosen = sc.nextInt();

			if (chosen<1 || chosen>count){
				System.out.println("Invalid option. Choose again.");
			}else{
				switch(chosen){
					case(1):
						this.attack();

					case(2):
						this.defense();

					case(3):
						this.specialAttack();
				}
			}

		}
	}
}


class Warrior extends Hero implements Hero_powers{

	Warrior(){
		super(10, 3);
	}

	public String toString(){
		return "Warrior";
	}

	@Override
	public void specialAttack(Monster enemy){
		int bonus_moves = 3;
		int bonus_attack = 5;
		int bonus_defense = 5;

		for (int i = 0; i<bonus_moves; i++){
			//implement
		}
	}

}

class Mage extends Hero implements Hero_powers{

	Mage(){
		super(5, 5);
	}

	public String toString(){
		return "Mage";
	}

	@Override
	public void specialAttack(Monster enemy){
		
	}

}

class Thief extends Hero implements Hero_powers{

	Thief(){
		super(6, 4);
	}

	public String toString(){
		return "Thief";
	}

	@Override
	public void specialAttack(Monster enemy){
		
	}

}

class Healer extends Hero implements Hero_powers{

	Healer(){
		super(4, 8);
	}

	public String toString(){
		return "Healer";
	}

	@Override
	public void specialAttack(Monster enemy){
		
	}

}