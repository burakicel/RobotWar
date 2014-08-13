package robotWarSummative;

import java.awt.Color;
import becker.robots.City;
import becker.robots.Direction;

public class TestFighterRobot2 extends FighterRobot {

	//Variables
	private int health = 100;
	private int numStepsAllowed;

	/**
	 * Constructor Method
	 * @param arena = city of the robot
	 * @param a = starting avenue of the robot
	 * @param s = starting street of the robot
	 * @param direction = starting direction of the robot
	 * @param id = id of the robot
	 * @param attack = attack power of the robot
	 */
	public TestFighterRobot2(City arena, int a, int s,Direction direction, int id, int attack) {

		super(arena,a,s,direction,id,5, 2, 3);
		this.setLabel();

	}

	/**
	 * Set's the robots label
	 */
	public void setLabel(){
		this.setColor(Color.BLUE);
		this.setLabel(Integer.toString(health));
		//this.setLabel(Integer.toString(this.getID()));

		if (this.health <= 0){
			this.setColor(Color.BLACK);
		}
	}

	/**
	 * Goes to a coordinate given
	 * @param q = avenue of the desired location
	 * @param w = street of the desired location
	 */
	public void goToLocation(int q, int w){
		
		int numSteps = 0;
		System.out.println("HELLLLLLOOOOOOOO : "+q+"  "+w);
		
		if (this.getAvenue() < q){
			
			while(this.isFacingEast() == false){
				this.turnLeft();
			}
			
			while(this.getAvenue() != q ){
				this.move();
				numSteps += 1;
			}
		}
		else if (this.getAvenue() > q){
			
			while(this.isFacingWest() == false){
				this.turnLeft();
			}
			
			while(this.getAvenue() != q ){
				this.move();
				numSteps += 1;
			}
			
		}
		
		if (this.getStreet() < w){
			
			while(this.isFacingSouth() == false){
				this.turnLeft();
			}
			
			while(this.getStreet() != w ){
				this.move();
				numSteps += 1;
			}
		}
		else if (this.getStreet() > w){
			
			while(this.isFacingNorth() == false){
				this.turnLeft();
			}
			
			while(this.getStreet() != w ){
				this.move();
				numSteps += 1;
			}
			
		}
			

	}

	/**
	 * Requests turn from the battle manager
	 * @param energy = the energy that the robot has
	 * @param w = the information about the opponents
	 */
	public TurnRequest takeTurn(int energy, OppData[] w){

		TurnRequest burak;

		this.health = w[this.getID()].getHealth();
		this.setLabel();

		int target = this.target(w,this.getAvenue(),this.getStreet(),this.getID());
		System.out.println("HELLLLLLOOOOOOOO : "+target);
		
		int turnX = w[target].getAvenue();
		int turnY = w[target].getStreet();

		//Changes amount of steps based on energy
		if (energy > 15){
			this.numStepsAllowed = 3;
		}
		else{
			this.numStepsAllowed = energy/5;
		}

		//Makes robot go to the calculated allowed coordinate
		int goX = calculateXLocation(turnX);
		int goY = calculateYLocation(turnY);
		
		
		//this.goToLocation(turnX, turnY,this.getNumMoves());

		if (w[target].getStreet() == this.getStreet() && w[target].getAvenue() == this.getAvenue()){
			burak = new TurnRequest(goX, goY, target, energy/20);
		}
		else {
			burak = new TurnRequest(goX, goY, -1, 0);
		}

		
		return burak;

	}
	

	/**
	 * Travels to a y location based on the maximum number of moves allowed
	 * @param turnY = Y location or street that the robot wants to move
	 * @return calculateYLocation = The actual street that the robot is allowed to move into
	 */
	private int calculateYLocation(int turnY) {
		
		int currentY = this.getStreet();
		
		//Checks whether the target location is at south or north
		if (currentY > turnY){
			
			//Counts while the number of steps allowed is not breached
			while (turnY < currentY && 0 < this.numStepsAllowed){
				
				this.numStepsAllowed -= 1;
				currentY -= 1;
				
			}
			
		}
		else if (currentY < turnY)
		{	
			//Counts while the number of steps allowed is not breached
			while (turnY > currentY && 0 < this.numStepsAllowed){
				
				this.numStepsAllowed -= 1;
				currentY += 1;
				
			}
			
		}
		return currentY;
		
	}

	/**
	 * Calculates the maximum point that my robot is allowed to move in x direction
	 * @param turnX = avenue of the enemy
	 * @return currentX = avenue that my robot is allowed to move to
	 */
	private int calculateXLocation(int turnX) {
		
		int currentX = this.getAvenue();
		
		//Checks whether the target location is at east or west
		if (currentX > turnX){
			
			//Counts while the number of steps allowed is not breached
			while (turnX < currentX && 0 < this.numStepsAllowed){
				
				this.numStepsAllowed -= 1;
				currentX -= 1;
				
			}
			
		}
		else if (currentX < turnX){
			
			//Counts while the number of steps allowed is not breached
			while (turnX > currentX && 0 < this.numStepsAllowed){
				
				this.numStepsAllowed -= 1;
				currentX += 1;
				
			}
			
		}
		return currentX;
	}

	/**
	 * Selects a target opponent to attack
	 * @param w = additional information about the opponents
	 * @param avenue = avenue of the robot
	 * @param street = street of the robot
	 * @param j = Id of the robot
	 * @return the id of the target robot
	 */
	private int target(OppData[] w, int avenue, int street, int j) {

		int holder = -10;
		int health = 0;

		for (int i = 0; i<w.length;i++){

			if (j != i && w[i].getHealth()>0){

				if(holder<0 || w[i].getHealth()<health){
					health = w[i].getHealth();
					holder = i;
				}

			}


		}

		return holder;
	}

	/**
	 * Receives Battle Results
	 * @param a = Health Lost
	 * @param b = Opponent Id
	 * @param c = Opponent Health Lost
	 */
	public void battleResult(int a,int b,int c){
		
		this.health = this.health-a;
		
		this.setLabel();

	}

}