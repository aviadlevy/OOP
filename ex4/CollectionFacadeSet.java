import java.util.Collection;

public class CollectionFacadeSet implements SimpleSet {

	public Collection<String> mySet;
	
	public CollectionFacadeSet(Collection<String> collection) {
		mySet = collection;
	}

	@Override
	public boolean add(String newValue) {
		return mySet.add(newValue);
	}

	@Override
	public boolean contains(String searchVal) {
		return mySet.contains(searchVal);
	}

	@Override
	public boolean delete(String toDelete) {
		return mySet.remove(toDelete);
	}

	@Override
	public int size() {
		return mySet.size();
	}

}
