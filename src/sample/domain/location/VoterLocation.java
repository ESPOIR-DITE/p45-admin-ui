package sample.domain.location;

public class VoterLocation {
    private String id;
    private String voterId;
    private String locationId;

    public VoterLocation() {
    }

    public VoterLocation(String id, String voterId, String locationId) {
        this.id = id;
        this.voterId = voterId;
        this.locationId = locationId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}
