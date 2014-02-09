package FoodBoy;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

public class Game extends Applet implements Runnable, KeyListener{

	private FatMan man;
	private Image image, character, background, leftGrab, leftMid, 
	leftEat, rightGrab, rightMid, rightEat;
	private LeftConveyor leftBelt;
	private RightConveyor rightBelt;
	private Graphics second;
	private URL base;
	private static Background bg1, bg2;
	
	private int weight;
	
	private Animation aniL, aniR;
	
	//private ArrayList<Food> leftFood, rightFood;

	
	//public Queue<Food> food = new LinkedList<Food>();
	
	//private int i = 0;

	@Override
	public void init() {
		setSize(1080, 720);
		setBackground(Color.WHITE);
		setFocusable(true);
		Frame frame = (Frame)this.getParent().getParent();
		frame.setTitle("Food Boy");
		frame.setResizable(false);
		weight = 0;
		addKeyListener(this);
		try{
			base = getDocumentBase();
		} catch(Exception e){
				
		}
		
		man = new FatMan(this);
		character = getImage(base, "img/newDefault.png");
		background = getImage(base, "data/background.png");
		leftBelt = new LeftConveyor(this);
		rightBelt = new RightConveyor(this);
		//food = new Food(this, Food.ICECREAMSUNDAE, 0, 240);
		
		//food.add(new Food(this, Food.ICECREAMSUNDAE, 0, 240));
		
	
		
		aniL = new Animation();
		aniR = new Animation();
		updateAnimation();
		
		//leftFood = new ArrayList<Food>();
		//rightFood = new ArrayList<Food>();


	}

	@Override
	public void start() {
		Thread thread=new Thread(this);
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
		while(true){
			rightBelt.update();
			leftBelt.update();
			//man.update();
			updateAnimation();
			animate();
			repaint();
			try{
				Thread.sleep(90);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (man.hp < 1) 
				break;
		}
	}
	
	public void animate () {
		aniL.update(150);
		aniR.update(150);
	}

	public void updateAnimation(){
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
		
		g.drawString("Weight:" +man.getWeight() + " Pounds", this.getWidth()/2-40, 30);
		g.drawString("Health:" +man.getHP(), this.getWidth()/2-40, 70);
		
		if(man.isLeft() ==false && man.isRight()==false){
			g.drawImage(man.getDefault(), this.getWidth()/2 - 355, 85, this);
		}
		if(man.isLeft() == true){
			g.drawImage(aniL.getImage(), this.getWidth()/2 - 355, 85, this);
		}
		
		if(man.isRight() == true){
			g.drawImage(aniR.getImage(), this.getWidth()/2 - 355, 85, this);
		}

		//g.drawImage(man.getImage(), 120, 75, this);
		g.drawImage(leftBelt.getImage(), 0, 300, this);
		g.drawImage(rightBelt.getImage(), this.getWidth() - rightBelt.getImage().getWidth(this), 300, this);
		//i+=5;
		/*
		for (Food e: food) {
			g.drawImage(e.getImage(), i, 240, this);
		}
		*/
		
		for (Food e: leftBelt.leftConveyor) {
			g.drawImage(e.getImage(), e.x, e.y, this);
		}
		
		for (Food e: rightBelt.rightConveyor) {
			g.drawImage(e.getImage(), e.x, e.y, this);
		}
		
		//g.drawImage(food.getImage(), 60, 240, this);
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		Food mostRecent;
		
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			man.grabLeft();
			 mostRecent = leftBelt.leftConveyor.peek();
			if (mostRecent != null && mostRecent.x >= this.getWidth()/2 - 300) {
				mostRecent = leftBelt.leftConveyor.poll();
				if (!(mostRecent.isHarmful() || mostRecent.isFatal()) & mostRecent.isEdible()) {
					man.addWeight(mostRecent.getWeight());
					mostRecent.image = null; 
					mostRecent.setEdible(false);
				}
				else{
					if (mostRecent.isFatal() && mostRecent.image != null)
						man.hp -= 5;
					else if (mostRecent.image != null){
						man.hp--;
					}
				}
						
			}
			break;

		case KeyEvent.VK_RIGHT:
			man.grabRight();
			 mostRecent = rightBelt.rightConveyor.peek();
			if (mostRecent != null && mostRecent.x <= this.getWidth()/2 + 200) {
				mostRecent = rightBelt.rightConveyor.poll();
				if (!(mostRecent.isHarmful() || mostRecent.isFatal()) & mostRecent.isEdible()) {
					man.addWeight(mostRecent.getWeight());
					mostRecent.image = null; 
					mostRecent.setEdible(false);
				}
				else {
					if (mostRecent.isFatal() && mostRecent.image != null)
						man.hp -= 5;
					else if (mostRecent.image != null){
						man.hp--;
					}
				}
						
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
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



}
