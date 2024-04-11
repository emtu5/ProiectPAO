package Model;

import java.util.ArrayList;
import java.util.List;

public class Semifinal extends LiveShow{
    private int qualifiers;
    public Semifinal(String showName, int qualifiers) {
        super(showName);
        this.qualifiers = qualifiers;
    }

    @Override
    public void results() {

    }
}
