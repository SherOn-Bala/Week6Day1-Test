
package ca.judacribz.week6day1_test.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coffee implements Parcelable
{

    @SerializedName("last_updated_at")
    @Expose
    private String lastUpdatedAt="";
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Creator<Coffee> CREATOR = new Creator<Coffee>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Coffee createFromParcel(Parcel in) {
            return new Coffee(in);
        }

        public Coffee[] newArray(int size) {
            return (new Coffee[size]);
        }

    }
    ;

    protected Coffee(Parcel in) {
        this.lastUpdatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.desc = ((String) in.readValue((String.class.getClassLoader())));
        this.imageUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Coffee() {
    }

    /**
     * 
     * @param id
     * @param lastUpdatedAt
     * @param desc
     * @param imageUrl
     * @param name
     */
    public Coffee(String lastUpdatedAt, String desc, String imageUrl, String id, String name) {
        super();
        this.lastUpdatedAt = lastUpdatedAt;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.id = id;
        this.name = name;
    }

    public String getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(String lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lastUpdatedAt);
        dest.writeValue(desc);
        dest.writeValue(imageUrl);
        dest.writeValue(id);
        dest.writeValue(name);
    }

    public int describeContents() {
        return  0;
    }
}
