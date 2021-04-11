import com.google.gson.*;
import shaded.parquet.org.codehaus.jackson.JsonNode;
import shaded.parquet.org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        String sURL = "https://www.cbr-xml-daily.ru/daily_json.js";

        URL url;

        {
            try {
                url = new URL(sURL);
                URLConnection request = url.openConnection();
                request.connect();

                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                String valutes = root.getAsJsonObject().get("Valute").toString();


                ObjectMapper objectMapper = new ObjectMapper();
                StringReader reader = new StringReader(valutes);
                JsonNode valutes1 = objectMapper.readTree(reader);

                Iterator<JsonNode> element = valutes1.getElements();

                Map<String, Double> mapValue = new HashMap<>();

                for (Iterator<JsonNode> it = element; it.hasNext(); ) {
                    JsonNode node = it.next();
                    Valute valute = objectMapper.readValue(node, Valute.class);
                    mapValue.put(valute.getName(), Math.abs(valute.getValue() - valute.getPrevious()));
                }

                mapValue.entrySet().stream()
                        .sorted(Map.Entry.<String, Double>comparingByValue().reversed()).limit(5).forEach(System.out::println);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}


