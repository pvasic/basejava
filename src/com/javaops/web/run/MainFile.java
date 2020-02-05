package com.javaops.web.run;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * @author Vasichkin Pavel
 */
public class MainFile {
    public static void main(String[] args) throws IOException {

        // The used interface FileVisitor for walkFileTree
        //Path directory = Paths.get("./");
        //Files.walkFileTree(directory, new PrintFiles());

        //HW09
        File file = new File("./src");
        hw09PrintFiles(file);

        printPath();
    }

    private static void hw09PrintFiles(File file) throws IOException {
        String indent = "";
        indentedPrintingFiles(file, indent);
    }

    private static void indentedPrintingFiles(File file, String indent) throws IOException {
        String indentPlus = indent + "\t";
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    System.out.printf("%1$s Directory: %2$s\n", indent, f.getName());
                    indentedPrintingFiles(f, indentPlus);
                } else {
                    System.out.printf("%1$s File: %2$s \n",indent, f.getName());
                }
            }
        }
    }

    public static void printPath() {
        Path directory = Paths.get("/home/pavel/basejava/storage");
        System.out.println(directory.toString());
        directory = Paths.get("/home/pavel/basejava/storage" + "/" + "uuid4");
        System.out.println(directory.toString());
        System.out.println(directory.isAbsolute());
        System.out.println(directory.toAbsolutePath().getFileName());
        System.out.println(Files.exists(directory));
    }

    public static void examplePrint() {
        String filePath = ".//.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/com/javaops/web");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class PrintFiles extends SimpleFileVisitor<Path> {

        // Print information about
        // each type of file.
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
            if (attr.isSymbolicLink()) {
                System.out.format("Symbolic link: %s ", file);
            } else if (attr.isRegularFile()) {
                System.out.format("Regular file: %s ", file);
            } else {
                System.out.format("Other: %s ", file);
            }
            System.out.println("(" + attr.size() + "bytes)");
            return CONTINUE;
        }

        // Print each directory visited.
        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
            System.out.format("Directory: %s%n", dir);
            return CONTINUE;
        }

        // If there is some error accessing
        // the file, let the user know.
        // If you don't override this method
        // and an error occurs, an IOException
        // is thrown.
        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.err.println(exc);
            return CONTINUE;
        }
    }
}
