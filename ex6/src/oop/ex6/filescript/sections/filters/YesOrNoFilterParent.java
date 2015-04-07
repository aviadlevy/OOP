package oop.ex6.filescript.sections.filters;

public class YesOrNoFilterParent {
	
	protected boolean trueOrFalse;
	private static final String YES = "YES", NO = "NO";
	
	public YesOrNoFilterParent(String yesNo) throws WarningYesOrNoException {
		if(yesNo.equals(YES)) {
			trueOrFalse = true;
		} else if (yesNo.equals(NO)) {
			trueOrFalse = false;
		} else {
			throw new WarningYesOrNoException();
		}
	}
}
