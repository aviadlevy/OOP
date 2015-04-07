
/**
 * data structure that will help us "flag" the cell if it deleted
 * 
 * @author Aviad Levy
 *
 */
public class OpenHashSetData {
    
	public String key;
	public boolean isDeleted;
	
	public OpenHashSetData(){
		this.key = null;
		this.isDeleted = false;
	}
}