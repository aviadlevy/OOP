public class SimpleMathTerm extends MathTerm {
	private String termName;
	private int precisionDigits;
	private boolean isPrecisionDigits = false;
	
	public SimpleMathTerm(java.lang.String termName){
		this.termName = termName;
	}
	
	public boolean isNumeric(){
		for (int i=0; i < this.termName.length(); i++) {
	        char c = this.termName.charAt(i);
			//check if negative ('-' is first) or if number is decimal fractions
	        if ((i==0 && c=='-') || c=='.')
				continue;
			if (!Character.isDigit(c))
	        	return false;
	    }
		return true;
	}
	
	public void setPrecisionDigits(int precisionDigits){
		this.precisionDigits = precisionDigits;
		this.isPrecisionDigits = true;
	}
	
	private String cutPrecisionDigits(int precisionDigits){
		String newTerm = "";
		int countLength = 0;
		
		//copy the string to a new string char by char until/if we meet the '.'
		for (char c : this.termName.toCharArray()){
			if (c == '.'){
				/* when we met '.', we check if we need to trim every thing
                   including the '.' or not. if not we'll add the '.' now */
				if (precisionDigits != 0) {
					newTerm = newTerm + c;
				    countLength++;
				}
				break;
			}
			newTerm = newTerm + c;
			countLength++;
		}
		//les't check if the precision is bigger then my value length.
		if (countLength < this.termName.length()){
			
			/* in this for-loop we check if passing the precision length
			 * and the value length. again we coping char by char. */
			for (int i=0; i < precisionDigits &&
					countLength < this.termName.length(); i++,countLength++){
				
				newTerm = newTerm + this.termName.charAt(countLength);
				
			}
		}
		return newTerm;
	}
	
	public java.lang.String toLatex(){
		if (this.isNumeric())
			if (this.isPrecisionDigits) 
			     this.termName = this.cutPrecisionDigits(this.precisionDigits);
		
		this.termName = this.printExponentBarredNageted(this.termName);
		return this.termName;
	}
}
