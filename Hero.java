import java.util.Scanner;
import java.text.DecimalFormat;

interface Hero_powers{

	public void specialAttack(Monster enemy);
}

class Hero{

	protected int num_moves = 0;
	private double initialHP = 100;
	protected double HP = 100;
	private int XP = 0;
	private int currentLevel = 1;
	protected int attack;
	protected int defense;
	private boolean specialAttack = false;
	private Scanner sc = new Scanner(System.in);
	private static DecimalFormat df = new DecimalFormat("0.00");
	private boolean defenseON = false;

	boolean checkIfDefending(){
		return defenseON;
	}

	String healthProfile(Monster m){
		return "Your HP: " + df.format(this.getHP()) + "/" + this.getInitialHP() + " Monster HP: " + m.monsterHP();
	}

	double getInitialHP(){
		return initialHP;
	}

	int getDefensePower(){
		return this.defense;
	}

	double getHP(){
		return HP;
	}

	protected Hero(int attack, int defense){
		this.attack = attack;
		this.defense = defense;
	}

	void attacked(double pow){
		this.HP -= pow;
	}

	protected void attack(Monster m){
		System.out.println("You attacked and inflicted "+ this.attack + " damage to the monster.");
		m.attacked(attack);
		System.out.println(this.healthProfile(m));
		m.attack(this);
	}

	protected void defense(Monster m){
		System.out.println("Monster attack will be reduced by upto " + this.defense);
		System.out.println(this.healthProfile(m));
		defenseON = true;
		m.attack(this);
		defenseON = false;
	}

	protected void specialAttack(){}

	protected void fightOptions(Monster enemy){

		while (true){
			System.out.println("Choose move:");
			int count = 0;
			System.out.println(++count + ") Attack");
			System.out.println(++count + ") Defense");

			if (specialAttack){
				System.out.println(++count + ") Special Attack");
			}

			if (num_moves%4 == 3 && !specialAttack){
				num_moves = 0;
				specialAttack = true;
			}

			int chosen = sc.nextInt();

			if ((chosen<1 || chosen>count) && chosen!=-1){
				System.out.println("Invalid option. Choose again.");
			}else{
				num_moves++;
				switch(chosen){
					case(1):
						System.out.println("You choose to attack");
						this.attack(enemy);
						break;

					case(2):
						System.out.println("You choose to defend");
						this.defense(enemy);
						break;

					case(3):
						System.out.println("Special attack activated.");
						this.specialAttack();
						break;

					case(-1):
						System.out.println("Exiting");
						return;
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
		System.out.println("Attack and defense power boosted by 5 for next 3 moves.");
		int bonus_moves = 3;
		int bonus_attack = 5;
		int bonus_defense = 5;

		int attack_temp = this.attack; 
		int defense_temp = this.defense;

		this.attack += 3;
		this.defense += 3;

		System.out.println(this.healthProfile(enemy));
		
		for (int i = 0; i<bonus_moves; i++){
			super.fightOptions(enemy);
			this.num_moves = 0;
		}

		System.out.println("Special power deactivated.");

		this.attack = attack_temp;
		this.defense = defense_temp;
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
		System.out.println("Reducing enemy's HP by 5% for next 3 moves.");
		for (int i = 0; i<3; i++){
			double mons_temp_HP = enemy.getHP();
			double toReduce = mons_temp_HP*(0.05);
			enemy.attacked(toReduce);
			System.out.println(this.healthProfile(enemy));

			super.fightOptions(enemy);
		}

		System.out.println("Special power deactivated.");
		System.out.println(this.healthProfile(enemy));

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

	private void increaseHP(){
		double toIncrease = this.HP * 0.05;
		double temp = this.HP + toIncrease;

		if (temp > this.getInitialHP()){
			this.HP = this.getInitialHP();
		}else{
			this.HP = temp;
		}
	}

	@Override
	public void specialAttack(Monster enemy){
		System.out.println("Increasing own HP by 5% for next 3 moves.");
		for (int i = 0; i<3; i++){

			this.increaseHP();			
			System.out.println(this.healthProfile(enemy));
			super.fightOptions(enemy);
		}

		System.out.println("Special power deactivated.");
		System.out.println(this.healthProfile(enemy));
	}

}