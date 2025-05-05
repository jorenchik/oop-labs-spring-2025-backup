package jtm.activity09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class StreamEditor {

    public static void main(String[] args) throws Exception {
        String inFileName, outFileName; // Names of input and output files
        int inLineNo = 0;            // Line number to change or delete
        String content;              // Text content to add/replace
        PrintWriter writer = null;   // Character writer
        BufferedReader reader = null; // Character reader
        File inFile;                 // Input file reference
        int curLineNo = 0;           // Counter for lines read
        String curLineContent;       // Content of current line
        boolean delete = false;      // Flag for deletion

        // Check number of passed parameters
        if (args == null || args.length != 4) {
            System.err.println("Please use arguments: [-]lineNo (TextToAdd/Replace|-) (inputFile|-) (outputFile|-)");
            System.exit(1);
        }

        // Parse line number and determine deletion
        try {
            inLineNo = Integer.parseInt(args[0]);
            delete = inLineNo < 0;
            inLineNo = Math.abs(inLineNo);
        } catch (NumberFormatException e) {
            System.err.println("Invalid line number: " + args[0]);
            System.exit(1);
        }

        // Get content to add/replace
        content = args[1];

        // Initialize reader - read from STDIN or from file.
        if ("-".equals(args[2])) {
            reader = new BufferedReader(new InputStreamReader(System.in));
        } else {
            inFileName = args[2];
            inFile = new File(inFileName);
            if (!inFile.exists()) {
                inFile.createNewFile();
            }
            reader = new BufferedReader(new FileReader(inFile));
        }

        // Initialize writer - write to STDOUT or to file.
        if ("-".equals(args[3])) {
            writer = new PrintWriter(System.out);
        } else {
            outFileName = args[3];
            writer = new PrintWriter(outFileName);
        }

        boolean operationDone = false;


        // Read and process each line
        while ((curLineContent = reader.readLine()) != null) {
            curLineNo++;

            if (curLineNo == inLineNo) {
                if (!delete) {
                    // Add or replace line
                    if (content != null && !content.isEmpty()) {
                        writer.println(content);
                    } else {
                        writer.println();
                    }
                }
                operationDone = true;
            } else {
                writer.println(curLineContent);
            }
        }

        // If specified line is beyond end, pad with empty lines
        if (!operationDone && inLineNo != 0 && !delete) {
			for (int i = curLineNo + 1; i < inLineNo; i++) {
				writer.println();
			}
			if (content != null && !content.isEmpty()) {
				writer.println(content);
			} else {
				writer.println();
			}
        }

        // Flush and close resources
        writer.flush();
        reader.close();
        writer.close();
    }
}
