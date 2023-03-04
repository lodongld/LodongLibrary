package com.lodong.multipleimagepickers;

public class Data {
    String uri;
    Boolean selected;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Data() {
    }

    public Data(String uri, Boolean selected) {
        this.uri = uri;
        this.selected = selected;
    }
}
