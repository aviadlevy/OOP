public class BinaryMathTerm extends MathTerm {
	protected MathTerm firstTerm;
	protected MathTerm secondTerm;
	
	public BinaryMathTerm(MathTerm firstTerm, MathTerm secondTerm) {
		this.firstTerm = firstTerm;
		this.secondTerm = secondTerm;
	}
	
	public java.lang.String toLatex() {
		return "";
	}
	
}
