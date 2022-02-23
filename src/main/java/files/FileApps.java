package files;

import java.io.File;
import java.text.DecimalFormat;

public class FileApps {
    public static double getFileSize(File dir)
    {
        double fileSizeSum = 0.;
        try {
            File[] paths = dir.listFiles();
            for (File f : paths)
            {
                if(f.isFile())
                {
                    fileSizeSum += f.length();
                } else
                {
                    fileSizeSum += getFileSize(f);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return fileSizeSum;
    }

    public static String getSize(double size, String dir)
    {
        DecimalFormat decimalFormat = new DecimalFormat( "#.##" );
        if ( size < 1000 * 1024 )
        {
            return "Размер папки " + dir + " Составляет: " + decimalFormat.format(size/1024) + " KB";
        }
        else if ( size < 1000 * 1048576 )
        {
            return "Размер папки " + dir + " Составляет: " + decimalFormat.format(size/1048576) + " MB";
        }
        else {
            return "Размер папки " + dir + " Составляет: " + decimalFormat.format(size/1073741824) +  " GB";
        }

    }
}
