import MVC.model.PieceLayoutFactory;
import MVC.model.Pieces.Piece;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PieceLayoutFactoryTest {

    @Test
    public void testReverse(){
        String[][] original = {{"1", "2", "3"},{"4", "5", "6"}};
        String[][] reversed = {{"4", "5", "6"},{"1", "2", "3"}};
        String[][] test = PieceLayoutFactory.reverseLayout(original);

            for (int i = 0; i < reversed.length-1; i++){
                for (int j = 0; j < reversed[i].length-1; j++){
                    assertEquals(reversed[i][j], test[i][j]);
                }
            }



    }
}
