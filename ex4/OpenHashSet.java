	/** 
	 * a hash-set based on open-hashing with quadratic probing. Extends SimpleHashSet.
	 * 
	 * @author Aviad Levy
	 */
	public class OpenHashSet extends SimpleHashSet {
	    
		private OpenHashSetData[] hashTable, resizedTable;
		
		/**
		 * A default constructor. Constructs a new, empty table with default
         * initial capacity (16), upper load factor (0.75) and lower load factor (0.25).
		 */
		public OpenHashSet() {
			super();
			hashTable = new OpenHashSetData[capacity];
			for(int i=0;i<capacity;i++)
				hashTable[i] = new OpenHashSetData();
		}
	
		/**
		 * Constructs a new, empty table with the specified load factors,
         * and the default initial capacity (16).
		 * 
		 * @param upperLoadFactor The upper load factor of the hash table.
		 * @param lowerLoadFactor The lower load factor of the hash table.
		 */
		public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
			super(upperLoadFactor, lowerLoadFactor);
			hashTable = new OpenHashSetData[capacity];
			for(int i=0;i<capacity;i++)
				hashTable[i] = new OpenHashSetData();
		}
		
		/**
		 * Data constructor - builds the hash set by adding the elements one by one.
		 * Duplicate values should be ignored. The new table has the default values
		 * of initial capacity (16), upper load factor (0.75), and lower load 
		 * factor (0.25).
		 * 
		 * @param data Values to add to the set.
		 */
		public OpenHashSet(java.lang.String[] data) {
			super();
			hashTable = new OpenHashSetData[capacity];
			for(int i=0;i<capacity;i++) {
				hashTable[i] = new OpenHashSetData();
			}
			for(int i=0;i<data.length;i++){
				if(data[i] != null){
					this.add(data[i]);
				}
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
			if(!this.contains(newValue)){
				int cell = Math.abs(newValue.hashCode()) & (capacity-1);
				for(int i=1;i<=capacity;i++){
		            if(this.hashTable[cell].key == null){
						this.hashTable[cell].key = newValue;
						this.numOfElem++;
						if ((float)numOfElem / capacity > upperLoad) {
							capacity *= 2;
							resizeHashTable();
						}
						return true;
					} else {
						cell = (Math.abs(newValue.hashCode()) + (i + (i*i))/2)  & (capacity-1);				}
				}
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
			int cell = (Math.abs(searchVal.hashCode()))  & (capacity-1);
			for(int i = 1;hashTable[cell].key != null || hashTable[cell].isDeleted;i++) {
				if(hashTable[cell].key != null){
					if(hashTable[cell].key.equals(searchVal)) {
						return true;
					}
				}
				cell = (Math.abs(searchVal.hashCode()) + (i + (i*i))/2)  & (capacity-1);
			}
			return false;
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
			if(contains(toDelete)){
				int cell = Math.abs(toDelete.hashCode()) & (capacity-1);
				for(int i=1;hashTable[cell].key != null || hashTable[cell].isDeleted;i++){
					if(hashTable[cell].key != null){
						if(hashTable[cell].key.equals(toDelete)){
							hashTable[cell].key = null;
							hashTable[cell].isDeleted = true;
							numOfElem--;
							if((float)numOfElem / capacity < lowerLoad) {
								capacity /= 2;
								resizeHashTable();
							}
							return true;
						}
					} 
					cell = (Math.abs(toDelete.hashCode()) + (i + (i*i))/2)  & (capacity-1);
				}
			}
			return false;
		}
		
		/**
		 * resize the hash table in order to the capacity to be in right amaount
		 */
		private void resizeHashTable(){
	        
			// create a new Hash table:
			resizedTable = new OpenHashSetData[capacity];
			for(int i=0;i<capacity;i++)
				resizedTable[i] = new OpenHashSetData();
			
			for(int i=0;i<hashTable.length;i++){
				if(hashTable[i].key != null){
					int cell = Math.abs(hashTable[i].key.hashCode()) & (capacity-1);
					int j=1;
					while(resizedTable[cell].key != null){
						cell = (Math.abs(hashTable[i].key.hashCode())  + (j+(j*j))/2) & (capacity-1);
						j++;
					}
					resizedTable[cell].key = hashTable[i].key;
					resizedTable[cell].isDeleted = false;
				}
			}
			hashTable = resizedTable;
		}
	}