import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicLong;

public class ProjectFoundation {

    private static class PrintFiles extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
            if (attr.isDirectory()) {
                try {
                    System.out.format("Directory: %s, size: %d bytes\n", file, getDirSize(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if (attr.isRegularFile()) {
                System.out.format("Regular file: %s, size %d bytes\n", file, attr.size());
            }
            return FileVisitResult.CONTINUE;
        }


        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.err.println(exc);
            return FileVisitResult.CONTINUE;
        }

        /**
         * Walks through directory path and sums up all files' sizes.
         *
         * @param dirPath Path to directory.
         * @return Total size of all files included in dirPath.
         * @throws IOException when something is wrong file system
         */
        private long getDirSize(Path dirPath) throws IOException {
            final AtomicLong size = new AtomicLong(0L);

            Files.walkFileTree(dirPath, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    size.addAndGet(attrs.size());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    return FileVisitResult.CONTINUE;
                }
            });

            return size.get();
        }
    }

    /**
     * Main method.
     *
     * @param args
     */
    public static void main(String [] args) {
        Path p = Paths.get("");
        try {
            Files.walkFileTree(p, EnumSet.noneOf(FileVisitOption.class), 1, new PrintFiles());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}