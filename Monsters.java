class Monster{

	protected int HP = 100;
	protected int level;

	public int getLevel(){
		return this.level;
	}

}

class Goblins extends Monster{

	Goblins(){
		this.HP = 100;
		this.level = 1;
	}
}

class Zombies extends Monster{

	Zombies(){
		this.HP = 100;
		this.level = 2;
	}
	
}

class Fiends extends Monster{
	
	Fiends(){
		this.HP = 100;
		this.level = 3;
	}
}

class Lionfang extends Monster{

	Lionfang(){
		this.HP = 100;
		this.level = 4;
	}
	
}