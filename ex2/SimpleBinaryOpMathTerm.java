public class SimpleBinaryOpMathTerm extends BinaryMathTerm {
	private char sign;
	private String termName;
	
	public SimpleBinaryOpMathTerm(MathTerm firstTerm, MathTerm secondTerm,
            char sign){
		super(firstTerm, secondTerm);
		this.sign = sign;
	}
	
	public java.lang.String toLatex(){
		if (this.sign == '*') {
			this.termName = this.firstTerm.toLatex() + " \\cdot " +
		           this.secondTerm.toLatex();
		} else {
		    this.termName = this.firstTerm.toLatex() + "" + this.sign + "" + 
	               this.secondTerm.toLatex();
		}
		
		this.termName = this.printExponentBarredNageted(this.termName);
		
		return this.termName;
	}
}
