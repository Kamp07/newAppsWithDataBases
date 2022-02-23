package htmlApps;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> srcImg = new ArrayList<>();
        String[] filename = new String[1000];
        try {
            Document doc =  Jsoup.connect("https://lenta.ru/").get();
            Elements elements = doc.select("img");
            elements.forEach(element -> {
                srcImg.add(element.attr("abs:src"));
            });

            for (int i = 0; i < srcImg.size(); i++) {
                System.out.println(i + " " + srcImg.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < filename.length; i++) {
            filename[i] = "img" + i + ".jpg";
        }
        FileOutputStream fileOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            for (int i = 0; i < srcImg.size(); i++) {
                bufferedInputStream = new BufferedInputStream(new URL(srcImg.get(i)).openStream());
                fileOutputStream = new FileOutputStream("data/" + filename[i]);
                byte[] data = new byte[1024];
                int count;
                while ((count = bufferedInputStream.read(data, 0, 1024)) != -1) {
                    fileOutputStream.write(data, 0, count);
                    fileOutputStream.flush();
                }
            }

            } catch(IOException e){
                e.printStackTrace();
            } finally{
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


    }
}
