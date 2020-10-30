package sample.domain.user;

public class Party {
    private String id;
    private String name;
    private String description;
    private byte[] flag;

    public Party() {
    }

    public Party(String id, String name, String description,byte[] flag) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public byte[] getFlag() {
        return flag;
    }

    public void setFlag(byte[] flag) {
        this.flag = flag;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
