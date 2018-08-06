/**
 *
 * @author gal.
 *
 */
public class Sin extends UnaryExpression implements Expression {
    /**
    *
    * @param x an Expression
    */
    public Sin(Expression x) {
        setExpres(x);
        this.setSign(" sin ");
    }
    /**
    *
    * @param x a double
    */
    public Sin(double x) {
        Num x1 = new Num(x);
        setExpres(x1);
        this.setSign(" sin ");
    }
    /**
    *
    * @param x a String
    */
    public Sin(String x) {
        Var x1 = new Var(x);
        setExpres(x1);
        this.setSign(" sin ");
    }
    /**
    *
    * @param var a String
    * @return the expression tree resulting from differentiating the current expression relative to variable `var`.
    */
   public Expression differentiate(String var) {
       Expression e1 = this.getExpres().differentiate(var);
       Expression e2 = new Cos(this.getExpres());
       Expression e3 = new Mult(e1, e2);
       return e3;
   }
}
