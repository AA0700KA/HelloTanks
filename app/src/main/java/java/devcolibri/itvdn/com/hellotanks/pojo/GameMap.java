package java.devcolibri.itvdn.com.hellotanks.pojo;



import java.util.List;

public class GameMap {

    private String name;
    private List<AbstractObjects> objects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AbstractObjects> getObjects() {
        return objects;
    }

    public void setObjects(List<AbstractObjects> objects) {
        this.objects = objects;
    }

}
