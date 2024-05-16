package Service;

import java.io.FileWriter;
import java.time.LocalDateTime;

public class AuditLogService {
    private static final String filename = "auditlog.csv";
    public void log(String action) {
        try {
            FileWriter writer = new FileWriter(filename, true);
            String text = LocalDateTime.now() + "," + action + "\n";
            writer.append(text);
            writer.close();
        } catch (Exception e) {
            System.out.println("Error while writing to file");
        }
    }
}
