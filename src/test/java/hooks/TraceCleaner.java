package hooks;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class TraceCleaner {

    private static final String TRACE_FOLDER_PATH = "target/traceFiles";

    public static void clearOldTraces() {
        Path traceFolderPath = Paths.get(TRACE_FOLDER_PATH);

        if (Files.exists(traceFolderPath)) {
            try (Stream<Path> files = Files.list(traceFolderPath)) {
                files.forEach(file -> {
                    try {
                        Files.delete(file);
                        System.out.println("Deleted old trace file: " + file.toString());
                    } catch (IOException e) {
                        System.err.println("Failed to delete file: " + file.toString());
                    }
                });
            } catch (IOException e) {
                System.err.println("Error listing trace files: " + e.getMessage());
            }
        } else {
            // If folder doesn't exist, create it
            try {
                Files.createDirectories(traceFolderPath);
                System.out.println("Trace folder created: " + traceFolderPath);
            } catch (IOException e) {
                System.err.println("Failed to create trace folder: " + e.getMessage());
            }
        }
    }
}
