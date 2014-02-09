package FoodBoy;

import java.awt.Image;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.Queue;

public class RightConveyor{

	public Queue<Food> rightConveyor;
	private Image[] images;
	private int stepper;
	private int count;

	private Game game;

	public RightConveyor(Game game) {
		this.game = game;
		//rightConveyor = new Queue<Food>();
		images = new Image[3];
		initImages(game);
		rightConveyor = new LinkedList<Food>();
	}

	private void initImages(Game game) {
		images[0] = game.getImage(game.getBase(), "img/rightbelt1.png");
		images[1] = game.getImage(game.getBase(), "img/rightbelt2.png");
		images[2] = game.getImage(game.getBase(), "img/rightbelt3.png");
	}

	public Image getImage() {
		return images[stepper % images.length];
	}

	public void update() {
		stepper++;
		//count = 0;
		if (stepper % 13 == 0)
			rightConveyor.add(new Food(game,(int) (Math.random()*5)+1, game.getWidth(), 500));

		try { 
			
		for (Food e: rightConveyor) {
			if (e.x > game.getWidth() - this.getImage().getWidth(game) - 20)
				e.moveLeft();
			else
				if (e.x > game.getWidth() - this.getImage().getWidth(game) - 70) 
					e.fallFortyFiveR();
				else
					if (e.x > game.getWidth() - this.getImage().getWidth(game) - 90 && e.y < 525)
						e.fallNinetyR();
					else {
						if (!e.isFatal() && !e.isHarmful())
							game.returnMan().hp--;
						e.image = null;
						rightConveyor.poll();
						//count++;
					}
		}
		}
		catch (ConcurrentModificationException e) {
			
		}
		
	}


}
