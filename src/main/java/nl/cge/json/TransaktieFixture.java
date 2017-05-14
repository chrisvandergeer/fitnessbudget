package nl.cge.json;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 14-05-17.
 */
public class TransaktieFixture {

    private String path;

    public TransaktieFixture(String path) {
        this.path = path;
    }

    public List<Object> query() {
        List<Object> result = list();
        try {
            HttpResponse<JsonNode> response = Unirest.get(path)
                    .header("accept", "application/json").asJson();
            JSONArray data = response.getBody().getObject().getJSONArray("data");
            for (Object object : data) {
                JSONObject jsonObject = (JSONObject) object;
                List<Object> row = list();
                row.add(column(jsonObject, "transaktiedatum"));
                row.add(column(jsonObject, "omschrijving"));
                row.add(column(jsonObject, "rekening"));
                row.add(column(jsonObject, "tegenrekening"));
                row.add(column(jsonObject, "bedrag"));
                result.add(row);
            }
            return result;
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }

    }

    private List<Object> column(JSONObject jsonObject, String key) {
        List<Object> column = list();
        column.add(key);
        column.add(jsonObject.get(key));
        return column;
    }

    private List<Object> list() {
        return new ArrayList<Object>();
    }

}
