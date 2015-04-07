
/**
 * This spaceship attempts to run away from the fight. It will always accelerate
 * and constantly turn away from the closest ship. If the nearest ship is closer
 * than 0.2 units, and if its angle to the Runner is less than 0.2 radians, the
 * Runner feels threatened and will attempt to teleport
 * 
 * @author Aviad Levy
 *
 */
public class RunnerShip extends SpaceShip {
	public RunnerShip(){
		super();
	}

	@Override
	public void doAction(SpaceWars game) {
		
		SpaceShip closestShip = game.getClosestShipTo(this);
        double closestShipAngle = spaceShipPhysics.angleTo(closestShip.getPhysics());
        
        //the closest ship is too close, so let's teleport
        if((spaceShipPhysics.distanceFrom(closestShip.getPhysics())<
        	CLOSE_SHIP_LENGTH) && closestShipAngle < CLOSE_SHIP_ANGLE && 
        	closestShipAngle > -CLOSE_SHIP_ANGLE) {
        	super.teleport();
        }
        
        if(closestShipAngle < 0)  //close ship is right to us, escape to left
        	spaceShipPhysics.move(ACCELERATION, LEFT);
        else                      //close ship is left to us, escape to right
        	spaceShipPhysics.move(ACCELERATION, RIGHT);
        
        super.updateStats();
	}

}
