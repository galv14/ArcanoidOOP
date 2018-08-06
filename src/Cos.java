/**
 *
 * @author gal.
 *
 */
public class Cos extends UnaryExpression implements Expression {
    /**
     *
     * @param x an Expression
     */
    public Cos(Expression x) {
        setExpres(x);
        this.setSign(" cos ");
    }
    /**
    *
    * @param x a double
    */
    public Cos(double x) {
        Num x1 = new Num(x);
        setExpres(x1);
        this.setSign(" cos ");
    }
    /**
    *
    * @param x a String
    */
    public Cos(String x) {
        Var x1 = new Var(x);
        setExpres(x1);
        this.setSign(" cos ");
    }
    /**
    *
    * @param var a String
    * @return the expression tree resulting from differentiating the current expression relative to variable `var`.
    */
   public Expression differentiate(String var) {
       Expression e1 = this.getExpres().differentiate(var);
       Expression e2 = new Neg(new Sin(this.getExpres()));
       Expression e3 = new Mult(e1, e2);
       return e3;
   }

}
