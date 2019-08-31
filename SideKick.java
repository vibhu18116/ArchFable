abstract class SideKick  implements Comparable<SideKick>{

	private int XP;
	private int HP = 100;
	private float attackPower;

	@Override
	public int compareTo(SideKick other){
		if ((this.XP - other.XP)!=0){
			return other.XP - this.XP;
		}else{
			if (this.attackPower < other.attackPower)
				return 1;
			else if (other.attackPower > this.attackPower)
				return -1;
			return 0;
		}
	}

	public float getPower(){
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

	protected int attack(Monster m, int toAttackOrNot){
		if (toAttackOrNot == -1){
			return -1;
		}
		System.out.println("SideKick attacked and inflicted " + this.attackPower 
			+ " damage to the monster.");
		return 0;
	}

	protected void reward(){

	}

	protected void increasePower(float num){
		this.attackPower += num*(0.5);
	}

	protected abstract void specialMove();

}

class Minion extends SideKick implements Cloneable{

	private final static int baseCost = 5;
	private boolean powerUsed = false;
	private Minion clones[] = new Minion[3];

	Minion(int price){
		super(1);
		if (price > baseCost){
			price -= baseCost;
		}

		this.increasePower(price);
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
		// try{
			Minion copy = (Minion) this.clone();
			return copy;
		// }catch (CloneNotSupportedException e){
		// 	System.out.println(e);
		// 	return null;
		// }
		
	}

	private void multiply(){
		if (powerUsed){
			System.out.println("Sorry! You have already used the cloning ability");
		}else{
			for (int i = 0; i<3; i++){
				clones[i] = this.clone();
				System.out.println(clones[i]);
			}
			powerUsed = true;
			System.out.println("Cloning Done.");
		}
	}

	@Override
	protected void specialMove(){
		this.multiply();
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
		}

		this.increasePower(price);
	}

	@Override
	protected int attack(Monster m, int toAttackOrNot){
		return super.attack(m, toAttackOrNot);
	}

	private void activateDefense(){

	}

	static int getBaseCost(){
		return baseCost;
	}

	@Override
	protected void specialMove(){
		this.activateDefense();
	}

}