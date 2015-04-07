
/**
 * This ship pursues other ships and tries to fire at them. It will always
 * accelerate, and turn towards the nearest ship. When its angle to the nearest
 * ship is less than 0.2 radians, it will fire.
 *
 * @author Aviad Levy
 *
 */
public class AggressiveShip extends SpaceShip {
    
	public AggressiveShip(){
		super();
	}
	
	@Override
	public void doAction(SpaceWars game) {
		
		SpaceShip closestShip = game.getClosestShipTo(this);
        double closestAngleToShip = spaceShipPhysics.angleTo(closestShip.getPhysics());
        
        if(closestAngleToShip > CLOSE_SHIP_ANGLE)
			//the ship is too left to us. we can't fire yet let's accelerate left
			spaceShipPhysics.move(ACCELERATION, LEFT);
		else if(closestAngleToShip < -CLOSE_SHIP_ANGLE)
			//the ship is too right to us. we can't fire yet let's accelerate right
			spaceShipPhysics.move(ACCELERATION, RIGHT);
		else {
			//the other ship is in our sight. let's fire
			spaceShipPhysics.move(ACCELERATION, NO_DIRECTION);
			super.fire(game);
		}
        super.updateStats();
	}

}
