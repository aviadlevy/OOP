package oop.ex6.filescript.sections.orders;

public class OrderFactory {

	private static final String SEPEATOR = "#",	REVERSE = "REVERSE";
	
	/**
	 * @param orderString string we need to convert to Order.
	 * @return the Order the string represent.
	 * @throws Warning 
	 */
	public static Order createOrder(String orderString) throws WarningInOrderNameException {
		boolean isReverse = false;
		Order order = null;
		
		String[] array = orderString.split(SEPEATOR); //split the string with #
		//check if REVERSE in the end of the string
		if(array[array.length - 1].equals(REVERSE))
			isReverse = true;
		
		//check all cases given and send object according to what needed.
		switch(array[0]) {
			case "abs":
				order = new AbsoluteOrder();
				break;
			case "type":
				order = new TypeOrder();
				break;
			case "size":
				order =  new SizeOrder();
				break;
			case "":  //if no line entered in section after ORDER.
				order = new AbsoluteOrder();
				break;
			default:
				throw new WarningInOrderNameException();
		} 
		
		if(isReverse)
			order = new ReverseOrder(order);
		
		return order;
	}
}
