package com.thinktag.consistenthash;

public class Node {

    private int angle;
    private String hash;
    private String value;

    public Node(String value, String hash, int angle) {
        this.value = value;
        this.hash = hash;
        this.angle = angle;
    }

    public int getAngle() {
        return angle;
    }

    public String getHash() {
        return hash;
    }

    public String getValue() {
        return value;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
