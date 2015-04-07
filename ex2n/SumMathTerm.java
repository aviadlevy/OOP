public class SumMathTerm extends MathTerm {
    private MathTerm lowerTerm;
    private MathTerm upperTerm;
    private MathTerm sumTerm;
    private String termName;
    
	public SumMathTerm(MathTerm subTerm,MathTerm superTerm,MathTerm sumTerm) {
		this.lowerTerm = subTerm;
		this.upperTerm = superTerm;
		this.sumTerm = sumTerm;		
	}
	
	public java.lang.String toLatex(){
		this.termName = "\\sum_{ " + this.lowerTerm.toLatex() + " }^{ " +
	          this.upperTerm.toLatex() + " }{ " + this.sumTerm.toLatex() + " }";

		this.termName = this.printExponentBarredNageted(this.termName);
		
		return this.termName;
		
	}
}
