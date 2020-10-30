package sample.domain.location;

public class Location {
    private String id;
    private String location;
    private String address;
    private String description;
    private String parentLocationId;

    public Location() {
    }

    public Location(String id, String location, String address, String description, String parentLocationId) {
        this.id = id;
        this.location = location;
        this.address = address;
        this.description = description;
        this.parentLocationId = parentLocationId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentLocationId() {
        return parentLocationId;
    }

    public void setParentLocationId(String parentLocationId) {
        this.parentLocationId = parentLocationId;
    }
}
