import java.awt.Image;
import oop.ex3.*;

/**
 * The API spaceships need to implement for the SpaceWars game. 
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 *  a base class for the other spaceships or any other option you will choose.
 *  
 * @author oop
 */
public abstract class SpaceShip{
	
	private int health, maxEnergy;
	protected int energy, fireDelay;
	protected boolean shieldIsOn;
	protected SpaceShipPhysics spaceShipPhysics = new SpaceShipPhysics();
	
	private static final int GETTING_HIT_ENERGY = 10, GETTING_HIT_HEALTH = 1, 
	    HEALTH_AT_START = 20, ENERGY_AT_START = 200, DEATH_LEVEL = 0,
	    END_OF_TURN_ENERGY = 1, ENERGY_TO_FIRE = 20, ENERGY_TO_TELEPORT = 150,
	    ENERGY_TO_SHIELD = 3, START_DELAY = 8, STOP_DELAY = 0;
    protected static final int RIGHT = -1, LEFT = 1, NO_DIRECTION = 0;
    protected final static double CLOSE_SHIP_LENGTH = 0.2, CLOSE_SHIP_ANGLE = 0.2;
    protected final static boolean ACCELERATION = true;
	
	
    /**
     * the constructor for our spaceships, with the physic location, current energy,
     * max energy, health, shield status and whether we can fire.
     */
    public SpaceShip(){
		shieldIsOn = false;
		energy = ENERGY_AT_START;
		maxEnergy = ENERGY_AT_START;
		health = HEALTH_AT_START;
		spaceShipPhysics = new SpaceShipPhysics();
		fireDelay = STOP_DELAY;
	}
    
    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
    public abstract void doAction(SpaceWars game);

    /**
     * This method is called every time a collision with this ship occurs 
     */
    public void collidedWithAnotherShip(){
        if(!shieldIsOn){
        	health -= GETTING_HIT_HEALTH;
        	maxEnergy -= GETTING_HIT_ENERGY;
        	
        	//check if max energy is still bigger/equal to current energy
        	if(maxEnergy < energy)
        		energy = maxEnergy;
        }
    }

    /** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset(){
        shieldIsOn = false;
        energy = ENERGY_AT_START;
        maxEnergy = ENERGY_AT_START;
        health = HEALTH_AT_START;
        spaceShipPhysics = new SpaceShipPhysics();  //to give the ship new location
    }

    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
    	if(health == DEATH_LEVEL)
    		return true;
    	else
            return false;
    }

    /**
     * Gets the physics object that controls this ship.
     * 
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return spaceShipPhysics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
    	if(!shieldIsOn){
    	    health -= GETTING_HIT_HEALTH;
    	    maxEnergy -= GETTING_HIT_ENERGY;

    	    //check if max energy is still bigger/equal to current energy
        	if(maxEnergy < energy)
        		energy = maxEnergy;
    	}
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
    public Image getImage(){
    	if(shieldIsOn)
    		return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
    	else
    		return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }

    /**
     * Attempts to fire a shot.
     * 
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if(energy >= ENERGY_TO_FIRE && fireDelay == STOP_DELAY){
        	game.addShot(spaceShipPhysics);
        	energy -= ENERGY_TO_FIRE;
        	fireDelay = START_DELAY;
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if(energy >= ENERGY_TO_SHIELD) {
        	shieldIsOn = true;
        	energy -= ENERGY_TO_SHIELD;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
       if(energy >= ENERGY_TO_TELEPORT){
    	   spaceShipPhysics = new SpaceShipPhysics();
    	   energy -= ENERGY_TO_TELEPORT;
       }
    }
    
    /**
     * Adds energy and update fire delay.
     */
    protected void updateStats(){
    	/*check if we can add all the energy that we can give the ship,
    	or in case we'll do so, we'll pass max energy allowed*/
    	if(energy < (maxEnergy - END_OF_TURN_ENERGY))
    		energy += END_OF_TURN_ENERGY;
    	//if we can't add all the energy, then energy is max energy
    	else
    		energy = maxEnergy;
    	
    	if(fireDelay > STOP_DELAY)
    		fireDelay--;
    }
}
