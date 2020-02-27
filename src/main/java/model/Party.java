package model;

//@Getter
//@Setter
public class Party {
    private String type;
    private Integer id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Party{" +
                "type='" + type + '\'' +
                ", id=" + id +
                '}';
    }
}
