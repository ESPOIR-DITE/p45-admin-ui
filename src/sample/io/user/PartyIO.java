package sample.io.user;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.*;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import sample.domain.user.Party;
import sample.domain.vote.Voter;
import sample.io.Api;
import sample.io.IoInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PartyIO implements IoInterface<Party,String> {
    private static RestTemplate restTemplate= new RestTemplate();
    private static HttpHeaders headers = new HttpHeaders();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static JSONObject personJsonObject;
    private static String partyURL = Api.getApi() + "party/";

    private static PartyIO partyIO;

    public PartyIO() {
    }

    public static PartyIO getPartyIO() {
        if (partyIO == null) {
            partyIO = new PartyIO();
        }
        return partyIO;
    }

    @Override
    public int create(Party party) throws IOException {
        String createPersonUrl = partyURL + "create";
        headers.setContentType(MediaType.APPLICATION_JSON);
        personJsonObject = new JSONObject();
        ResponseEntity<Party> reponse = restTemplate.postForEntity(createPersonUrl, party, Party.class);
        //System.out.println(reponse.getBody().getId());
        return reponse.getStatusCode().value();
    }

    @Override
    public int update(Party party) throws IOException {
        String createPersonUrl = partyURL + "update";
        headers.setContentType(MediaType.APPLICATION_JSON);
        personJsonObject = new JSONObject();
        ResponseEntity<Party> reponse = restTemplate.postForEntity(createPersonUrl, party, Party.class);
        //System.out.println(reponse.getBody().getId());
        return reponse.getStatusCode().value();
    }

    @Override
    public Party read(String id) throws IOException {
        String readURL = partyURL + "read?id="+id;
        ResponseEntity<Party> response = restTemplate.getForEntity(readURL,Party.class);
       // System.out.println(response.getBody().getId());
        return response.getBody();
    }
    public Party readWithAbbreviation(String abbreviation){
        String readURL = partyURL + "readWith?abv="+abbreviation;
        ResponseEntity<Party> response = restTemplate.getForEntity(readURL,Party.class);
        //System.out.println(response.getBody().getId());
        return response.getBody();
    }

    @Override
    public Boolean delete(String id) {
        String deleteURL = partyURL + "delete?id="+id;
        ResponseEntity<Boolean> response = restTemplate.getForEntity(deleteURL, Boolean.class);
        //System.out.println(response.getBody());
        return response.getBody();
    }

    public Boolean deleteWithAbbreviation(String id) {
        String deleteURL = partyURL + "deleteWith?abv="+id;
        ResponseEntity<Boolean> response = restTemplate.getForEntity(deleteURL, Boolean.class);
        //System.out.println(response.getBody());
        return response.getBody();
    }

    public List<Party> readAllX() throws IOException {
        String deleteURL = partyURL + "reads";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(deleteURL, HttpMethod.GET,entity, java.lang.String.class);
        //System.out.println(response.getBody());
        return GetThemInList(response.getBody());
    }
    @Override
    public String readAll() {
        return null;
    }

    @Override
    public Long count() {
        String deleteURL = partyURL + "count";
        ResponseEntity<Long> response = restTemplate.getForEntity(deleteURL, Long.class);
        return response.getBody();
    }

    public static void main(String[] args) throws ParseException, IOException {


        PartyIO partyIO = new PartyIO();
        String result = partyIO.readAll();
        //System.out.println(result.toString());

        //parse(result);

       }
    public List<Party> GetThemInList(String value) throws IOException {
        List<Party> parties = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        Party[] pp1 = mapper.readValue(value, Party[].class);
        for (Party party: pp1){
            //System.out.println(party.getName());
            parties.add(party);
        }
        return parties;
    }
}
