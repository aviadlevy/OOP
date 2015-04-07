/**
 * This class has a single static method (createSpaceships(String[])).
 * It is used by the supplied driver to create all the spaceship objects 
 * according to the command line arguments.
 * 
 * @author Aviad Levy
 *
 */
public class SpaceShipFactory {
	private final static char HUMAN = 'h', RUNNER = 'r', BASHER = 'b', AGGRESIVE ='a',
			DRUNK = 'd', SPECIAL = 's';
    
	/**
	 * Creates spaceships.
	 * 
	 * @param args array which indicates the type of spaceships to create.
	 * @return array of spaceships
	 */
	public static SpaceShip[] createSpaceShips(String[] args) {
        if(args == null)
        	return null;
        
        SpaceShip[] ships = new SpaceShip[args.length];
        
        for(int i=0; i < ships.length; i++){
        	
        	if(args[i].length() != 1){ //check if the word is more then 1 letter
        		ships[i] = null;
        		continue;
        	}
        	
        	switch(args[i].charAt(0)){
        	
        	case HUMAN:
        		ships[i] = new HumanShip();
        		break;
        	
        	case RUNNER:
        		ships[i] = new RunnerShip();
        		break;
        		
        	case BASHER:
        		ships[i] = new BasherShip();
        		break;
        		
        	case AGGRESIVE:
        		ships[i] = new AggressiveShip();
        		break;
        		
        	case DRUNK:
        		ships[i] = new DrunkShip();
        		break;
        	
        	case SPECIAL:
        		ships[i] = new SpecialShip();
        		break;
        	
        	default:
        		ships[i] = null; //the letter is not one of the ships letter
        	}        	
        }
        
        return ships;
    }
}
