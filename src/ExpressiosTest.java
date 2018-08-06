import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author gal.
 *
 */
public class ExpressiosTest {
    /**
     *
     * @param args user
     * @throws Exception exceptions
     */
    public static void main(String[] args) throws Exception {
        Expression e1 = new Mult("x", 2);
        Expression e2 = new Sin(new Mult(4, "y"));
        Expression e3 = new Pow("e", "x");
        Expression full = new Plus(new Plus(e1, e2), e3);
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", 2.71);
        try {
            System.out.println(full.toString());
            System.out.println(full.evaluate(assignment));
            System.out.println(full.differentiate("x"));
            System.out.println(full.differentiate("x").evaluate(assignment));
            System.out.println(full.differentiate("x").simplify());
        } catch (Exception e) {
            System.out.println("error1");
        }
    }
}
