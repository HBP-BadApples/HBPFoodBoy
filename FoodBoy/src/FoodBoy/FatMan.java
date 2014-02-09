package FoodBoy;

import java.awt.Image;

public class FatMan {

	private boolean left = false;
	private boolean right = false;
	public int hp = 3;
	private Game game;
	private int weight = 120;

	//private Animation grabLeft, grabRight; // releaseLeft, releaseRight;

	public FatMan(Game game){
		this.game = game;
	}

	public void grabLeft(){
		this.left = true;
	}

	public void grabRight(){  
		this.right = true;
	}

	public void stopLeft(){
		this.left = false;
	}

	public void stopRight(){
		this.right = false;
	}

	public boolean isLeft(){
		return left;
	}

	public boolean isRight(){
		return right;
	}

	public void setHP(int health){
		hp = health;
	}

	public int getHP(){
		return hp;
	}

	public int getWeight() {
		return weight;
	}

	public void addWeight (int weight) {
		this.weight += weight;
	}

	public Image getDefaultImage() {
		if (weight < 200) {
			return game.getImage(game.getBase(), "img/newDefault.png");
		}
		else if (weight >= 200 && weight < 500) {
			return game.getImage(game.getBase(), "img/v3default.png");
		}
		else if (weight >= 500 && weight < 1000){
			return game.getImage(game.getBase(), "img/v5default.png");
		}
		else {
			return game.getImage(game.getBase(), "img/v7default.png");
		}
	}

	public Image getLeftGrab(){
		if(weight < 200 ){
			return game.getImage(game.getBase(), "img/leftGrab.png");
		}
		else if(weight >= 200 && weight < 500){
			return game.getImage(game.getBase(), "img/v3leftgrab.png");
		}
		else if(weight >= 500 && weight < 1000){
			return game.getImage(game.getBase(), "img/v5leftgrab.png");

		}
		else{
			return game.getImage(game.getBase(), "img/v7leftgrab.png");
		}
	}

	public Image getLeftMid(){
		if(weight < 200 ){
			return game.getImage(game.getBase(), "img/leftMid.png");
		}
		else if(weight >= 200 && weight < 500){
			return game.getImage(game.getBase(), "img/v3leftmid.png");
		}
		else if(weight >= 500 && weight < 1000){
			return game.getImage(game.getBase(), "img/v5leftmid.png");

		}
		else{
			return game.getImage(game.getBase(), "img/v7leftmid.png");
		}
	}

	public Image getLeftEat(){
		if(weight < 200 ){
			return game.getImage(game.getBase(), "img/leftEat.png");
		}
		else if(weight >= 200 && weight < 500){
			return game.getImage(game.getBase(), "img/v3lefteat.png");
		}
		else if(weight >= 500 && weight < 1000){
			return game.getImage(game.getBase(), "img/v5lefteat.png");

		}
		else{
			return game.getImage(game.getBase(), "img/v7lefteat.png");
		}
	}

	public Image getRightGrab(){
		if(weight < 200 ){
			return game.getImage(game.getBase(), "img/rightGrab.png");
		}
		else if(weight >= 200 && weight < 500){
			return game.getImage(game.getBase(), "img/v3rightgrab.png");
		}
		else if(weight >= 500 && weight < 1000){
			return game.getImage(game.getBase(), "img/v5rightgrab.png");

		}
		else{
			return game.getImage(game.getBase(), "img/v7rightgrab.png");
		}
	}

	public Image getRightMid(){
		if(weight < 200 ){
			return game.getImage(game.getBase(), "img/rightMid.png");
		}
		else if(weight >= 200 && weight < 500){
			return game.getImage(game.getBase(), "img/v3rightmid.png");
		}
		else if(weight >= 500 && weight < 1000){
			return game.getImage(game.getBase(), "img/v5rightmid.png");

		}
		else{
			return game.getImage(game.getBase(), "img/v7rightmid.png");
		}
	}

	public Image getRightEat(){
		if(weight < 200 ){
			return game.getImage(game.getBase(), "img/rightEat.png");
		}
		else if(weight >= 200 && weight < 500){
			return game.getImage(game.getBase(), "img/v3righteat.png");
		}
		else if(weight >= 500 && weight < 1000){
			return game.getImage(game.getBase(), "img/v5righteat.png");
		}
		else{
			return game.getImage(game.getBase(), "img/v7righteat.png");
		}
	}

}
