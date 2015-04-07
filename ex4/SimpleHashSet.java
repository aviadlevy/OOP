
/**
 * A superclass for implementations of hash-sets implementing the SimpleSet interface.
 * 
 * @author Aviad Levy
 *
 */
public abstract class SimpleHashSet implements SimpleSet {
	protected static final int DEFAULT_CAPACITY = 16;
	protected static final float DEFAULT_LOW_LOAD = 0.25f, DEFAULT_UP_LOAD = 0.75f;
	
	protected int capacity, numOfElem;
	protected static float lowerLoad, upperLoad;	
	
	/**
	 * constructor with default values
	 */
	public SimpleHashSet() {
	    capacity = DEFAULT_CAPACITY;
	    upperLoad = DEFAULT_UP_LOAD;
	    lowerLoad = DEFAULT_LOW_LOAD;
	    numOfElem = 0;
	}
	
	/**
	 * constructor with upper and lower factors define by user
	 * 
	 * @param upperLoadFactor
	 * @param lowerLoadFactor
	 */
	public SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){
		capacity = 16;
		upperLoad = upperLoadFactor;
		lowerLoad = lowerLoadFactor;
		numOfElem = 0;
	}

	@Override
	public abstract boolean add(String newValue);

	@Override
	public abstract boolean contains(String searchVal);

	@Override
	public abstract boolean delete(String toDelete);

	/* (non-Javadoc)
	 * @see SimpleSet#size()
	 */
	@Override
	public int size() {
		return numOfElem;
	}
	
	/** 
	 * @return capacity of the hash table.
	 */
	public int capacity(){
		return capacity;
	}
}