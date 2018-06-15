package entity;

public class Hotel {

    public Hotel(int id, String name, int starCount) {
        this.id = id;
        this.name = name;
        this.starCount = starCount;
    }

    private int id;
    private String name;
    private int starCount;

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

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", starCount=" + starCount +
                '}';
    }
}
