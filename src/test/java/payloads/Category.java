package payloads;

public class Category {

    public Category(int id, String name){
        this.id = id;
        this.name = name;
    }
    public int id;

    public String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
