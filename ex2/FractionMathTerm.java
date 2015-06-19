public class FractionMathTerm extends BinaryMathTerm {
	private String termName;
	
	public FractionMathTerm(MathTerm firstTerm,
            MathTerm secondTerm){
		super(firstTerm,secondTerm);
	}
	
	public java.lang.String toLatex() {
		this.termName = "\\frac{ " + this.firstTerm.toLatex() + " }{ " +
	           this.secondTerm.toLatex() + " }";
		
		this.termName = this.printExponentBarredNageted(this.termName);
		
		return this.termName;
	}
}
