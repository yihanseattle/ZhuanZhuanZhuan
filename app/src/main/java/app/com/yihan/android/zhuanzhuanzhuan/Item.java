package app.com.yihan.android.zhuanzhuanzhuan;

/**
 * Created by HanYi on 7/19/16.
 */
public class Item {

    private String placeName;
    private String lunchOrDinner;
    private int colorIndex;

    public Item(int colorIndex, String lunchOrDinner, String placeName) {
        this.colorIndex = colorIndex;
        this.lunchOrDinner = lunchOrDinner;
        this.placeName = placeName;
    }

    public int getColorIndex() {
        return colorIndex;
    }

    public void setColorIndex(int colorIndex) {
        this.colorIndex = colorIndex;
    }

    public String getLunchOrDinner() {
        return lunchOrDinner;
    }

    public void setLunchOrDinner(String lunchOrDinner) {
        this.lunchOrDinner = lunchOrDinner;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    @Override
    public String toString() {
        return "Item{" +
                "colorIndex=" + colorIndex +
                ", placeName='" + placeName + '\'' +
                ", lunchOrDinner='" + lunchOrDinner + '\'' +
                '}';
    }
}
