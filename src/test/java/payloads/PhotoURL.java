package payloads;

public class PhotoURL {

    public PhotoURL(String[] strArr){
        this.photoURL = strArr;
    }
    public String[] photoURL;

    public String[] getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String[] photoURL) {
        this.photoURL = photoURL;
    }
}
