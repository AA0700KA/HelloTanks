package java.devcolibri.itvdn.com.hellotanks.util;

public class GameFPS {

    private static int tankFPS = 5;
    private static int bulletFPS = 24;

    public static int getTankFPS() {
        return tankFPS;
    }

    public static void setTankFPS(int tankFPS) {
        GameFPS.tankFPS = tankFPS;
    }

    public static int getBulletFPS() {
        return bulletFPS;
    }

    public static void setBulletFPS(int bulletFPS) {
        GameFPS.bulletFPS = bulletFPS;
    }

}
