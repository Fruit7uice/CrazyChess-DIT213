package MVC.model;
import java.util.Objects;
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

    public boolean equals(Object other) {
        return other instanceof Tuple<?, ?> && Objects.equals(fst, ((Tuple<?, ?>) other).fst)
                && Objects.equals(snd, ((Tuple<?, ?>) other).snd);
    }

    public int hashCode() {
        if (fst == null)
            return (snd == null) ? 0 : snd.hashCode() + 1;
        else if (snd == null)
            return fst.hashCode() + 2;
        else
            return fst.hashCode() * 17 + snd.hashCode();
    }
}
