package MVC.snap;

import MVC.model.Piece;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;

public class EventHandler {
    public static void pressed(MouseEvent event, Piece p) {
        p.setColor(Color.BLUE);
    }

    public static void dragDetected(MouseEvent event, Piece p) {
        Dragboard db = p.StartDragAndDrop(TransferMode.ANY);

        ClipboardContent content = new ClipboardContent();
        content.putString("");
        db.setContent(null);

        event.consume();
    }

    public static void released(MouseEvent event, Piece p) {
    }

    public static void dragComplete(DragEvent event, Piece p) {
    }

    public static void dragOver(DragEvent event, Rectangle r) {
        if(event.getGestureSource() != r && event.getDragboard().hasString()){
            event.acceptTransferModes(TransferMode.ANY);
        }
        event.consume();
    }

    public static void dragDropped(DragEvent event, Rectangle r) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if(db.hasString()){

        }
    }
}
