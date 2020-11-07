package sample.io.vote;

public class VoteResult {
    private String party;
    private Long voice;

    public VoteResult() {
    }

    public VoteResult(String party, Long voice) {
        this.party = party;
        this.voice = voice;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public Long getVoice() {
        return voice;
    }

    public void setVoice(Long voice) {
        this.voice = voice;
    }

    @Override
    public String toString() {
        return ""+ party +":  " + voice ;
    }
}
