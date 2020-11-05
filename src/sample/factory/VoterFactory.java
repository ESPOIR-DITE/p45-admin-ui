package sample.factory;

import sample.domain.vote.Voter;

import java.io.File;

public class VoterFactory {
    public static Voter getVoter(String id, String name, String surnname, String phoneNumber, byte[] image){
        return new Voter(id,name,surnname,phoneNumber,image);
    }
}
