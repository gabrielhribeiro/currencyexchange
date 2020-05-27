package exchange.datarib;

import org.json.JSONObject;

import java.util.Iterator;

public class IsolateCurrency {
    private String currency;

    public IsolateCurrency(String currency) {
        this.currency = currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    static void OpenvalueJson(String currency){
        JSONObject jsonObject = new JSONObject();
        Iterator<String> keys = jsonObject.keys();

        while(keys.hasNext()) {
            String key = keys.next();
            if (jsonObject.get(key) instanceof JSONObject) {
                // do something with jsonObject here
            }
        }

    }
}
