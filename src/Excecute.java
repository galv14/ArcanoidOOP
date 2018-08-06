import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 *
 */
public class Excecute extends BaseExpression {
    /**
     *
     * @param args dfh
     * @throws Exception fgd
     */
    public static void main(String[] args) throws Exception {
        Expression e2 = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));
        String s = e2.toString();
        System.out.println(s);
        List<String> vars = e2.getVariables();
        for (String v : vars) {
            System.out.println(v);
        }
        Expression e3 = e2.assign("y", e2);
        System.out.println(e3);
        Expression e4 = e3.assign("y", e3);
        System.out.println(e4);
        // (x + ((x + y)^2))^2
        e3 = e3.assign("x", new Num(1));
        System.out.println(e3);
        // (1 + ((1 + y)^2))^2
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 4.0);
        double value = e2.evaluate(assignment);
        System.out.println("The result is: " + value); //36
        System.out.println(new Minus(new Num(1), new Num(2)).evaluate()); //-1
       // Expression e5 = new Pow(new Plus(new Var("x"), new Var("x")), new Var("y"));
        Expression e6 = new Log(new Pow(4, 2), "e");
        //Expression x = new Pow("x", "hl");
        //Expression x2 = new Pow("x", "hlj");
        e6 = e6.simplify();
        //Expression e7 = new Plus(e5, e6);
        System.out.println(e4.getVariables()); // [x,y]
        try {
            System.out.println(e2.evaluate());
        } catch (Exception e) {
            System.out.println("error");
        }
        System.out.println(e6.toString());
        //System.out.println(e6.evaluate());
    }
}