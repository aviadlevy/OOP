import java.awt.Image;
import oop.ex3.*;


/**
 * Controlled by the user. The keys are: left-arrow and right-arrow to turn, 
 * up-arrow to accelerate, ’d’ to fire a shot, ’s’ to turn on the shield, 
 * ’a’ to teleport.
 * 
 * @author Aviad Levy
 *
 */
public class HumanShip extends SpaceShip {
    public HumanShip(){
    	super();
    }
	@Override
	public void doAction(SpaceWars game) {
		
		shieldIsOn = false;
		
		GameGUI gui = game.getGUI();
		
		if(gui.isTeleportPressed()){
			super.teleport();
		}
		
		
		if(gui.isLeftPressed())
			spaceShipPhysics.move(gui.isUpPressed(), LEFT);
		else if(gui.isRightPressed())
			spaceShipPhysics.move(gui.isUpPressed(), RIGHT);
		else
			spaceShipPhysics.move(gui.isUpPressed(), NO_DIRECTION);
		
		if(gui.isShieldsPressed()){
			super.shieldOn();
		}
		
		if(gui.isShotPressed()){
			super.fire(game);
		}
		
		super.updateStats();
	}
    
	@Override
	public Image getImage(){
		//return the human spaceship instead of the default (enemy spaceship)
		if(super.shieldIsOn) 
			return GameGUI.SPACESHIP_IMAGE_SHIELD;
		else
			return GameGUI.SPACESHIP_IMAGE;
	}
}