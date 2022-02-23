package files;

import files.CopyFiles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner vvod = new Scanner(System.in);
        String first = vvod.nextLine();
        String second = vvod.nextLine();
        CopyFiles copyFiles = new CopyFiles();
        copyFiles.copy(first, second);

    }


}
