package com.exist.model;

public class Pair implements Comparable<Pair> {

    private String key;
    private String value;
    private String keyValuePair;

    public Pair(String key, String value) {
        this.key = key;
        this.value = value;
        this.assembleKeyPair(key, value);
    }

    public void initializePair(String key, String value) {
        this.key = key;
        this.value = value;
        this.assembleKeyPair(key, value);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
        this.updateKeyPair(key);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        this.updateValuePair(value);
    }

    public String getKeyValuePair() {
        return this.keyValuePair;
    }

    public void updateKeyPair(String key) {

        int index = this.keyValuePair.indexOf(",");

        this.keyValuePair = key + this.keyValuePair.substring(index);
    }

    private void updateValuePair(String value) {

        int index = this.keyValuePair.indexOf(",");

        this.keyValuePair = this.keyValuePair.substring(0, index + 1) + value;
    }

    private void assembleKeyPair(String key, String value) {
        this.keyValuePair = key + "," + value;
    }

    @Override
    public int compareTo(Pair other) {
        return this.keyValuePair.compareTo(other.keyValuePair);
    }
}
