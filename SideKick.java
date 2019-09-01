abstract class SideKick  implements Comparable<SideKick> , Cloneable{

	private int XP;
	protected int HP = 100;
	private float attackPower;

	protected SideKick(float attackPower){
		this.attackPower = attackPower;
	}

	@Override
	public int compareTo(SideKick other){
		if (!this.equals(other)){
			return other.XP - this.XP;
		}else{
			if (this.attackPower < other.attackPower)
				return 1;
			else if (other.attackPower > this.attackPower)
				return -1;
			return 0;
		}
	}

	@Override
	public boolean equals(Object other){
		if (other instanceof SideKick){
			SideKick other1 = (SideKick) other;
			return this.XP == other1.XP;
		}
		return false;
	}

	String skHealth(){
		return "SideKick's HP: " + this.HP + "/100";
	}

	float getPower(){
		return attackPower;
	}

	protected int attacked(double pow){
		this.HP -= 1.5*pow;

		if (this.HP<0){
			this.HP = 0;
			return -1;
		}

		return 0;
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

	protected int attack(Monster m, int toAttackOrNot){
		if (toAttackOrNot == -1){
			return -1;
		}
		int x = m.attacked(this.attackPower);
		System.out.println("SideKick attacked and inflicted " + this.attackPower 
			+ " damage to the monster.");
		if (x == -1) return x;
		return 0;
	}

	protected void reward(int amount){
		this.XP += (int) amount*0.1;
		this.attackPower += (amount*0.1)/5;
		this.setMaxHP();
	}

	protected void increasePower(float num){
		this.attackPower += num*(0.5);
	}

	protected abstract int specialMove();

}

class Minion extends SideKick{

	private final static int baseCost = 5;
	private boolean powerUsed = false;
	private Minion clones[] = new Minion[3];

	Minion(int price){
		super(1);
		if (price > baseCost){
			price -= baseCost;
			this.increasePower(price);
		}
	}

	@Override
	public String skHealth(){
		String s = super.skHealth();

		if (powerUsed){
			for (int i = 0; i<3; i++){
				s += "\nSideKick's HP: " + clones[i].getHP() + "/100";
			}
		}

		return s;
	}

	@Override
	public int attacked(double pow){
		int y = super.attacked(pow);
		if (powerUsed){
			for (int i = 0; i<3; i++){
				clones[i].HP -= pow*1.5;
			}
		}

		return y;
	}

	@Override
	protected int attack(Monster m, int toAttackOrNot){
		int m_res  = super.attack(m, toAttackOrNot);
		if (m_res == -1) return -1;

		if (powerUsed){
			for (int i = 0; i<3; i++){
				int x = m.attacked(clones[i].getPower());
				System.out.println("SideKick attacked and inflicted " + clones[i].getPower() 
					+ " damage to the monster.");

				if (x == -1)
					return -1;
			}
		} return 0;
	}

	@Override
	protected Minion clone(){
		try{
			Minion copy = (Minion) super.clone();
			// System.out.println("here");
			return copy;
		}catch (CloneNotSupportedException e){
			// System.out.println(e);
			return null;
		}
		
	}

	private void multiply(){
		if (powerUsed){
			System.out.println("Sorry! You have already used the cloning ability");
		}else{
			for (int i = 0; i<3; i++){
				clones[i] = this.clone();
				// System.out.println(clones[i]);
			}
			powerUsed = true;
			System.out.println("Cloning Done.");
		}
	}

	@Override
	protected int specialMove(){
		this.multiply();
		return 0;
	}


	@Override
	protected void reward(int amount){
		super.reward(amount);

		clones = new Minion[3];

	}


	static int getBaseCost(){
		return baseCost;
	}

}


class Knight extends SideKick{

	private final static int baseCost = 8;

	Knight(int price){
		super(2);
		if (price > baseCost){
			price -= baseCost;
			this.increasePower(price);
		}
	}

	@Override
	protected int attack(Monster m, int toAttackOrNot){
		return super.attack(m, toAttackOrNot);
	}

	private int activateDefense(){
		return 5;
	}

	static int getBaseCost(){
		return baseCost;
	}

	@Override
	protected int specialMove(){
		return this.activateDefense();
	}

}