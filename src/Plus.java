/**
 *
 * @author gal.
 *
 */
public class Plus extends BinaryExpression implements Expression {
    /**
     *
     * @param x an Expression
     * @param y a Expression
     */
    public Plus(Expression x, Expression y) {
        this.setFirst(x);
        this.setSecond(y);
        this.setSign(" + ");
    }
    /**
    *
    * @param x a double
    * @param y a double
    */
    public Plus(double x, double y) {
        Num x1 = new Num(x);
        this.setFirst(x1);
        Num y1 = new Num(y);
        this.setSecond(y1);
        this.setSign(" + ");
    }
    /**
    *
    * @param x a String
    * @param y a String
    */
    public Plus(String x, String y) {
        Var x1 = new Var(x);
        this.setFirst(x1);
        Var y1 = new Var(y);
        this.setSecond(y1);
        this.setSign(" + ");
    }
    /**
    *
    * @param x a double
    * @param y a String
    */
    public Plus(double x, String y) {
        Num x1 = new Num(x);
        this.setFirst(x1);
        Var y1 = new Var(y);
        this.setSecond(y1);
        this.setSign(" + ");
    }
    /**
    *
    * @param x a String
    * @param y a double
    */
    public Plus(String x, double y) {
        Var x1 = new Var(x);
        this.setFirst(x1);
        Num y1 = new Num(y);
        this.setSecond(y1);
        this.setSign(" + ");
    }
    /**
    *
    * @param x a String
    * @param y a Expression
    */
    public Plus(String x, Expression y) {
        Var x1 = new Var(x);
        this.setFirst(x1);
        this.setSecond(y);
        this.setSign(" + ");
    }
    /**
    *
    * @param x a Expression
    * @param y a String
    */
    public Plus(Expression x, String y) {
        this.setFirst(x);
        Var y1 = new Var(y);
        this.setSecond(y1);
        this.setSign(" + ");
    }
    /**
    *
    * @param x a Expression
    * @param y a double
    */
    public Plus(Expression x, double y) {
        this.setFirst(x);
        Num y1 = new Num(y);
        this.setSecond(y1);
        this.setSign(" + ");
    }
    /**
    *
    * @param x a double
    * @param y a Expression
    */
    public Plus(double x, Expression y) {
        Num x1 = new Num(x);
        this.setFirst(x1);
        this.setSecond(y);
        this.setSign(" + ");
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
       Expression e3 = new Plus(e1, e2);
       return e3;
   }
   /**
   *
   * @param var a String
   * @return the expression tree resulting from differentiating the current expression relative to variable `var`.
   */
  public Expression differentiate(String var) {
      Expression e1 = this.getFirst().differentiate(var);
      Expression e2 = this.getSecond().differentiate(var);
      return new Plus(e1, e2);
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
      if (canEval(f)) {
          try {
              if (f.evaluate() == 0) {
                  return s;
              }
          } catch (Exception e) {
              System.out.println("error");
          }
      }
      if (canEval(s)) {
          try {
              if (s.evaluate() == 0) {
                  return f;
              }
          } catch (Exception e) {
              System.out.println("error");
          }
      }
      return new Plus(f, s);
  }
}
