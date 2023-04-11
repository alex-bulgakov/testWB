package org.example;

import java.io.*;
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

        try {
            generateURLS("urls.txt");
        } catch (IOException e) {
        }

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
        String file_counter = "counter.txt";
//        String file_urls = "urls.txt";

        int counter = getCounter(file_counter);
        for (int basketID = 1; basketID <= 11; basketID++) {
            for (int i = counter; i < 99999999; i++) {

                setCounter(file_counter, i);

                String counter_str = String.format("%08d", i);
                String result_str = String.format("https://basket-%02d.wb.ru/vol%s/part%s/%s/info/sellers.json",
                        basketID, counter_str.substring(0, 3), counter_str.substring(0, 5), counter_str);
                System.out.println(counter_str);
                if (isRealURL(result_str)) {
                    System.out.println(result_str);
                    writeURL(result_str, String.format("urls_basket%02d.txt", basketID));
                }
            }
        }
        setCounter(file_counter, 10001001);
    }

    public static int getCounter(String file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader bf = new BufferedReader(fr);
        int counter = Integer.parseInt(bf.readLine());
        bf.close();
        fr.close();
        return counter;
    }

    public static void setCounter(String file, int counter) throws IOException {
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(String.format("%08d", counter));
        bw.close();
        fw.close();
    }

    public static void writeURL(String url, String file) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(url + "\n");
        bw.close();
        fw.close();
    }
}