package jtm.activity09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple text stream editor. Reads text from file or stdin,
 * edits or deletes the specified line, and writes to file or stdout.
 */
public class StreamEditor {

    public static void main(String[] args) throws Exception {
        // Check parameters
        if (args == null || args.length != 4) {
            System.err.println("Please use arguments: [-]lineNo (TextToAdd/Replace|-) (inputFile|-) (outputFile|-)");
            System.exit(1);
        }

        // Parse line number and deletion flag
        int rawLineNo = Integer.parseInt(args[0]);
        boolean delete = rawLineNo < 0;
        int targetLine = Math.abs(rawLineNo);

        // Text to add/replace (ignored if deleting)
        String content = args[1];

        // Input and output file names
        String inFileName = args[2];
        String outFileName = args[3];

        // Initialize reader
        BufferedReader reader;
        if ("-".equals(inFileName)) {
            reader = new BufferedReader(new InputStreamReader(System.in));
        } else {
            File inFile = new File(inFileName);
            if (!inFile.exists()) {
                inFile.createNewFile();
            }
            reader = new BufferedReader(new FileReader(inFile));
        }

        // Initialize writer
        PrintWriter writer;
        if ("-".equals(outFileName)) {
            writer = new PrintWriter(System.out);
        } else {
            writer = new PrintWriter(new File(outFileName));
        }

        // Read all lines
        List<String> allLines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            allLines.add(line);
        }

        // Pad with empty lines if necessary
        while (allLines.size() < targetLine) {
            allLines.add("");
        }

        // Modify or delete the target line
        if (delete) {
            if (targetLine >= 1 && targetLine <= allLines.size()) {
                allLines.remove(targetLine - 1);
            }
        } else {
            allLines.set(targetLine - 1, content);
        }

        // Write out lines
        for (String outLine : allLines) {
            if (outLine != null) {
                writer.write(outLine);
                // Append newline only for non-empty lines
                if (!outLine.isEmpty()) {
                    writer.println();
                }
            }
        }

        // Cleanup
        writer.flush();
        reader.close();
        writer.close();
    }
}
