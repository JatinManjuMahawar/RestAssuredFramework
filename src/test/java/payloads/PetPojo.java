package payloads;

import java.util.List;

public class PetPojo {

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Tags[] getTags() {
        return tags;
    }
    public void setTags(Tags[] tags) {
        this.tags = tags;
    }

    Category category;
    Tags[] tags;

    public PetPojo(Category category, Tags[] tags){
            this.category = category;
            this.tags = tags;

    }

    public int id;
    public String status;
    public String name;

    public void setPhotoURL(List<String> photoURL) {
        PhotoURL = photoURL;
    }



    public List<String> PhotoURL;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}