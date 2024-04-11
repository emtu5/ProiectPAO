package Model;


public class Semifinal extends LiveShow{
    private int qualifiers;
    public Semifinal(String showName, int qualifiers) {
        super(showName);
        this.qualifiers = qualifiers;
    }

    @Override
    public void results() {
        //W.I.P
        System.out.println("This is a semifinal!");
    }
}
