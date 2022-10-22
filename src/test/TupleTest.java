import MVC.model.Tuple;
import org.junit.Test;
import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

public class TupleTest {

@Test
public void testFirst(){
    Tuple test = new Tuple(1, null);
    assertEquals(1, test.getFirst());
}

@Test
    public void testSecond(){
    Tuple test = new Tuple(null, 2);
    assertEquals(2, test.getSecond());
}

@Test
    public void testTuple(){
    Tuple test = new Tuple(5,10);
    assertEquals(5, test.getFirst());
    assertEquals(10, test.getSecond());
}
}
