import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;

class getJSON {

    public static String stockName = "TSLA";
    private static  String timeSeries = "TIME_SERIES_DAILY";
    static JSONObject json;
    int daysRange = 50;

    static {
        try {
            json = new JSONObject(IOUtils.toString(new URL("https://www.alphavantage.co/query?function="+timeSeries+"&symbol="+stockName+"&apikey=SM8N99YHT47GOT39"), Charset.forName("UTF-8")));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws JSONException {
    }
    getJSON() throws IOException, JSONException {
    }

    public double[] getPriceArray() throws JSONException {
        double[] price = new double[daysRange];
        int days = 0;
        for (int i = 0; i < daysRange; i++) {
            LocalDate myObj = LocalDate.now().minusDays(5+days);       //Today date minus x days
            JSONObject jsonDatePairs = (json.getJSONObject("Time Series (Daily)"));
            if(jsonDatePairs.has(String.valueOf(myObj))){
                // It exists, do your stuff
                JSONObject jsonPairs = (jsonDatePairs.getJSONObject(String.valueOf(myObj)));
                price[i] = jsonPairs.getDouble("4. close");
                days++;
            } else {
                // It doesn't exist, do nothing
                i=i-1;
                days++;
                continue;
            }
        }
    return price.clone();
    }

    public String[] getDateArray() throws JSONException {
        String[] date = new String[daysRange];
        int days = 0;
        for (int i = 0; i < daysRange; i++) {
            LocalDate myObj = LocalDate.now().minusDays(5+days);
            JSONObject jsonDatePairs = (json.getJSONObject("Time Series (Daily)"));
            if(jsonDatePairs.has(String.valueOf(myObj))) {
                date[i] = String.valueOf(myObj);
                days++;
            } else {
                i=i-1;
                days++;
                continue;
            }
        }
        return date.clone();
    }
}

