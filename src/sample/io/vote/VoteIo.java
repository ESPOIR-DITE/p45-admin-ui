package sample.io.vote;

import net.minidev.json.JSONObject;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import sample.domain.vote.Vote;
import sample.io.Api;
import sample.io.IoInterface;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class VoteIo implements IoInterface<Vote, String> {
    private static String voteURL= Api.getApi()+"vote/";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public int create(Vote vote) throws IOException {
        URL url = new URL(voteURL+"create");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-type","application/json; utf-8");
        con.setRequestProperty("Accept","application/json");
        con.setDoOutput(true);

        String jsonInputString ="{\"id\": \""+vote.getId()+"\",\"candidateId\": \""+vote.getId()+"\",\"date\": \""+vote.getDate()+"\",\"locationId\": \""+vote.getLocationId()+"\"}";


        try(OutputStream os = con.getOutputStream()){
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input,0,input.length);
        }
        int code = con.getResponseCode();
        //System.out.println(code);
        return code;
    }


    @Override
    public int update(Vote vote) throws IOException {
        URL url = new URL(voteURL+"create");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-type","application/json; utf-8");
        con.setRequestProperty("Accept","application/json");
        con.setDoOutput(true);

        String jsonInputString ="{\"id\": \""+vote.getId()+"\",\"candidateId\": \""+vote.getId()+"\",\"date\": \""+vote.getDate()+"\",\"locationId\": \""+vote.getLocationId()+"\"}";

        try(OutputStream os = con.getOutputStream()){
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input,0,input.length);
        }
        int code = con.getResponseCode();
        //System.out.println(code);
        return code;
    }

    @Override
    public Vote read(String id) throws IOException {
        URL url = new URL(voteURL+"read?id="+id);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-type","application/json; utf-8");
        con.setRequestProperty("Accept","application/json");
        con.setDoOutput(true);


        System.out.println(con.getResponseMessage());
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }

    @Override
    public String readAll() {
        return null;
    }

    @Override
    public Long count() {
        return null;
    }
}
