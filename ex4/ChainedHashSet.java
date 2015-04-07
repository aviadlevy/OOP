import java.util.ArrayList;
import java.util.LinkedList;

/**
 * a hash-set based on chaining. Extends SimpleHashSet.
 * 
 * @author Aviad Levy
 */
public class ChainedHashSet extends SimpleHashSet {
	
	private ArrayList<LinkedList<String>> hashTable, resizedTable;
	
	/**
	 * A default constructor. Constructs a new, empty table with default initial
	 * capacity (16), upper load factor (0.75) and lower load factor (0.25).
	 */
	public ChainedHashSet() {
		super();
		
		hashTable = new ArrayList<LinkedList<String>>();
		for (int i = 0; i < capacity; i++) {
			hashTable.add(new LinkedList<String>());
		}
	}

	/**
	 * Constructs a new, empty table with the specified load factors, and the 
	 * default initial capacity (16).
	 * 
	 * @param upperLoadFactor  The upper load factor of the hash table.
	 * @param lowerLoadFactor  The Lower load factor of the hash table.
	 */
	public ChainedHashSet(float upperLoadFactor, float lowerLoadFactor) {
		super(upperLoadFactor, lowerLoadFactor);

		hashTable = new ArrayList<LinkedList<String>>();
		for (int i = 0; i < capacity; i++) {
			hashTable.add(new LinkedList<String>());
		}
	}
	
	/**
	 * Data constructor - builds the hash set by adding the elements one by one.
	 * Duplicate values should be ignored. The new table has the default values 
	 * of initial capacity (16), upper load factor (0.75), and lower load factor
	 * (0.25).
	 * 
	 * @param data Values to add to the set.
	 */
	public ChainedHashSet(java.lang.String[] data) {
		super();

		// create a new Hash table:
		hashTable = new ArrayList<LinkedList<String>>();
		for (int i = 0; i < capacity; i++) {
			hashTable.add(new LinkedList<String>());
		}

		// add the given values to table:
		for (int i = 0; i < data.length; i++) {
			if (data[i] != null)
				this.add(data[i]);
		}
	}

	/**
	 * Add a specified element to the set.
	 * 
	 * @param newValue New value to add to the set
	 * @return False iff newValue already exists in the set
	 * @see SimpleHashSet#add(java.lang.String)
	 */
	@Override
	public boolean add(String newValue) {
		if (!contains(newValue)) {
			int cell = Math.abs(newValue.hashCode()) % capacity;
			hashTable.get(cell).add(newValue);

			numOfElem++;

			// check if table resizing is needed:
			if ((float)numOfElem / capacity > upperLoad) {
				capacity *= 2;
				resizeHashTable();
			}
			return true;
		}
		return false;
	}


	/**
	 * Look for a specified value in the set.
	 * 
	 * @param searchVal Value to search for
	 * @return True iff searchVal is found in the set
	 * @see SimpleHashSet#contains(java.lang.String)
	 */
	@Override
	public boolean contains(String searchVal) {
		int cell = Math.abs(searchVal.hashCode()) % capacity;
		return hashTable.get(cell).contains(searchVal);
	}

	/**
	 * Remove the input element from the set.
	 * 
	 * @param toDelete Value to delete
	 * @return True iff toDelete is found and deleted
	 * @see SimpleHashSet#delete(java.lang.String)
	 */
	@Override
	public boolean delete(String toDelete) {
		if (contains(toDelete)) {
			// look for object's index by its hash value and delete it:
			int cell = Math.abs(toDelete.hashCode()) % capacity;
			hashTable.get(cell).remove(toDelete);
			numOfElem--;

			// check if table resizing is needed:
			if ((float)numOfElem / capacity < lowerLoad) {
				capacity /= 2;
				resizeHashTable();
			}
			return true;
		}
		return false;
	}
	
	/**
	 * resize the hash table in order to the capacity to be in right amaount
	 */
	private void resizeHashTable(){

		// create a new Hash table:
		resizedTable = new ArrayList<LinkedList<String>>();
		for (int i = 0; i < capacity; i++) {
			resizedTable.add(new LinkedList<String>());
		}

		// import values from old table to the new one (by hashing):
		for (LinkedList<String> inList : hashTable) {
			if (inList != null){
				for (String data : inList) {
					int cell = Math.abs(data.hashCode()) % capacity;
					resizedTable.get(cell).add(data);
				}
			}
		}
		hashTable = resizedTable;
	}


}
