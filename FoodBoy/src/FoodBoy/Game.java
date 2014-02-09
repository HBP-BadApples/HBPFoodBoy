package FoodBoy;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

public class Game extends Applet implements Runnable, KeyListener {

	private FatMan man;
	private Image image, background, leftGrab, leftMid, leftEat, rightGrab, rightMid,
	rightEat;
	public LeftConveyor leftBelt;
	public RightConveyor rightBelt;
	private Graphics second;
	// private Food food;
	private URL base;
	private Font font = new Font("font/Ubuntu-L.ttf", Font.ITALIC, 40);

	private boolean deathBySpoil = false, gameOver = false;

	private Animation aniL, aniR, ending;

	// public Queue<Food> food = new LinkedList<Food>();

	// private int i = 0;

	@Override
	public void init() {
		setSize(1080, 720);
		setBackground(Color.WHITE);
		setFocusable(true);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Food Boy");
		frame.setResizable(false);
		addKeyListener(this);
		try {
			base = getDocumentBase();
		} catch (Exception e) {

		}

		man = new FatMan(this);
		background = getImage(base, "img/bg.png");
		leftBelt = new LeftConveyor(this);
		rightBelt = new RightConveyor(this);
		// food = new Food(this, Food.ICECREAMSUNDAE, 0, 240);

		// food.add(new Food(this, Food.ICECREAMSUNDAE, 0, 240));

		aniL = new Animation();
		aniR = new Animation();
		ending = new Animation();

		updateAnimation();

	}

	@Override
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		while (true) {
			if(!gameOver){
				rightBelt.update();
				leftBelt.update();

				updateAnimation();
				animate();

				repaint();
				try {
					Thread.sleep(60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (man.hp < 1 || gameOver) {
				boomEnd();
				gameOver = true;
				animateEnding();
				updateAnimation();
				repaint();
				try {
					Thread.sleep(60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}


	}

	public void animate() {
		aniL.update(150);
		aniR.update(150);
	}

	private void animateEnding() {
		ending.update(150);
	}


	public void updateAnimation() {
		leftGrab = man.getLeftGrab();
		leftMid = man.getLeftMid();
		leftEat = man.getLeftEat();

		rightGrab = man.getRightGrab();
		rightMid = man.getRightMid();
		rightEat = man.getRightEat();

		aniL.addFrame(leftGrab, 50);
		aniL.addFrame(leftMid, 50);
		aniL.addFrame(leftEat, 50);

		aniR.addFrame(rightGrab, 50);
		aniR.addFrame(rightMid, 50);
		aniR.addFrame(rightEat, 50);
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, this);
		g.setColor(Color.WHITE);
		g.setFont(font);
		
		if (!gameOver) {
			g.drawString("Weight (lbs): " + man.getWeight(),
					this.getWidth() / 4 - 250, 40);
			g.drawString("Watch out for ",
					this.getWidth() / 4 - 250, 300);
			g.drawString("spoiled food/bombs!", this.getWidth() / 4 - 250, 400);
			g.drawString("Health: " + man.getHP(), this.getWidth() / 2 + 300, 40);

			g.setColor(Color.BLACK);
			if (man.isLeft() == false && man.isRight() == false) {
				g.drawImage(man.getDefaultImage(), 190, 85, this);
			}
			if (man.isLeft() == true) {
				g.drawImage(aniL.getImage(), 190, 85, this);
				g.drawImage(getImage(this.base, "img/LHsploosh.png"), this.getWidth()/2-73, 140, this);
			}

			if (man.isRight() == true) {
				g.drawImage(aniR.getImage(), 190, 85, this);
				g.drawImage(getImage(this.base, "img/RHsploosh.png"), this.getWidth()/2-73, 140, this);
			}

			// g.drawImage(man.getImage(), 120, 75, this);
			g.drawImage(leftBelt.getImage(), 0, this.getHeight()
					- leftBelt.getImage().getHeight(this), this);
			g.drawImage(rightBelt.getImage(), this.getWidth()
					- rightBelt.getImage().getWidth(this), this.getHeight()
					- rightBelt.getImage().getHeight(this), this);
			// i+=5;
			/*
			 * for (Food e: food) { g.drawImage(e.getImage(), i, 240, this); }
			 */

			for (Food e : leftBelt.leftConveyor) {
				g.drawImage(e.getImage(), e.x, e.y, this);
			}

			for (Food e : rightBelt.rightConveyor) {
				g.drawImage(e.getImage(), e.x, e.y, this);
			}



		}
		else if (gameOver) {
			g.drawImage(ending.getImage(), 0, 0, this);
			g.setFont(font);
			g.drawString("Game Over!", this.getWidth()/2 - 110, this.getHeight()/2);
			g.drawString("You gained " + (man.getWeight() - 120) + " pounds!", this.getWidth()/2 - 200, this.getHeight()/2 + 40);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		Food mostRecent;

		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			man.grabLeft();
			mostRecent = leftBelt.leftConveyor.peek();
			if (mostRecent != null && mostRecent.x >= this.getWidth() / 2 - 300) {
				mostRecent = leftBelt.leftConveyor.poll();
				if (!(mostRecent.isHarmful() || mostRecent.isFatal())) {
					man.addWeight(mostRecent.getWeight());
					mostRecent.image = null;
					mostRecent = null;
				} else if (mostRecent.isFatal() && mostRecent.image != null)
					man.hp -= 5;
				else if (mostRecent.image != null) {
					man.hp--;

					if (man.hp < 1)
						deathBySpoil = true;
				}
			}
			break;

		case KeyEvent.VK_RIGHT:
			man.grabRight();
			mostRecent = rightBelt.rightConveyor.peek();
			if (mostRecent != null && mostRecent.x <= this.getWidth() / 2 + 200) {
				mostRecent = rightBelt.rightConveyor.poll();
				if (!(mostRecent.isHarmful() || mostRecent.isFatal())) {
					man.addWeight(mostRecent.getWeight());
					mostRecent.image = null;
					mostRecent = null;
				} else if (mostRecent.isFatal() && mostRecent.image != null)
					man.hp -= 5;
				else if (mostRecent.image != null) {
					man.hp--;
					if (man.hp < 1)
						deathBySpoil = true;
				}
			}
			break;
		
		case KeyEvent.VK_SPACE:
			if (gameOver) {
				init();
				gameOver = false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			man.stopLeft();
			break;

		case KeyEvent.VK_RIGHT:
			man.stopRight();
			break;
		}
	}

	public URL getBase() {
		return base;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public FatMan returnMan () {
		return man;
	}

	public void boomEnd () {
		for(int i = 1; i <= 6; i++) {
			ending.addFrame(getImage(base, "img/explosion" + i +".png"), (long) 250);

			if (image == null) {
				image = createImage(this.getWidth(), this.getHeight());
				second = image.getGraphics();
			}
			else
				second.drawImage(ending.getImage(), this.getWidth()/2, this.getHeight()/2, this);
		}
	}
}