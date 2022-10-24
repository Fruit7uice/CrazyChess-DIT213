package MVC.model;
import java.util.Objects;

/**
 * This class represents a tuple which is used by creating
 * and updating the lists used in the game logic.
 * @param <A> first object in the tuple
 * @param <B> second object in the tuple
 */
public class Tuple<A, B> {
    private A fst;
    private B snd;

    public Tuple(A fst, B snd) {
        this.fst = fst;
        this.snd = snd;
    }

    public A getFirst() {
        return fst;
    }

    public B getSecond() {
        return snd;
    }

    public String toString() {
        return "Tuple[" + fst + "," + snd + "]";
    }

    /**
     * This method is used to check whether a tuple class is equal to the tuple class
     * given as parameter
     * @param object a tuple class given as a parameter
     */
    public boolean equals(Object object) {
        return object instanceof Tuple<?, ?> && Objects.equals(fst, ((Tuple<?, ?>) object).fst)
                && Objects.equals(snd, ((Tuple<?, ?>) object).snd);
    }

    /**
     * This method returns the hash code for tuple class object.
     * Makes it possible to perform hashing related algorithms.
     */
    public int hashCode() {
        if (fst == null)
            return (snd == null) ? 0 : snd.hashCode() + 1;
        else if (snd == null)
            return fst.hashCode() + 2;
        else
            return fst.hashCode() * 17 + snd.hashCode();
    }
}
