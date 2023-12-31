package ldts.dino.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import ldts.dino.utils.Position;

public interface GUI {
    ACTION getNextAction() throws IOException;
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;

    void paintBackground(String hashtag);
    void drawArea(Position position, int width, int height, String color);

    enum ACTION {UP, DOWN, NONE, QUIT, SELECT, BACK, JUMP, BOMB}

    void drawImage(Position pos, BufferedImage image);

    void drawImageFromFile(Position pos, String filename);

    void drawPixel(Position pos, String color);

    void drawText(Position position, String text, int size, String color);
}