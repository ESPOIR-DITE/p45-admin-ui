package sample.io.vote;

import net.minidev.json.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.*;
import sample.domain.vote.Voter;
import sample.io.Api;
import sample.io.IoInterface;

import java.io.IOException;
import java.util.List;

import org.springframework.web.client.RestTemplate;


public class Voter_io implements IoInterface<Voter, Integer> {

    private static Voter_io voter_io;

    private static RestTemplate restTemplate= new RestTemplate();
    private static HttpHeaders headers = new HttpHeaders();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static JSONObject personJsonObject;
    private static String voteURL = Api.getApi() + "voter/";

    public Voter_io() {
    }

    public static Voter_io getVoter_io() {
        if (voter_io == null) {
            voter_io  = new Voter_io();
        }
        return voter_io;
    }

    @Override
    public int create(Voter voter) throws IOException {
        String createPersonUrl = voteURL + "create";
        headers.setContentType(MediaType.APPLICATION_JSON);
        personJsonObject = new JSONObject();
        ResponseEntity<Voter> reponse = restTemplate.postForEntity(createPersonUrl, voter, Voter.class);
        System.out.println(reponse.getBody().getId());
        return reponse.getStatusCode().value();
    }

    @Override
    public int update(Voter voter) throws IOException {
        String createPersonUrl = voteURL + "update";
        headers.setContentType(MediaType.APPLICATION_JSON);
        personJsonObject = new JSONObject();
        ResponseEntity<Voter> reponse = restTemplate.postForEntity(createPersonUrl, voter, Voter.class);
        System.out.println(reponse.getBody().getId());
        return reponse.getStatusCode().value();
    }

    @Override
    public Voter read(String id) throws IOException {
        String readURL = voteURL + "read?id="+id;
        ResponseEntity<Voter> response = restTemplate.getForEntity(readURL,Voter.class);
        System.out.println(response.getBody().getId());
        return response.getBody();
    }

    @Override
    public Boolean delete(String id) {
        String deleteURL = voteURL + "delete?id="+id;
        ResponseEntity<Boolean> response = restTemplate.getForEntity(deleteURL, Boolean.class);
        //System.out.println(response.getBody());
        return response.getBody();
    }
    @Override
    public String readAll() {
        String deleteURL = voteURL + "reads";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(deleteURL, HttpMethod.GET,entity, java.lang.String.class);
        //System.out.println(response.getBody());
        return response.getBody();
    }

    @Override
    public Long count() {
        String deleteURL = voteURL + "count";
        ResponseEntity<Long> response = restTemplate.getForEntity(deleteURL, Long.class);
        return response.getBody();
    }


}
