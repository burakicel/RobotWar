package robotWarSummative;

import java.awt.Color;
import becker.robots.City;
import becker.robots.Direction;

public class IcelBot extends FighterRobot {

	//Variables
	private int health=100;
	private int starter = 0;
	private int numStepsAllowed;
	private int oppDataCounter;
	private IcelBotOppData[] extendedData = null;
	private IcelBotOppData [] movementList;
	

	/**
	 * IlluminaBot Constructor
	 * @param arena = the environment where the battle is carried out
	 * @param a = starting avenue of the robot
	 * @param s = starting street of the robot
	 * @param direction = starting direction of the robot
	 * @param id = id of the robot
	 * @param attack = attack point of the robot
	 */
	public IcelBot(City arena, int a, int s,Direction direction, int id, int attack) {

		super(arena,a,s,direction,id,5, 2, 3);
		this.setLabel();

	}

	/**
	 * Set Label
	 */
	public void setLabel(){
		
		this.setColor(Color.WHITE);
		this.setLabel(Integer.toString(health));

		//Makes robot go black once health reaches zero
		if (this.health <= 0){
			this.setColor(Color.BLACK);
		}
	}

	/**
	 *  Makes Robot Go to a coordinate
	 * @param avenue = avenue that the robot will travel to
	 * @param street = street that the robot will travel to
	 */
	public void goToLocation(int avenue, int street){
		
		//Goes to the wanted avenue
		//Checks if the location desired is in east or west of the robot
		if (this.getAvenue() < avenue){
			
			//Makes robot face east
			while(this.isFacingEast() == false){
				this.turnLeft();
			}
			
			//Makes robot travel until it reaches an appropriate avenue
			while(this.getAvenue() != avenue){
				this.move();
			}
		}
		else if (this.getAvenue() > avenue){
			
			//Makes robot face west
			while(this.isFacingWest() == false){
				this.turnLeft();
			}
			
			//Makes robot travel until it reaches an appropriate avenue
			while(this.getAvenue() != avenue){
				this.move();
			}
			
		}
		
		//Goes to the desired street
		//Checks if the location desired is in south or north of the street
		if (this.getStreet() < street){
			
			//Makes robot face south
			while(this.isFacingSouth() == false){
				this.turnLeft();
			}
			
			//Makes robot travel until it reaches an appropriate street
			while(this.getStreet() != street){
				this.move();
			}
		}
		else if (this.getStreet() > street){
			
			//Makes robot face north
			while(this.isFacingNorth() == false){
				this.turnLeft();
			}
			
			//Makes robot travel until it reaches an appropriate street
			while(this.getStreet() != street){
				this.move();
			}
			
		}
			

	}

	/**
	 * TurnRequest, requests turns for the robot
	 * @param a = energy
	 * @param w = Opponent Data
	 */
	public TurnRequest takeTurn(int energy, OppData[] w){

		
		//Variables
		TurnRequest request;
		IcelBotOppData [] extendedData = oppDataPrepare(w);
		
		this.starter += 1;
						
		//Resets health and labeling
		this.health = w[this.getID()].getHealth();
		this.setLabel();

		//Passes in an extended oppdata to determine the target
		int target = this.target(extendedData);
				
		//Gets the avenue and street of the target robot
		int turnX = extendedData[target].getAvenue();
		int turnY = extendedData[target].getStreet();
		
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
		
		
		//Updates Number of the steps allowed
		this.numStepsAllowed = this.getNumMoves();


		//Decided what to request based on it's position and energy
		if (w[target].getStreet() == goY && w[target].getAvenue() == goX && energy>=20){
			request = new TurnRequest(goX, goY, target, energy/20);
		}
		else {
			request = new TurnRequest(goX, goY, -1, 0);
		}

		
		//Returns the request
		return request;

	}



