/**
 * This ship is able to hide in another ship. if the ship sense that another
 * ship is nearby the ship will try to teleport to the exact same location as
 * the enemy ship. if we can't teleport, let's run.
 * 
 * @author Aviad Levy
 *
 */
public class SpecialShip extends SpaceShip {
	
	private final static int MY_ENERGY_TO_TELEPORT = 150;
	
	public SpecialShip(){
		super();
	}
	
	@Override
	public void doAction(SpaceWars game) {
		
		SpaceShip closestShip = game.getClosestShipTo(this);
		double closestAngleToShip = spaceShipPhysics.angleTo(closestShip.getPhysics());
		
		if(spaceShipPhysics != closestShip.getPhysics()){
			if((spaceShipPhysics.distanceFrom(closestShip.getPhysics())<
		        	CLOSE_SHIP_LENGTH) && closestAngleToShip < CLOSE_SHIP_ANGLE && 
		        	closestAngleToShip > -CLOSE_SHIP_ANGLE) {
		        	teleport(closestShip);
		        }
			
		    if(closestAngleToShip < 0)  //close ship is right to us, escape to left
	      	    spaceShipPhysics.move(ACCELERATION, LEFT);
            else                      //close ship is left to us, escape to right
	            spaceShipPhysics.move(ACCELERATION, RIGHT);
		}
	}
	

	private void teleport(SpaceShip closest){
		if(energy >= MY_ENERGY_TO_TELEPORT){
	    	   spaceShipPhysics = closest.getPhysics();
	    	   energy -= MY_ENERGY_TO_TELEPORT;
		}
	}
}