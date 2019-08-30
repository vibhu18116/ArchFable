abstract class SideKick{

	private int XP;
	private int HP = 100;
	private float attackPower;

	private float getPower(){
		return attackPower;
	}

	protected SideKick(float attackPower){
		this.attackPower = attackPower;
	}

	int getHP(){
		return this.HP;
	}

	int getXP(){
		return this.XP;
	}

	void setXP(int increment){
		this.XP += increment;
	}

	void setHP(int val){
		if (this.HP + val <= 100){
			this.HP += val;		
		}else{
			this.setMaxHP();
		}
	}

	void setMaxHP(){
		this.HP = 100;
	}

	protected abstract void attack();

	protected void reward(){

	}

}

class Minion extends SideKick implements Cloneable{

	private final int baseCost = 5;
	private boolean powerUsed = false;
	private Minion clones[] = new Minion[3];

	Minion(int price){

		if (price > baseCost){
			price -= baseCost;

		}

		super(1 + price*0.5);
	}

	@Override
	protected void attack(){

	}

	@Override
	protected Minion clone(){
		Minion copy = this.clone();
		return copy;
	}

	private void multiply(){
		if (powerUsed){
			System.out.println("Sorry! You have already used the cloning ability");
		}else{
			for (int i = 0; i<3; i++){
				clones[i] = this.clone();
			}

			System.out.println("Cloning Done.");
		}
	}


	static int getBaseCost(){
		return baseCost;
	}

}


class Knight extends SideKick{

	private final int baseCost = 8;

	Knight(){
		super(2);
	}

	@Override
	protected void attack(){
		
	}

	private void activateDefense(){

	}

	static int getBaseCost(){
		return baseCost;
	}

}