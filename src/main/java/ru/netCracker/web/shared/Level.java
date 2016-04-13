package ru.netCracker.web.shared;

/**
 * Created by rewweRrr on 24.02.2016
 */
public class Level {
    private static Level instance;
    public static final int EASY = 1;
    public static final int MEDIUM = 2;
    public static final int HARD = 3;
    private int level = EASY;

    public static synchronized Level getInstance() {
        if (instance == null) {
            instance = new Level();
        }
        return instance;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}

