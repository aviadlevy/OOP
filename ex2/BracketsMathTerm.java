public class BracketsMathTerm extends MathTerm {
    private MathTerm internal;
    private String termName;
    
	public BracketsMathTerm(MathTerm internalTerm){
		this.internal = internalTerm;
	}
	
	public java.lang.String toLatex(){
		this.termName = "\\left( " + this.internal.toLatex() + " \\right)";
		this.termName = this.printExponentBarredNageted(this.termName);
		 
		return this.termName;
	}

}
