package FoodBoy;

import java.awt.Image;

public class Food {

	public static final int BOMB = 0;
	public static final int RATPOISON = 1;
	public static final int BADAPPLE = 2;
	public static final int CHICKENWAFFLE = 3;
	public static final int EGGPLANT = 4;
	public static final int ICECREAMSUNDAE = 5;
	public static final int CEREAL = 6;
	public static final int PIE = 7;
	public static final int RAMEN = 8;
	public static final int APPLE = 9;
	public static final int CAKE = 10;
	
	public Image image;
	String fall;
	
	private Game game;
	
	
	public int x, y, weight;
	private boolean harmful = false, fatal = false, edible = true;
	
	public Food (Game game, int foodType, int startX, int startY) {
		this.game = game;
		x = startX;
		y = startY;		
		
		switch (foodType) {
		
		case BOMB:
			fatal = true;
			weight = 0;
			image = game.getImage(game.getBase(), "img/bombcake.png");
			fall = "img/bombcake";
			y = y + 40;
			break;
			
		case RATPOISON:
			harmful = true;
			weight = 0;
			image = game.getImage(game.getBase(), "img/ratpoison.png");
			fall = "img/ratpoison";
			break;
			
		case BADAPPLE:
			harmful = true;
			weight = 0;
			image = game.getImage(game.getBase(), "img/badapple.png");
			fall = "img/badapple";
			y = y + 40;
			break;
		
		case CHICKENWAFFLE:
			image = game.getImage(game.getBase(), "img/chickenwaffles.png");
			fall = "img/chickenwaffles";
			y = y + 40;
			weight = 50;
			break;
			
		case EGGPLANT:
			image = game.getImage(game.getBase(), "img/eggplant.png");
			fall = "img/eggplant";
			y = y + 40;
			weight = 25;
			break;
		
		case ICECREAMSUNDAE:
			image = game.getImage(game.getBase(), "img/sundae.png");
			fall = "img/sundae";
			weight = 50;
			break;
			
		case CEREAL:
			image = game.getImage(game.getBase(), "img/cereal.png");
			fall = "img/cereal";
			weight = 25;
			break;
				
		case PIE:
			image = game.getImage(game.getBase(), "img/pie.png");
			fall = "img/pie";
			y = y + 40;
			weight = 50;
			break;
				
		case RAMEN:
			image = game.getImage(game.getBase(), "img/ramen.png");
			fall = "img/ramen";
			weight = 25;
			y = y + 40;
			break;
			
		case APPLE:
			image = game.getImage(game.getBase(), "img/apple.png");
			fall = "img/apple";
			weight = 25;
			y = y + 40;
			
		case CAKE:
			image = game.getImage(game.getBase(), "img/cake.png");
			fall = "img/cake";
			weight = 50;
			y = y + 40;
		
		
		}
		
		
		
	}
	
	public void moveRight() {
		x += 10;
	}
	
	public void moveLeft() {
		x -= 10;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void fallFortyFiveL() {
		image = game.getImage(game.getBase(), fall + "45L.png");
		x += 10;
		y += 10;
	}
	
	public void fallNinetyL() {
		image = game.getImage(game.getBase(), fall + "90L.png");
		y += 10;
	}
	
	public void fallFortyFiveR() {
		image = game.getImage(game.getBase(), fall + "45R.png");
		x -= 10;
		y += 10;
	}
	
	public void fallNinetyR() {
		image = game.getImage(game.getBase(), fall + "90R.png");
		y += 10;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public boolean isHarmful() {
		return harmful;
	}
	
	public boolean isFatal() {
		return fatal;
	}
	
	public void setFatal(boolean set) {
		fatal = set;
	}
	
	public void setHarmful(boolean set) {
		harmful = set;
	}
	
	public void setEdible (boolean edible) {
		this.edible = edible;
	}
	
	public boolean isEdible () {
		return edible;
	}
	
	public String toString() {
		return ("The food item was " +fall.substring(3, fall.length()));
	}

}
