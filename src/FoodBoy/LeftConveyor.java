package FoodBoy;

import java.awt.Image;
import java.util.LinkedList;
import java.util.Queue;

public class LeftConveyor{

	public Queue<Food> leftConveyor;
	private Image[] images;
	private int stepper;
	private int count;
	private Game game;


	public LeftConveyor(Game game) {
		this.game = game;
		leftConveyor = new LinkedList<Food>();
		images = new Image[3];
		initImages(game);
		//leftConveyor.add(new Food(game, Food.ICECREAMSUNDAE, -50, 240));
	}

	private void initImages(Game game) {
		images[0] = game.getImage(game.getBase(), "img/leftbelt1.png");
		images[1] = game.getImage(game.getBase(), "img/leftbelt2.png");
		images[2] = game.getImage(game.getBase(), "img/leftbelt3.png");
	}

	public Image getImage() {
		return images[stepper % images.length];
	}

	public void update() {
		stepper++;
		count = 0;
		if (stepper % 13 == 0)
			leftConveyor.add(new Food(game,(int) (Math.random()*Food.NUMBER_FOODS), -100, 500));
		
		for (Food e: leftConveyor) {
			if (e.x < this.getImage().getWidth(game) - 50)
				e.moveRight();
			else
				if (e.x < this.getImage().getWidth(game)) 
					e.fallFortyFiveL();
				else
					if (e.x < this.getImage().getWidth(game) + 10 && e.y < 525)
						e.fallNinetyL();
					else {
						e.image = null;
						count++;
					}
		}
		for(int i = 0; i < count; i++)
			leftConveyor.poll();
	}


}
