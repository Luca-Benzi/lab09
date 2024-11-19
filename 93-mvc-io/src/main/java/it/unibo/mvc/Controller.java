package it.unibo.mvc;
import java.util.List;

/**
 *
 */
public interface Controller {
    /**
     * @param string sets the new string as the current
     */
    void setString(String string);
    /**
     * @return the current string
     */
    String getString();
    /**
     * @return the entire history of the strings printed so far
     */
    List<String> getHistory();
    /**
     * prints the current string to terminal.
     */
    void printString();
}
