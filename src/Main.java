import Model.*;
import Runner.ContestRunner;
import Service.*;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ContestRunner contestRunner = new ContestRunner();
        contestRunner.run();
    }
}
