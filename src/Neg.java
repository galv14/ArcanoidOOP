/**
 *
 * @author gal.
 *
 */
public class Neg extends UnaryExpression implements Expression {
    /**
    *
    * @param x an Expression
    */
    public Neg(Expression x) {
        setExpres(x);
        this.setSign(" - ");
    }
    /**
    *
    * @param x a double
    */
    public Neg(double x) {
        Num x1 = new Num(x);
        setExpres(x1);
        this.setSign(" - ");
    }
    /**
    *
    * @param x a String
    */
    public Neg(String x) {
        Var x1 = new Var(x);
        setExpres(x1);
        this.setSign(" - ");
    }
    /**
    *
    * @param var a String
    * @return the expression tree resulting from differentiating the current expression relative to variable `var`.
    */
   public Expression differentiate(String var) {
       Expression e1 = new Neg(this.getExpres().differentiate(var));
       return e1;
   }
}
