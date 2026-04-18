import java.util.Collection;
import java.util.function.BiPredicate;

public class RuleValidator {

    /**
     * Validates the logical rule provided in the class activity.
     * * Logic: For all x, if there exists a y such that x is Type A, 
     * y is Type S, and P(x,y) is true, then for all z where P(y,z) 
     * is true, z must also be Type A.
     */
    public static boolean checkRule(
            Collection<Object> Ua, 
            Class<?> A, 
            Class<?> S, 
            BiPredicate<Object, Object> P
    ) {
        for (Object x : Ua) {
            // The implication only matters if x is of type A.
            // If x is not A, the antecedent (left side) is false, 
            // making the whole rule true for this x.
            if (!A.isInstance(x)) {
                continue;
            }

            // We look for every possible y that could satisfy the antecedent
            for (Object y : Ua) {
                if (S.isInstance(y) && P.test(x, y)) {
                    
                    // If such a y exists, we must verify the "consequent" (right side):
                    // Every z related to y must be of type A.
                    for (Object z : Ua) {
                        if (P.test(y, z) && !A.isInstance(z)) {
                            // Counterexample found: P(y,z) is true, but z is NOT A.
                            return false;
                        }
                    }
                }
            }
        }
        // No counterexamples found across the entire collection.
        return true;
    }

    public static void main(String[] args) {
        // This is where you would initialize your Ua collection 
        // and call checkRule(Ua, A.class, S.class, (x, y) -> ...);
        System.out.println("Rule Validator Ready.");
    }
}