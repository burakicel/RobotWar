package robotWarSummative;

public class IcelBotOppData extends OppData {

	//Variables
	private int counter = 0;
	private int oldAvenue;
	private int oldStreet;
	private int movementAmount = 0 ;
	
	/**
	 * Constructor
	 * @param id = Id of the robot
	 * @param a = avenue of the robot
	 * @param s = street of the robot
	 * @param health = health of the robot
	 */
	public IcelBotOppData(int id, int a, int s, int health) {
		super(id, a, s, health);
		oldAvenue = a;
		oldStreet = s;
	}
	
	/**
	 * Changes the amount of steps the robot took
	 * @param avenue = Opponent Robot's current avenue
	 * @param street = Opponent Robot's current street
	 */
	public void setMovementAmount(int avenue, int street){
		
		if(this.counter == 0){
			//Sets up the old avenues for the first time
			this.oldAvenue = avenue;
			this.oldStreet = street;
			this.counter += 1;
			
		}
		else{
			//Adds in the number of steps taken by the opponent robot
			this.movementAmount += Math.abs(this.oldAvenue-avenue);
			this.movementAmount += Math.abs(this.oldStreet-street);
			this.oldAvenue = avenue;
			this.oldStreet = street;
			
		}
		
		
	}
	
	/**
	 * Returns / gives the amount of the steps the opponent robot took
	 * @return
	 */
	public int getMovementAmount(){
		
		return this.movementAmount;
		
	}
	
}
