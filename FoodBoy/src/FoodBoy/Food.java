package FoodBoy;

import java.awt.Image;

public class Food {

	public static final int BOMB = 0;
	public static final int RATPOISON = 1;
	public static final int BADAPPLE = 2;
	public static final int GOODAPPLE = 3;
	public static final int CHICKENWAFFLE = 4;
	public static final int EGGPLANT = 5;
	public static final int ICECREAMSUNDAE = 6;
	public static final int PIE = 7;
	public static final int CAKE = 8;
	public static final int RAMEN = 9;
	public static final int CEREAL = 10;
	
	public static final int NUMBER_FOODS = 11;

	public Image image;
	String fall;

	private Game game;

	public int x, y, weight;
	private boolean harmful = false, fatal = false;

	public Food(Game game, int foodType, int startX, int startY) {
		this.game = game;
		x = startX;
		y = startY;

		switch (foodType) {

		case BOMB:
			fatal = true;
			harmful = false; // only because it's already fatal.
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
		
		case GOODAPPLE:
			harmful = false;
			weight = 25;
			image = game.getImage(game.getBase(), "img/apple.png");
			fall = "img/apple";
			break;
			
		case CHICKENWAFFLE:
			harmful = false;
			weight = 50;
			image = game.getImage(game.getBase(), "img/chickenwaffles.png");
			fall = "img/chickenwaffles";
			y = y + 40;
			break;

		case EGGPLANT:
			harmful = false;
			weight = 25;
			image = game.getImage(game.getBase(), "img/eggplant.png");
			fall = "img/eggplant";
			y = y + 40;
			break;

		case ICECREAMSUNDAE:
			harmful = false;
			weight = 50;
			image = game.getImage(game.getBase(), "img/sundae.png");
			fall = "img/sundae";
			break;
		
		case PIE:
			harmful = false;
			weight = 25;
			image = game.getImage(game.getBase(), "img/pie.png");
			fall = "img/pie";
			break;
		
		case CAKE: 
			harmful = false;
			weight = 50;
			image = game.getImage(game.getBase(), "img/cake.png");
			fall = "img/cake";
			break;
		
		case RAMEN:
			harmful = false;
			weight = 35;
			image = game.getImage(game.getBase(), "img/ramen.png");
			fall = "img/ramen";
			break;
		
		case CEREAL:
			harmful = false;
			weight = 35;
			image = game.getImage(game.getBase(), "img/cereal.png");
			fall = "img/ramen";
			break;
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

}
