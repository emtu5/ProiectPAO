import Model.*;
import Repository.DbConstants;
import Runner.ContestRunner;
import Service.*;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ContestRunner contestRunner = new ContestRunner();
        contestRunner.run();
    }
}
