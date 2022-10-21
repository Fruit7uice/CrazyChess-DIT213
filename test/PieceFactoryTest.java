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
    Piece pawn = PieceFactory.createPawn(1,1);;
    assertFalse(pawn.isPlayerOne());
    assertEquals("Pawn", pawn.getType() );
}
@Test
public void createWhitePawn(){
    PieceFactory.isPlayerOne = true;
    Piece pawn = PieceFactory.createPawn(1,1);
    assertTrue(pawn.isPlayerOne());
    assertEquals("Pawn", PieceFactory.createPawn(1,1).getType() );
}

@Test
public void createBlackRook(){
    PieceFactory.isPlayerOne = false;
    Piece rook = PieceFactory.createRook(1,1);
    assertFalse(rook.isPlayerOne());
    assertEquals("Rook", rook.getType());
}

@Test
    public void createWhiteRook(){
    PieceFactory.isPlayerOne = true;
    Piece rook = PieceFactory.createRook(1,1);
    assertTrue(rook.isPlayerOne());
    assertEquals("Rook", rook.getType());
}

@Test
    public void createBlackBishop(){
    PieceFactory.isPlayerOne = false;
    Piece bishop = PieceFactory.createBishop(1,1);
    assertFalse(bishop.isPlayerOne());
    assertEquals("Bishop", bishop.getType());
}

@Test
    public void createWhiteBishop(){
    PieceFactory.isPlayerOne = true;
    Piece bishop = PieceFactory.createBishop(1,1);
    assertTrue(bishop.isPlayerOne());
    assertEquals("Bishop", bishop.getType());
}

@Test
    public void createBlackKnight(){
    PieceFactory.isPlayerOne = false;
    Piece knight = PieceFactory.createKnight(1,1);
    assertFalse(knight.isPlayerOne());
    assertEquals("Knight", knight.getType());
}

@Test
    public void createWhiteKnight(){
    PieceFactory.isPlayerOne = true;
    Piece knight = PieceFactory.createKnight(1,1);
    assertTrue(knight.isPlayerOne());
    assertEquals("Knight", knight.getType());
}


}
