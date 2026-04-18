// // class activity 
// // Write a code in java: 

// Ua = Collection<Java.Object>

// Things that are type A and type S.

// where something can be related to another thing via the relation P(x,y)

// Type A and Type S are classes.

// Determine whether or not this rule holds for set of object or collection provided by Ua.

// For all x there exists y such that P(x,y)nS(y)nA(x) -> For all z P(y,z)n A(z)


import java.util.Collection;
import java.util.function.BiPredicate;

public class LogicChecker {
 //public static void main(String[] args){
    /**
     * Ua: collection of objects
     * A:  class representing type A
     * S:  class representing type S
     * P:  relation P(x, y)
     *
     * Returns true iff the rule holds on Ua:
     * For all x, if there exists y such that
     *   P(x,y) && S(y) && A(x)
     * then for all z, if P(y,z) then A(z).
     */
    public static boolean ruleHolds(
            Collection<Object> Ua,
            Class<?> A,
            Class<?> S,
            BiPredicate<Object, Object> P
    ) {
        for (Object x : Ua) {
            // Only relevant if x is of type A
            if (!A.isInstance(x)) {
                continue;
            }

            // Find some y such that P(x,y) && S(y)
            Object witnessY = null;
            for (Object y : Ua) {
                if (S.isInstance(y) && P.test(x, y)) {
                    witnessY = y;
                    break;
                }
            }

            // If no such y exists, implication is true for this x (vacuously)
            if (witnessY == null) {
                continue;
            }

            // Now check: for all z, if P(y,z) then A(z) must hold
            for (Object z : Ua) {
                if (P.test(witnessY, z) && !A.isInstance(z)) {
                    // Found a counterexample: P(y,z) holds but z is not of type A
                    return false;
                }
            }
        }

        // No counterexamples found; rule holds for Ua
        return true;
    }
 }}
