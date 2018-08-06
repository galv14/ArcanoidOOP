/**
 *
 * @author gal.
 *
 */
public class Pow extends BinaryExpression implements Expression {
    /**
     *
     * @param x a double
     * @param y a double
     */
    public Pow(Expression x, Expression y) {
        this.setFirst(x);
        this.setSecond(y);
        this.setSign("^");
    }
    /**
    *
    * @param x a double
    * @param y a double
    */
    public Pow(double x, double y) {
        Num x1 = new Num(x);
        this.setFirst(x1);
        Num y1 = new Num(y);
        this.setSecond(y1);
        this.setSign("^");
    }
    /**
    *
    * @param x a String
    * @param y a String
    */
    public Pow(String x, String y) {
        Var x1 = new Var(x);
        this.setFirst(x1);
        Var y1 = new Var(y);
        this.setSecond(y1);
        this.setSign("^");
    }
    /**
    *
    * @param x a double
    * @param y a String
    */
    public Pow(double x, String y) {
        Num x1 = new Num(x);
        this.setFirst(x1);
        Var y1 = new Var(y);
        this.setSecond(y1);
        this.setSign("^");
    }
    /**
    *
    * @param x a String
    * @param y a double
    */
    public Pow(String x, double y) {
        Var x1 = new Var(x);
        this.setFirst(x1);
        Num y1 = new Num(y);
        this.setSecond(y1);
        this.setSign("^");
    }
    /**
    *
    * @param x a String
    * @param y a Expression
    */
    public Pow(String x, Expression y) {
        Var x1 = new Var(x);
        this.setFirst(x1);
        this.setSecond(y);
        this.setSign("^");
    }
    /**
    *
    * @param x a Expression
    * @param y a String
    */
    public Pow(Expression x, String y) {
        this.setFirst(x);
        Var y1 = new Var(y);
        this.setSecond(y1);
        this.setSign("^");
    }
    /**
    *
    * @param x a Expression
    * @param y a double
    */
    public Pow(Expression x, double y) {
        this.setFirst(x);
        Num y1 = new Num(y);
        this.setSecond(y1);
        this.setSign("^");
    }
    /**
    *
    * @param x a double
    * @param y a Expression
    */
    public Pow(double x, Expression y) {
        Num x1 = new Num(x);
        this.setFirst(x1);
        this.setSecond(y);
        this.setSign("^");
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
      Expression e3 = new Pow(e1, e2);
      return e3;
  }
  /**
  *
  * @param var a String
  * @return the expression tree resulting from differentiating the current expression relative to variable `var`.
  */
  public Expression differentiate(String var) {
      Expression e1 = new Pow(this.getFirst(), this.getSecond());
      Expression e2 = new Mult(this.getSecond(), new Div(this.getFirst().differentiate(var), this.getFirst()));
      Expression e3 = new Mult(this.getSecond().differentiate(var), new Log("e", this.getFirst()));
      return new Mult(e1, new Plus(e2, e3));
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
      return new Pow(f, s);
  }
}
