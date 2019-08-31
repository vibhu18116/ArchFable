import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

interface Hero_powers{

	public void specialAttack(Monster enemy);
}

abstract class Hero{

	protected int num_moves = 0;
	private double initialHP = 100;
	protected double HP = 100;
	private int XP = 0;
	protected int currentLevel = 1;
	protected int attack;
	protected int defense;
	protected boolean specialAttack = false;
	private Scanner sc = new Scanner(System.in);
	protected static DecimalFormat df = new DecimalFormat("0.00");
	private boolean defenseON = false;
	protected int res = 0;
	protected ArrayList<SideKick> _sidekicks = new ArrayList<SideKick>();
	protected SideKick current_sidekick = null;
	protected int thisLevelXP = 0;


	protected Hero(int attack, int defense){
		this.attack = attack;
		this.defense = defense;
	}

	int getXP(){
		return XP;
	}

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

	int purchaseMinion(int sp){
		if (sp>this.XP){
			System.out.println("Sorry!! You are claiming more XP than you have!");
		}else{
			if (sp>Minion.getBaseCost()){
				Minion newMinion = new Minion(sp);
				_sidekicks.add(newMinion);
				return 1;
			}else{
				System.out.println("You don't have enough XP");
			}
		}
		return 0;
	}

	int purchaseKnight(int sp){
		if (sp>this.XP){
			System.out.println("Sorry!! You are claiming more XP than you have!");
		}else{
			if (sp>Minion.getBaseCost()){
				Knight newMinion = new Knight(sp);
				_sidekicks.add(newMinion);
				return 1;
			}else{
				System.out.println("You don't have enough XP");
			}
		}
		return 0;
	}

	protected void activateSK(){
		Collections.sort(_sidekicks);
		this.current_sidekick = _sidekicks.get(0);
		System.out.println("You have a sidekick " + this.current_sidekick.getClass().getName()
		+ " with you. Attack of sidekick is " + this.current_sidekick.getPower() + ".");

		if (this.current_sidekick instanceof Minion){
			System.out.println("Press c to use cloning ability. Else press f to move to the fight");
			String ans = sc.next();

			if (ans.equals("c")){
				this.current_sidekick.specialMove();
			}
		}else{
			this.current_sidekick = (Knight) this.current_sidekick;
		}
	}

	protected void purchaseSideKick(){
		System.out.println("Your current XP is " + this.XP);
		System.out.println("If you want to buy a minion, press 1.");
		System.out.println("If you want to buy a knight, press 2.");
		int skOption = sc.nextInt();

		System.out.print("XP to spend: ");
		int exchangeXP = sc.nextInt();

		int success = 0;

		if (skOption == 1){
			success = this.purchaseMinion(exchangeXP);
		}else{
			success = this.purchaseKnight(exchangeXP);
		}

		if (success == 0){

		}else{
			SideKick purchased = _sidekicks.get(_sidekicks.size()-1);
			System.out.println("You bought a sidekick: " + purchased.getClass().getName());
			System.out.println("XP of sidekick: " + purchased.getXP());
			System.out.println("Attack of sidekick is " + purchased.getPower());
		}
	}

	

	double attacked(double pow){
		this.HP -= pow;
		return this.HP;
	}

	protected void attack(Monster m){
		System.out.println("You attacked and inflicted "+ this.attack + " damage to the monster.");
		res = m.attacked(attack);
		if (this.current_sidekick != null){
			res = this.current_sidekick.attack(m, res);
		}
		if (res == -1){
			return;
		}
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

	protected abstract void specialAttack(Monster m);

	private void levelUp(Monster m){
		this.XP += m.getLevel()*20;
		this.thisLevelXP += m.getLevel()*20;
		System.out.println(m.getLevel()*20 + " XP awarded.");

		if (this.thisLevelXP >= 20 && currentLevel == 1){
				currentLevel = 2;
				this.thisLevelXP = 0;
				this.HP = 150;
				this.initialHP = 150;
				System.out.println("Level Up: " + currentLevel);
		}
		else if (this.thisLevelXP >= 40 && currentLevel == 2){
				currentLevel = 3;
				this.thisLevelXP = 0;
				this.HP = 200;
				this.initialHP = 200;
				System.out.println("Level Up: " + currentLevel);
		}
		else if (this.thisLevelXP >= 60 && currentLevel == 3){
				currentLevel = 4;
				this.thisLevelXP = 0;
				this.HP = 250;
				this.initialHP = 250;
				System.out.println("Level Up: " + currentLevel);
		}
	}

	protected int fightOptions(Monster enemy){

		int start = 0;

		while (true){
			if (res == -1){
				levelUp(enemy);
				res = 0;
				return 1;
			}

			if (this.HP<=0){
				System.out.println("Monster killed you!");
				this.HP = 100;
				this.XP = 0;
				this.currentLevel = 1;
				return -1;
			}

			System.out.println("Choose move:");
			int count = 0;
			System.out.println(++count + ") Attack");
			System.out.println(++count + ") Defense");

			if (num_moves%4 == 3 && !specialAttack && num_moves>=0){
				specialAttack = true;
			}

			// System.out.println(num_moves);

			if (specialAttack && num_moves>=0){
				System.out.println(++count + ") Special Attack");
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
						specialAttack(enemy);
						this.specialAttack = false;
						break;

					case(-1):
						System.out.println("Exiting");
						return 0;
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
		this.num_moves = -4;

		// int attack_temp = this.attack; 
		// int defense_temp = this.defense;
		super.specialAttack = true;

		super.attack += 3;
		super.defense += 3;

		System.out.println(this.healthProfile(enemy));
		
		for (int i = 0; i<bonus_moves; i++){
			super.fightOptions(enemy);
		}

		System.out.println("Special power deactivated.");

		// super.attack = attack_temp;
		// super.defense = defense_temp;
		super.attack -= 3;
		super.defense -= 3;
		super.specialAttack = false;
		this.num_moves = 0;
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
			res = enemy.attacked(toReduce);
			if (res == -1){
				return;
			}
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
		double enemy_pow = enemy.getHP();
		double stolen = enemy_pow*0.3;
		res = enemy.attacked(stolen);
		if (res == -1){
			return;
		}
		super.HP += stolen;
		if (super.HP > 100 && currentLevel ==1){
			super.HP = 100;
		}else if (super.HP > 150 && currentLevel ==2){
			super.HP = 150;
		}else if (super.HP > 200 && currentLevel ==3){
			super.HP = 200;
		}else if (super.HP > 250 && currentLevel ==4){
			super.HP = 250;
		}
		System.out.println("You stole " + df.format(stolen) + " HP from the monster!");
		System.out.println(this.healthProfile(enemy));
		enemy.attack(this);
		// System.out.println(this.healthProfile(enemy));
		System.out.println("Special power deactivated");

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