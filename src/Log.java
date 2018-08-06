/**
 *
 * @author gal.
 *
 */
public class Log extends BinaryExpression implements Expression {
    /**
     *
     * @param x a double
     * @param y a double
     */
    public Log(Expression x, Expression y) {
        this.setFirst(x);
        this.setSecond(y);
        this.setSign(" L ");
    }
    /**
    *
    * @param x a double
    * @param y a double
    */
    public Log(double x, double y) {
        Num x1 = new Num(x);
        this.setFirst(x1);
        Num y1 = new Num(y);
        this.setSecond(y1);
        this.setSign(" L ");
    }
    /**
    *
    * @param x a String
    * @param y a String
    */
    public Log(String x, String y) {
        Var x1 = new Var(x);
        this.setFirst(x1);
        Var y1 = new Var(y);
        this.setSecond(y1);
        this.setSign(" L ");
    }
    /**
    *
    * @param x a double
    * @param y a String
    */
    public Log(double x, String y) {
        Num x1 = new Num(x);
        this.setFirst(x1);
        Var y1 = new Var(y);
        this.setSecond(y1);
        this.setSign(" L ");
    }
    /**
    *
    * @param x a String
    * @param y a double
    */
    public Log(String x, double y) {
        Var x1 = new Var(x);
        this.setFirst(x1);
        Num y1 = new Num(y);
        this.setSecond(y1);
        this.setSign(" L ");
    }
    /**
    *
    * @param x a String
    * @param y a Expression
    */
    public Log(String x, Expression y) {
        Var x1 = new Var(x);
        this.setFirst(x1);
        this.setSecond(y);
        this.setSign(" L ");
    }
    /**
    *
    * @param x a Expression
    * @param y a String
    */
    public Log(Expression x, String y) {
        this.setFirst(x);
        Var y1 = new Var(y);
        this.setSecond(y1);
        this.setSign(" L ");
    }
    /**
    *
    * @param x a Expression
    * @param y a double
    */
    public Log(Expression x, double y) {
        this.setFirst(x);
        Num y1 = new Num(y);
        this.setSecond(y1);
        this.setSign(" L ");
    }
    /**
    *
    * @param x a double
    * @param y a Expression
    */
    public Log(double x, Expression y) {
        Num x1 = new Num(x);
        this.setFirst(x1);
        this.setSecond(y);
        this.setSign(" L ");
    }
    /**
    *
    * @return a nice string representation of the expression.
    */
    @ Override
    public String toString() {
        String x = ("log(" + getFirst().toString() + ", " + getSecond().toString() + ")");
        return x;
    }
    /**
    *
    * @param var a String
    * @param expression an Expression
    * @return a new expression in which all occurrences of the variable var
    * are replaced with the provided expression
    */
   public Expression assign(String var, Expression expression) {
       Expression e1 = getFirst().assign(var, expression);
       Expression e2 = getSecond().assign(var, expression);
       Expression e3 = new Log(e1, e2);
       return e3;
   }
   /**
   *
   * @param var a String
   * @return the expression tree resulting from differentiating the current expression relative to variable `var`.
   */
   public Expression differentiate(String var) {
        Expression e3 = this;
        if (this.getFirst().toString() != "e") {
            Expression e2 = new Div(new Log("e", this.getSecond()), new Log("e", this.getFirst()));
            return e2.differentiate(var);
        }
        e3 = new Mult(new Div(1, this.getSecond()), this.getSecond().differentiate(var));
        return e3;
    }
    /**
    *
    * @return a simplified version of the current expression.
    */
    public Expression simplify() {
        if (canEval(this.getFirst()) && canEval(this.getSecond())) {
            try {
                double x = this.evaluate();
                return new Num(x);
            } catch (Exception e) {
                System.out.println("error");
            }
        }
        Expression f = this.getFirst().simplify();
        Expression s = this.getSecond().simplify();
        return new Log(f, s);
    }
}
