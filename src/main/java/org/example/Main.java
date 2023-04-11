package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
//        String url = "https://basket-20.wb.ru/vol861/part86108/86108048/info/sellers.json";
        ArrayList<String> urls = getListOfURLs();


    }

    public static boolean isRealURL(String url) {

        try {
            // Создаем объект URL для запроса
            URL obj = new URL(url);
            // Открываем соединение и получаем код ответа
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            int responseCode = con.getResponseCode();
            // Если ответ 200, то записываем URL в файл
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return true;
            }
        } catch (UnknownHostException e) {
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return  false;
    }

    public static void generateURLS(String filename) throws IOException {

        FileWriter writer = new FileWriter(filename, true);

        for (int basketID = 1; basketID <= 11; basketID++) {
            for (int volID = 1; volID < 999; volID++) {
                for (int partID = 1; partID < 99; partID++) {
                    for (int subpartID = 1; subpartID < 999; subpartID++) {
                        if ( isRealURL(String.format("https://basket-%02d.wb.ru/vol%03d/part%03d%02d/%03d%02d%03d/info/sellers.json",
                                basketID, volID, volID, partID, volID, partID, subpartID))) {
                            writer.write(url + "\n");
                        }
                    }

                }
            }
        }

        writer.close();
    }
}