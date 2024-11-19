package it.unibo.mvc;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private final List<String> history = new LinkedList<>();
    private String current;

    @Override
    public void setString(final String string) {
        this.current = Objects.requireNonNull(string);
    }

    @Override
    public String getString() {
        return this.current;
    }

    @Override
    public List<String> getHistory() {
        return Collections.unmodifiableList(history);
    }

    @Override
    public void printString() {
        if (this.current == null) {
            throw new IllegalStateException("No string set yet");
        }
        history.add(this.current); 
        System.out.println(this.current); // NOPMD: allowed in exercise
    }
}
