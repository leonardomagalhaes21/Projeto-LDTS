package ldts.dino.controller.game.elements;

import ldts.dino.Application;
import ldts.dino.controller.Controller;
import ldts.dino.gui.GUI;
import ldts.dino.model.game.Game;
import ldts.dino.model.game.elements.dino.CrouchDino;
import ldts.dino.model.game.elements.dino.NormalDino;
import ldts.dino.model.game.elements.Ground;
import ldts.dino.utils.Position;
import ldts.dino.utils.SoundManager;

public class DinoController extends Controller<Game> {

    public DinoController(Game model) {
        super(model);
    }
    @Override
    public void step(Application application, GUI.ACTION action, long time) {
        dinoForm();
        updatePosition();
        if(action == GUI.ACTION.JUMP || action == GUI.ACTION.UP){
            if(getModel().getDino().getClass() == CrouchDino.class) {
                getModel().setDino(new NormalDino());
            } else {
                jump(getModel().isBootsActivated());
            }
        } if(action == GUI.ACTION.DOWN) {
            SoundManager.getInstance().playCrouchSound();
            getModel().setDino(new CrouchDino(getModel().getDino().getPosition()));
            getModel().getDino().setSpeed(10);
        }
    }

    private void dinoForm() {
        if(getModel().getClock() % 5 == 0) {
            if(getModel().getDino() instanceof NormalDino){
                (getModel().getDino()).changeDinoForm();
            }
            else if(getModel().getDino() instanceof CrouchDino){
                (getModel().getDino()).changeDinoForm();
            }
        }
    }

    private void updatePosition() {
        Position position = getModel().getDino().getPosition();
        position.setY(position.getY() + getModel().getDino().getSpeed());
        getModel().getDino().setSpeed(getModel().getDino().getSpeed() + getModel().getDino().getGravity());
    }

    private void jump(boolean hasBoots) {
        if(getModel().getDino().getPosition().getY() > Ground.HEIGHT - getModel().getDino().getHeight()) {
            SoundManager.getInstance().playJumpSound();
            if (hasBoots)
                getModel().getDino().setSpeed(-13);

            else
                getModel().getDino().setSpeed(-10);
        }
    }
}