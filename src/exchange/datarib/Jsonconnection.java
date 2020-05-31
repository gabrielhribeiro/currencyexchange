package exchange.datarib;

import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Iterator;

public class Jsonconnection {
    private String Url;
    private String Result;
    private static JSONObject rates;
    private static String date;
    private static String base;

    public Jsonconnection(String Url, String Result) {
        this.Url = Url;
        this.Result = Result;
    }

    public Jsonconnection(String Url) {
        this.Url = Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public void setResult(String result) {
        Result = result;
    }

    public void setRates(JSONObject rates) {
        this.rates = rates;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getUrl() {
        return Url;
    }

    public JSONObject getRates() {
        return rates;
    }

    public String getDate() {
        return date;
    }

    public String getBase() {
        return base;
    }

    static void ClientRequest(String Url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(Url)).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(Jsonconnection::ClientDeserialize)
                .join();
    }

    public static String ClientDeserialize(String Result)   {
        JSONObject json = new JSONObject(Result);
        Jsonconnection.rates = json.getJSONObject("rates");
        Jsonconnection.date = json.getString("date");
        Jsonconnection.base = json.getString("base");

        JSONArray songsArray = Jsonconnection.rates.toJSONArray(Jsonconnection.rates.names());
        //System.out.println(songsArray);

        //System.out.println(json);

        //String k = rates.keys().next();

         //System.out.println(k);

            return Result;
    }


}

