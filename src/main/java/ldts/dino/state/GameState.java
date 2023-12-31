package ldts.dino.state;

import ldts.dino.controller.Controller;
import ldts.dino.controller.game.GameController;
import ldts.dino.model.game.Game;
import ldts.dino.viewer.Viewer;
import ldts.dino.viewer.game.GameViewer;

public class GameState extends State<Game> {

    public GameState(Game model) {
        super(model);
    }

    @Override
    protected Controller<Game> getController() {
        return new GameController(getModel());
    }

    @Override
    protected Viewer<Game> getViewer() {
        return new GameViewer(getModel());
    }
}