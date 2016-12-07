package com.aibinong.backyard.util;

/**
 * Created by ouwa on 16/7/15.
 */
public class ChanceLevel {
    private int min;
    private int max;
    private int chance;

    public ChanceLevel(){

    }
    public ChanceLevel(int min, int max, int chance) {
        this.min = min;
        this.max = max;
        this.chance = chance;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    @Override
    public String toString() {
        return String.format("[%d-%d]%d%%", min, max, chance);
    }
}