	/**
	 * Prepares OppData Extension and makes it ready to be usable
	 * @param w = Regular OppData
	 * @return oppDataPrepare = Updated and Extended OppData
	 */
	private IcelBotOppData[] oppDataPrepare(OppData[] w) {
		
		//Checks if this is the first the oppdata gets expanded
		if(this.oppDataCounter == 0)
		{

			extendedData = new IcelBotOppData[w.length];
			movementList = new IcelBotOppData[w.length];
			
			//Transfers every value to the extended data
			for (int i=0; i< w.length ; i++){
				
				extendedData[i] = new IcelBotOppData(w[i].getID(), w[i].getAvenue(), w[i].getStreet(), w[i].getHealth());
				movementList[i] = new IcelBotOppData(w[i].getID(), w[i].getAvenue(), w[i].getStreet(), w[i].getHealth());
			}
			
			this.oppDataCounter += 1;
			
		}
		else
		{
			//Updates the extended opp data
			for (int i=0; i< extendedData.length ; i++)
			{	
				extendedData[i].setStreet(w[i].getStreet());
				extendedData[i].setAvenue(w[i].getAvenue());
				extendedData[i].setHealth(w[i].getHealth());
				extendedData[i].setMovementAmount(w[i].getAvenue(), w[i].getStreet());
			}
			
		}
		
		return extendedData;
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
	 * Calculates the best target to attack on
	 * @param w = Extended OppData
	 * @return target = The Id of the target
	 */
	private int target(IcelBotOppData[] w) {

		int holder = -10;
		
		OppData[] healthList = new IcelBotOppData[w.length];
		OppData[] distanceList = new IcelBotOppData[w.length];
		
		//Sets the movementList file, if the program runs for the first time
		if (this.starter == 0){
			movementList = new IcelBotOppData[w.length];
		}

		//Sets the movement list, inputs in values
		for (int i =0; i<w.length; i++){

			if(this.starter == 0){
				this.movementList[i] = new IcelBotOppData(w[i].getID(),w[i].getAvenue(),w[i].getStreet(),w[i].getHealth());
			}
			else {
				this.movementList[i].setMovementAmount(w[i].getAvenue(), w[i].getStreet());
			}
		}
		
		this.starter += 1;
		
		//Sorts the lists based on health, distance, movement amount
		healthList = this.sortHealth(w);
		distanceList = this.sortDistance(w);
		
		
		//Finds the ideal target based on health and distance
		holder = findIdealTarget(healthList,distanceList);
		//distanceList = this.sortDistance(w);
		

		return holder;
	}


	/**
	 * Given Health and Distance tries to calculate the best target
	 * It DOES NOT USE EXTENDED OPP DATA INFORMATION
	 * @param healthList = a list for the health of the opponent robots
	 * @param distanceList = a list for the distance of the opponent robots
	 * @return int = The Id of the potential target
	 */
	private int findIdealTarget(OppData[] healthList, OppData[] distanceList) {
		
		//Variables
		int average;
		int smallestAverage = -10;
		int holder = 0;

			//Counts from healthlist
			for(int i=0; i<healthList.length; i++){

					//Counts from distance list
					for(int q=0; q<distanceList.length; q++){

						//Checks if the opponents match up
						if (healthList[i].getID() == distanceList[q].getID()){

							//Ads the average
							average = i + q;

							//Finds the smallest average
							if (smallestAverage == -10 || average < smallestAverage){
								smallestAverage = i+q;
								holder = distanceList[q].getID();

							}

						}

					}

			}


	return holder;
}

	/**
	 * Sorts the OppData based on the distance
	 * @param w = Opponent data that is unsorted
	 * @return oppData[] = sorted opponent data based on distance
	 */
	private OppData[] sortDistance(OppData[] w) {
		OppData [] distanceList = new OppData[w.length];
		
		//Duplicates the list
		for (int i =0; i<w.length; i++){
			distanceList[i] = w[i];
		}

		//Variables
		int closeX = 0;
		int closeY = 0;
		int holder = 0;
		int totalDistance = 1000;
		int enemyCounter = 0;
		
		//Counts the opponents
		for (int q=0; q<distanceList.length;q++){
			
			if (distanceList[q].getHealth() > 0){
				enemyCounter += 1;
			}
			
		}
		
		//Size^2 efficiency, Selection Sorting
		for (int q = 0; q<distanceList.length;q++){

			for (int i = q; i<distanceList.length;i++){

				//Checks if he targets himself or a dead person
				if (this.getID() != i && w[i].getHealth()>0){

					closeX = Math.abs(w[i].getAvenue()-this.getAvenue());
					closeY = Math.abs(w[i].getStreet()-this.getStreet());
					
					//Finds the smallest distance
					if(closeX+closeY<totalDistance && i>holder){
						totalDistance = closeX+closeY;
						holder = i;
					}

				}

			}
			
			//Places the smallest distance in the beginning of the street
			distanceList[q] = w[holder];
			//holder = -10;
			totalDistance = 1000;


		}
		
		return distanceList;
	}

	/**
	 * Sorts OppData based on the health
	 * @param w = unsorted opponent data based on health
	 * @return OppData[] = sorted opponent data based on health
	 */
	private OppData[] sortHealth(OppData[] w) {
		
		//Variables
		int holder = 0;
		int health = 101;
		int enemyCounter = 0;
		OppData [] healthList = new OppData[w.length];
		
		//Duplicates the health list
		for (int i =0; i<w.length; i++){
			healthList[i] = w[i];
		}

		//Counts the enemies
		for (int q=0; q<healthList.length;q++){
			
			if (healthList[q].getHealth() > 0){
				enemyCounter += 1;
			}
			
		}
		
		//Selections sort, size^2 efficiency
		for (int q = 0; q<healthList.length;q++){

			for (int i = q; i<healthList.length;i++){

				//Checks if it attacks himself or a dead robot
				if (this.getID() != i && w[i].getHealth()>0){

					//Finds the smallest health
					if(w[i].getHealth()<health && i>holder){
						health = w[i].getHealth();
						holder = i;
					}

				}

			}
			
			//Places the smallest value to the beginning of the file
			healthList[q] = w[holder];
			health = 101;


		}
		
		return healthList;
	}

	/**
	 * Receives Battle Results
	 * @param a = Health Lost
	 * @param b = Opponent Id
	 * @param c = Opponent Health Lost
	 */
	public void battleResult(int a,int b,int c){
		
		//Edits Health
		this.health = this.health-a;
		
		this.setLabel();

	}

}