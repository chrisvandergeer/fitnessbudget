package nl.cge.nl.cge.fixtures.transaktie;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import nl.cge.StopTestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 14-05-17.
 */
public class TransaktieFixture {

    static enum RequestMethod { GET, POST };

    private RequestMethod requestMethod = RequestMethod.GET;
    private String path;

    /**
     * Default constructor voor Script table.
     */
    public TransaktieFixture() {
        super();
    }

    /**
     * Constructor voor Query table.
     *
     * @param path
     */
    public TransaktieFixture(String path) {
        this();
        this.path = path;
    }

    /**
     * Constructor voor Query table;
     * @param path
     * @param method
     */
    public TransaktieFixture(String path, String method) {
        this(path);
        requestMethod = RequestMethod.valueOf(method);
    }

    public List<Object> query() {
        if (RequestMethod.GET.equals(requestMethod)) {
            return createResult(doGet());
        } else if (RequestMethod.POST.equals(requestMethod)) {
            return createResult(doPost());
        }
        throw new StopTestException(String.format("Onjuist requestMethod: %s", requestMethod));
    }

    public String doDelete(String path) {
        try {
            HttpResponse<JsonNode> response = Unirest.delete(path).header("accept", "application/json").asJson();
            JSONObject jsonObject = response.getBody().getObject();
            return jsonObject.getString("message");
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpResponse<JsonNode> doPost() {
        try {
            return Unirest.post(path).header("accept", "application/json").asJson();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpResponse<JsonNode> doGet() {
        try {
            return Unirest.get(path).header("accept", "application/json").asJson();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Object> createResult(HttpResponse<JsonNode> response) {
        List<Object> result = list();
        JSONArray data = response.getBody().getObject().getJSONArray("data");
        for (Object object : data) {
            JSONObject jsonObject = (JSONObject) object;
            List<Object> row = list();
            row.add(column(jsonObject, "transaktiedatum"));
            row.add(column(jsonObject, "omschrijving"));
            row.add(column(jsonObject, "rekening"));
            row.add(column(jsonObject, "tegenrekening"));
            row.add(column(jsonObject, "bedrag"));
            row.add(column(jsonObject, "tags"));
            result.add(row);
        }
        return result;
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
