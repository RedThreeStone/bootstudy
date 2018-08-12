package it.lei.day1.entity;

public class Home {
    private  String homeName;
    private  int homeAge;

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public int getHomeAge() {
        return homeAge;
    }

    public void setHomeAge(int homeAge) {
        this.homeAge = homeAge;
    }

    @Override
    public String toString() {
        return "Home{" +
                "homeName='" + homeName + '\'' +
                ", homeAge=" + homeAge +
                '}';
    }
}
