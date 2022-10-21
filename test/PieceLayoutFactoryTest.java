import MVC.model.PieceFactory;
import MVC.model.PieceLayoutFactory;
import MVC.model.Pieces.*;
import MVC.model.Pieces.Piece;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PieceLayoutFactoryTest {


    @Test
    public void testClassic(){
       String[][] test = {{"Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook"},
                {"Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn"},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
               {"Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn"},
               {"Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook"}};
        Piece[][] created = PieceLayoutFactory.createClassicLayout();

        for(int i = 0; i < 2; i++){
            for(int j = 0; j < created[i].length-1 ; j++){
                assertEquals(created[i][j].getType(), test[i][j]);
                assertFalse(created[i][j].isPlayerOne());
            }
        }
        for(int i = created.length-1; i > 5; i--){
            for(int j = 0; j < created[i].length-1 ; j++){
                assertEquals(created[i][j].getType(), test[i][j]);
                assertTrue(created[i][j].isPlayerOne());
            }
        }
    }

    @Test
    public void testCrazy(){
        Piece[][] layout = PieceLayoutFactory.createCrazyLayout();
        String[] bList = new String[16];
        String[] wList = new String[16];

        int index = 0;
        for(int i = 0 ; i < 2 ; i++){
            for(int j = 0 ; j < layout[i].length;j++){
              bList[index] = layout[i][j].getType();
              index++;
              assertFalse(layout[i][j].isPlayerOne());
            }
        }

        index = 0;
        for(int i = layout.length-1 ; i > 5 ; i--){
            for(int j = 0 ; j < layout[i].length;j++){
                wList[index] = layout[i][j].getType();
                index++;
                assertTrue(layout[i][j].isPlayerOne());
            }
        }
        for(int k = 0; k < bList.length; k++)
        assertEquals(bList[k], wList[k]);
    }
}
