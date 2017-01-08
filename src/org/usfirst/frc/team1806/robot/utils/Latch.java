package org.usfirst.frc.team1806.robot.utils;

public class Latch {
    private boolean lastVal;

    public boolean update(boolean newVal) {
        boolean result = newVal && !lastVal;
        lastVal = newVal;
        return result;
    }
}