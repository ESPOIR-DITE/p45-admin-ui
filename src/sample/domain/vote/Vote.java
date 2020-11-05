package sample.domain.vote;

import java.util.Date;

public class Vote {
    private String id;
    private String candidateId;
    private String date;
    private String locationId;

    public Vote() {
    }

    public Vote(String id, String candidateId, String date, String locationId) {
        this.id = id;
        this.candidateId = candidateId;
        this.date = date;
        this.locationId = locationId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id='" + id + '\'' +
                ", candidateId='" + candidateId + '\'' +
                ", date=" + date +
                ", locationId='" + locationId + '\'' +
                '}';
    }
}
