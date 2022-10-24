package MVC.model;

import MVC.model.Pieces.Piece;


/**
 * Interface for the Observer-pattern that only holds the method of update.
 * The classes that implement this interface are for the ones that observe the observables.
 */

public interface Observer {
    void update(Piece[][] pieces);
}
