import MVC.model.PieceFactory;
import MVC.model.Pieces.*;

import MVC.model.Player;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class PieceFactoryTest {


@Test
public void createBlackPawn(){
    PieceFactory.isPlayerOne = false;
    PieceFactory.createPawn(1,1);
    assertEquals("Pawn", PieceFactory.createPawn(1,1).getType() );
}
}
