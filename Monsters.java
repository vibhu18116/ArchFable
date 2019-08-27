import java.util.Random;
import java.lang.Math;
import java.text.DecimalFormat;

class Monster{

	protected double HP = 100;
	protected int level;
	protected final int initialHP;
	private static DecimalFormat df = new DecimalFormat("0.00");

	protected Monster(int initialHP){
		this.initialHP = initialHP;
	}

	public int getLevel(){
		return this.level;
	}

	public double getHP(){
		return this.HP;
	}

	public String monsterHP(){
		return df.format(this.HP) + "/" + this.initialHP;
	}

	public int attacked(double power){
		double temp = this.HP - power;

		if (temp>=0){
			this.HP = temp;
		}else{
			System.out.println("Monster killed!");
			return -1;
		}

		return 0;
	}

	protected void attack(Hero victim){
		double attackPow  = -1;

		Random rand = new Random();

		while (attackPow<0 || attackPow>this.HP/4){
			double num = rand.nextGaussian();
			attackPow = (num * (this.HP)/24) + (this.HP/8);

		}

		System.out.println("Monster attacked!");


		if (this instanceof Lionfang){
			double f = Math.random();

			if (f<=0.1){
				System.out.println("LionFang attacked with special power.");
				attackPow = this.specialAttack();
			}
		}

		if (victim.checkIfDefending()){
			attackPow -= victim.getDefensePower();

			if (attackPow<0)
				attackPow = 0;
		}

		System.out.println("The monster attacked and inflicted " + df.format(attackPow) 
							+ " damage to you.");
		double victim_health = victim.attacked(attackPow);

		if (victim_health<=0){
			return;
		}
		System.out.println(victim.healthProfile(this));
	}

	private float specialAttack(){return 0;}

}

class Goblins extends Monster{

	Goblins(){
		super(100);
		this.HP = 100;
		this.level = 1;
	}
}

class Zombies extends Monster{

	Zombies(){
		super(150);
		this.HP = 150;
		this.level = 2;
	}
	
}

class Fiends extends Monster{
	
	Fiends(){
		super(200);
		this.HP = 200;
		this.level = 3;
	}
}

class Lionfang extends Monster{

	Lionfang(){
		super(250);
		this.HP = 250;
		this.level = 4;
	}

	private double specialAttack(){
		return this.HP/2;
	}
	
}