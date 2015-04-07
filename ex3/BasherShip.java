
/**
 * This ship attempts to collide with other ships. It will always accelerate,
 * and constantly turn towards the closest ship. If it gets within a distance 
 * of 0.2 units from another ship, it will turn on its shields
 * 
 * @author Aviad Levy
 *
 */
public class BasherShip extends SpaceShip {
    
	public BasherShip(){
		super();
	}
	
	@Override
	public void doAction(SpaceWars game) {
		shieldIsOn = false;
		
		SpaceShip closestShip = game.getClosestShipTo(this);
		double closestAngleToShip = spaceShipPhysics.angleTo(closestShip.getPhysics());
		
		if(closestAngleToShip > 0)
			//the ship is left to us. let's accelerate left
			spaceShipPhysics.move(ACCELERATION, LEFT);
		else if(closestAngleToShip < 0)
			//the ship is right to us. let's accelerate right
			spaceShipPhysics.move(ACCELERATION, RIGHT);
		else
			//the ship is ahead to us. let's accelerate without turning
			spaceShipPhysics.move(ACCELERATION, NO_DIRECTION);
	
		if(spaceShipPhysics.distanceFrom(closestShip.getPhysics()) < CLOSE_SHIP_LENGTH)
			//we close. let's put our shield on and go collide.
			super.shieldOn();
        
		super.updateStats();
	}
}
