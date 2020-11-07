package sample.io.admin;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import sample.domain.admin.Admin;
import sample.domain.user.Party;
import sample.io.Api;
import sample.io.IoInterface;
import sample.io.user.PartyIO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminIO implements IoInterface<Admin,String> {
    private static AdminIO adminIO;
    private static RestTemplate restTemplate= new RestTemplate();
    private static HttpHeaders headers = new HttpHeaders();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static JSONObject personJsonObject;
    private static String partyURL = Api.getApi() + "admin/";

    public AdminIO() {
    }

    public static AdminIO getAdminIO() {
        if (adminIO == null) {
            adminIO = new AdminIO();
        }return adminIO;
    }

    public int create(Admin admin) throws IOException {
        String createPersonUrl = partyURL + "create";
        headers.setContentType(MediaType.APPLICATION_JSON);
        personJsonObject = new JSONObject();
        ResponseEntity<Admin> reponse = restTemplate.postForEntity(createPersonUrl, admin, Admin.class);
        //System.out.println(reponse.getBody().getId());
        return reponse.getStatusCode().value();
    }

    @Override
    public int update(Admin admin) throws IOException {
        String createPersonUrl = partyURL + "update";
        headers.setContentType(MediaType.APPLICATION_JSON);
        personJsonObject = new JSONObject();
        ResponseEntity<Admin> reponse = restTemplate.postForEntity(createPersonUrl, admin, Admin.class);
        //System.out.println(reponse.getBody().getId());
        return reponse.getStatusCode().value();
    }

    @Override
    public Admin read(String id) throws IOException {
        String readURL = partyURL + "read?id="+id;
        ResponseEntity<Admin> response = restTemplate.getForEntity(readURL,Admin.class);
        // System.out.println(response.getBody().getId());
        return response.getBody();
    }

    public Admin readWithAbbreviation(String abbreviation){
        String readURL = partyURL + "readWith?abv="+abbreviation;
        ResponseEntity<Admin> response = restTemplate.getForEntity(readURL,Admin.class);
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

    @Override
    public String readAll() throws IOException {
        return null;
    }

    public Boolean deleteWithAbbreviation(String id) {
        String deleteURL = partyURL + "deleteWith?abv="+id;
        ResponseEntity<Boolean> response = restTemplate.getForEntity(deleteURL, Boolean.class);
        //System.out.println(response.getBody());
        return response.getBody();
    }
    public Boolean logIn(String email, String password){
        String login = partyURL + "ordinaryLogin?email="+email+"&password="+password;
        ResponseEntity<Boolean> response = restTemplate.getForEntity(login,Boolean.class);
        // System.out.println(response.getBody().getId());
        return response.getBody();
    }

    public Boolean superLogIn(String email, String password){
        String login = partyURL + "superAdminLogIn?email="+email+"&password="+password;
        ResponseEntity<Boolean> response = restTemplate.getForEntity(login,Boolean.class);
        // System.out.println(response.getBody().getId());
        return response.getBody();
    }

    public List<Admin> readAllX() throws IOException {
        String deleteURL = partyURL + "reads";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(deleteURL, HttpMethod.GET,entity, java.lang.String.class);
        //System.out.println(response.getBody());
        return GetThemInList(response.getBody());
    }

    public static void main(String[] args) throws ParseException, IOException {
        Admin adminObject = new Admin("espoir","dite","1234","0000");
        AdminIO admin = new AdminIO();
        //int result = admin.create(adminObject);
        System.out.println(admin.readAllX());
        //System.out.println(admin.superLogIn("espoir","1234"));
    }

    @Override
    public Long count() {
        return null;
    }
    public List<Admin> GetThemInList(String value) throws IOException {
        List<Admin> parties = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        Admin[] pp1 = mapper.readValue(value, Admin[].class);
        for (Admin admin: pp1){
            //System.out.println(party.getName());
            parties.add(admin);
        }
        return parties;
    }
}
