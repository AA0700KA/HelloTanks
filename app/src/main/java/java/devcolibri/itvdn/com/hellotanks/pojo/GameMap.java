package java.devcolibri.itvdn.com.hellotanks.pojo;



import java.util.List;

public class GameMap {

    private String name;
    private List<MovableObject> objects;
    private StopableObject[][] stopableObjects;

    private int width;
    private int height;

    public StopableObject[][] getStopableObjects() {
        return stopableObjects;
    }

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

    public void setStopableObjects(StopableObject[][] stopableObjects) {
        this.stopableObjects = stopableObjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MovableObject> getObjects() {
        return objects;
    }

    public void setObjects(List<MovableObject> objects) {
        this.objects = objects;
    }

}
