/**
 * Its pilot had a tad too much. We leave it to your creativity to define the 
 * ship’s exact behavior, but it must include randomness and should definitely 
 * be amusing to fight against
 * 
 * @author Aviad Levy
 *
 */
public class DrunkShip extends SpaceShip {
	
	public DrunkShip(){
		super();
	}
	
	
	@Override
	public void doAction(SpaceWars game) {
		double randomSide = Math.random();
		boolean accelerate;
		
		shieldIsOn = false;
		
		//random telepoting
		if(Math.random() < .2)
			super.teleport();
		
		//random accelerate
		if(Math.random() < .9)
			accelerate = true;
		else
			accelerate = false;
		
		//random turning
		if(randomSide < .4)
			spaceShipPhysics.move(accelerate, LEFT);
		else if(randomSide > .6)
			spaceShipPhysics.move(accelerate, RIGHT);
		else
			spaceShipPhysics.move(accelerate, NO_DIRECTION);
		
		//random shield
		if(Math.random() < .2)
			super.shieldOn();
		
		//random fire
		if(Math.random() < .4)
			super.fire(game);
		
		super.updateStats();

	}
}
