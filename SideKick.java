abstract class SideKick{

	private int XP;
	private int HP = 100;
	private int attackPower;

	protected SideKick(int attackPower){
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

	private int baseCost = 5;
	private boolean powerUsed = false;

	Minion(){
		super(1);
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
			//cloning
		}
	}

}


class Knight extends SideKick{

	private int baseCost = 8;

	Knight(){
		super(2);
	}

	@Override
	protected void attack(){
		
	}

	private void activateDefense(){

	}



}