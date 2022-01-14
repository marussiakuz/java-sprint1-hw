import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileFinder extends SimpleFileVisitor<Path>  {  // поиск файла на компьютере пользователя
    public String requiredFile;
    public static Path pathOfFile;

    public FileFinder (String requiredFile){
        this.requiredFile = requiredFile;
    }

    public static Path getPathOfFile (){   // вернуть путь файла
        return pathOfFile;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (requiredFile.equals(file.getFileName().toString())) {
            pathOfFile = file.toAbsolutePath();
            return FileVisitResult.TERMINATE;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed (Path file, IOException exc) throws IOException {
        return FileVisitResult.SKIP_SUBTREE;
    }
}
