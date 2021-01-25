package printtool.entity;

import java.util.List;

public class PosWholeTemplate {

    private int width;
    private int height;
    private int topHeight;
    private int bottomHeight;
    private List<PosSingleTemplate> singles;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getTopHeight() {
        return topHeight;
    }

    public void setTopHeight(int topHeight) {
        this.topHeight = topHeight;
    }

    public int getBottomHeight() {
        return bottomHeight;
    }

    public void setBottomHeight(int bottomHeight) {
        this.bottomHeight = bottomHeight;
    }

    public List<PosSingleTemplate> getSingles() {
        return singles;
    }

    public void setSingles(List<PosSingleTemplate> singles) {
        this.singles = singles;
    }
}
