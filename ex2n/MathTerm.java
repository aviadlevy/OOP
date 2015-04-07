public class MathTerm {
    private MathTerm exponent;
    private boolean barred;
    private boolean negative;
    protected boolean isExponent;
	public MathTerm(){
	}
	
	public void setExponentTerm(MathTerm exponentTerm) {
		this.isExponent = true;
		this.exponent = exponentTerm;
	}
	
	public MathTerm getExponentTerm() {
		return this.exponent;
	}
	
	public void setIsBarred(boolean isBarred){
		this.barred = isBarred;
	}
	
	public boolean getIsBarred(){
		return this.barred;
	}
	
	public void setIsNegated(boolean isNegated){
		this.negative = isNegated;
	}
	
	public boolean getIsNegated(){
		return this.negative;
	}
	
	public java.lang.String toLatex(){
		return "";
	}
	
	/**
	 * this is a method that check and change the value according to user inputs.
	 * @param term represent the term given by any if the subclasses
	 * @return term after we added what the user asked for (Exponent, Barred,
	 *  Negated) 
	 */
	protected String printExponentBarredNageted(String term) {
		if (this.isExponent)
			term = term + "^{ " + this.getExponentTerm().toLatex() + " }";
		if (this.getIsBarred())
			term = "\\overline{ " + term + " }";
		if (this.getIsNegated())
			term = "\\neg{ " + term + " }";
		
		return term;

	}
}
