package printtool.entity;

import java.util.List;

public class PosWholeTemplate {

    private int width;
    private int height;
    private int dx;
    private int dy;
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

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public List<PosSingleTemplate> getSingles() {
        return singles;
    }

    public void setSingles(List<PosSingleTemplate> singles) {
        this.singles = singles;
    }
}
