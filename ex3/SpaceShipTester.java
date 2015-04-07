import static org.junit.Assert.*;
import oop.ex3.*;

import org.junit.*;


public class SpaceShipTester {

	/**
	 * This method checking all kind of spaceships we implement.
	 * 
	 */
	@Test
	public void testShipsDriver(){
		
		SpaceShip agrresive = new AggressiveShip();
		tests(agrresive);
		
		SpaceShip basher = new BasherShip();
		tests(basher);
		
		SpaceShip runner = new RunnerShip();
		tests(runner);
		
		SpaceShip drunk = new DrunkShip();
		tests(drunk);
		
		SpaceShip special = new SpecialShip();
		tests(special);
	}
	
    /**
     * This method call all the tests for given ship.
     * 
     * @param ship the ship to test
     */
    public void tests(SpaceShip ship){
    	testIsDead(ship);
    	testReset(ship);
    	testTeleport(ship);
    	testUpdatingStats(ship);
    }
	
	/**
	 * Test if spaceship resets correctly.
	 * 
	 * @param ship the ship to test
	 */
	public void testReset(SpaceShip ship) {
		SpaceShipPhysics physics = ship.getPhysics();
		ship.reset();
		
		assertNotSame(physics, ship.getPhysics());
	}

	/**
	 * Test if spaceship dies correctly.
	 * 
	 * @param ship
	 */
	public void testIsDead(SpaceShip ship) {
		ship.reset();
		
		//check colliding 20 times		
		for(int i=0; i<20; i++){
			ship.collidedWithAnotherShip();
		}
		assertTrue(ship.isDead());
		
		ship.reset();
		
		//check got shot 20 times		
		for(int i=0; i<20; i++){
			ship.gotHit();
		}
		
		assertTrue(ship.isDead());
		
		ship.reset();
		
		ship.shieldOn(); //check the same with shield
		
		//check colliding 20 times with shield
		
		for(int i=0; i<=20; i++){
			ship.collidedWithAnotherShip();
		}
		assertFalse(ship.isDead());
		
		//check got shot 20 times
		for(int i=0; i<=20; i++){
			ship.gotHit();
		}
		assertFalse(ship.isDead());
	}

	/**
	 * Test if the spaceship teleports correctly.
	 * 
	 * @param ship the ship to test
	 */
	public void testTeleport(SpaceShip ship) {
		
		ship.reset();
		SpaceShipPhysics firstPhysics = ship.getPhysics();
		ship.teleport();
		SpaceShipPhysics secondPhysics = ship.getPhysics();
		
		assertNotSame(firstPhysics, secondPhysics); //check if teleport occurred
		
		ship.teleport();
		SpaceShipPhysics thirdPhysics = ship.getPhysics();
		
		assertSame(secondPhysics, thirdPhysics); // teleort shouldn't occurred
		
	}

	/**
	 * Test if all changing in spaceship stats is changing correctly.
	 * 
	 * @param ship the ship to test
	 */
	public void testUpdatingStats(SpaceShip ship) {
		
		SpaceWars game = new SpaceWars(new String[] {"a","r", "b"});
		ship.reset();
		
		//check if fire decreasing energy
		for(int i=0; i<5; i++){
		    int energy = ship.energy;
    		ship.fire(game);
		    assertEquals(ship.energy, energy - 20);
		    ship.fireDelay = 0;
		}
		
		//check if energy increasing at end of turn
		for(int i=0; i<5; i++){
		    int energy = ship.energy;
		    ship.updateStats();
		    assertEquals(ship.energy, energy + 1);
		}
		
		ship.reset();
		ship.fire(game);
		//check if fire delay is working
		for(int i=0; i<8; i++){
			assertNotEquals(0 , ship.fireDelay);
			ship.updateStats();
		}
		assertEquals(ship.fireDelay, 0);
		
	}

}
