package MVC.controller;

import MVC.model.PieceFactory;
import MVC.model.Pieces.*;
import MVC.model.SpecialMoves.PromotionChecker;
import MVC.view.PromotionView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PromotionController {

    // vid varje knapp skall jag ta bort bonden och ersätta med den knappen man trycker pås
    // lägg till update board i funktionen senare
    PromotionChecker pc = new PromotionChecker();
    Piece p;

    public void chooseBishop(ActionEvent event) throws IOException {
        System.out.println("you choose bishop");

        closeWindow(event);

        //ber modellen skapa en dam och lägga in den på bondens plats och uppdattera brädet
    }

    public void chooseKnight(ActionEvent event) throws IOException {
        System.out.println("you choose Knight");
        closeWindow(event);

    }

    public void chooseQueen(ActionEvent event) throws IOException {
        System.out.println("you choose Queen");
        PromotionChecker.queenChoice();
        closeWindow(event);
    }

    public void chooseRook(ActionEvent event) throws IOException{
        System.out.println("you choose Rook");
        //PromotionHandler.rookChoice();
        closeWindow(event);

    }

    private void closeWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Promotion.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.close();
    }

}
