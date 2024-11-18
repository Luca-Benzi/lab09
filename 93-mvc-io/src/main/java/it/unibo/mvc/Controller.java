package it.unibo.mvc;
import java.util.List;

/**
 *
 */
public interface Controller {
    void setString(final String string);
    String getString();
    List<String> getHistory();
    void printString();
}
