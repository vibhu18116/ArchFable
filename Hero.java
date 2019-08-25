class Hero{

	protected int num_moves = 0;
	private int HP = 100;
	private int XP = 0;
	private int currentLevel = 1;
	private int attack;
	private int defense;

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
	// public abstract int special_power();
}


class Warrior extends Hero{

	Warrior(){
		super(10, 3);
	}

	public String toString(){
		return "Warrior";
	}

}

class Mage extends Hero{

	Mage(){
		super(5, 5);
	}

	public String toString(){
		return "Mage";
	}

}

class Thief extends Hero{

	Thief(){
		super(6, 4);
	}

	public String toString(){
		return "Thief";
	}

}

class Healer extends Hero{

	Healer(){
		super(4, 8);
	}

	public String toString(){
		return "Healer";
	}

}