package files;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;


public class CopyFiles {
    public void copy(String from, String to)
    {
        File firstDir = new File(from);
        File secondDir = new File(to);
        try {
            FileUtils.copyDirectory(firstDir, secondDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
