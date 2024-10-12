package hooks;

import java.io.IOException;
import java.nio.file.*;

public class TraceFileUtils {

    private static final String TRACE_FOLDER_PATH = "target/traceFiles"; // Directory path

    public static Path createTraceFolder() {
        Path traceFolderPath = Paths.get(TRACE_FOLDER_PATH);
        try {
            // Create directory if it doesn't exist
            if (!Files.exists(traceFolderPath)) {
                Files.createDirectories(traceFolderPath);
                System.out.println("Trace directory created at: " + traceFolderPath.toString());
            }
        } catch (IOException e) {
            System.err.println("Error creating trace folder: " + e.getMessage());
        }
        return traceFolderPath;
    }
}

