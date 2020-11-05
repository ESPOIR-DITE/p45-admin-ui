package sample.io.user;

import sample.domain.user.Party;

import java.io.Serializable;
import java.util.List;

public class PartyList implements Serializable {
    private List<Party> parties;

    public PartyList(List<Party> parties) {
        this.parties = parties;
    }

    public PartyList() {
    }

    public List<Party> getParties() {
        return parties;
    }

    public void setParties(List<Party> parties) {
        this.parties = parties;
    }

    @Override
    public String toString() {
        return "PartyList{" +
                "parties=" + parties +
                '}';
    }
}
