import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ProvaSend {


    public static void send(String message) throws IOException {
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        String apiToken = "792719617:AAHvPg718FFQwNk750HICKKYMpxiTY2K4fQ";
        String chatId = "@tspnamemarco";
        String text = message;

        urlString = String.format(urlString, apiToken, chatId, text);

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        StringBuilder sb = new StringBuilder();
        InputStream is = new BufferedInputStream(conn.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String inputLine = "";
        while ((inputLine = br.readLine()) != null) {
            sb.append(inputLine);
        }
        String response = sb.toString();
// Do what you want with response
    }
}
