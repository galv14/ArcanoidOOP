import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author gal.
 *
 */
public abstract class BaseExpression {
    /**
    *
    * @param e1 an Expression
    * @return true or false if we can evaluate the expression
    */
   public boolean canEval(Expression e1) {
       try {
           e1.evaluate();
           return true;
       } catch (Exception e) {
           return false;
       }
   }
   /**
    *
    * @param e1 an Expression
    * @param e2 an Expression
    * @return if the two Expression's are equal or not
    * @throws Exception never
    */
   public static boolean equal(Expression e1, Expression e2) throws Exception {
       double x, y, count = 2;
       Map<String, Double> assignment = new TreeMap<String, Double>();
       List<String> vars1 = e1.getVariables();
       List<String> vars2 = e2.getVariables();
       for (String v : vars1) {
           assignment.put(v, count);
           count++;
       }
       for (String v : vars2) {
           assignment.put(v, count);
           count++;
       }
       x = e1.evaluate(assignment);
       y = e2.evaluate(assignment);
       if (x == y) {
           return true;
       }
       return false;
   }
}
