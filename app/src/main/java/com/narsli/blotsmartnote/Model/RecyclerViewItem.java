package com.narsli.blotsmartnote.Model;

public class RecyclerViewItem {
    //drawable-um imagnerin kareli int-ov dimel
    private int imageResource;
    private String zakaz;
    //cons -n avtomat kareli e sarqel alt+enter
    public RecyclerViewItem(int imageResource, String zakaz) {
        this.imageResource = imageResource;
        this.zakaz = zakaz;
    }

    //cons -n avtomat kareli e sarqel alt+enter
    public RecyclerViewItem() {
    }
    //cons -n avtomat kareli e sarqel alt+enter
//seter ev geter karel avtomat sarqel alt+insert

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public void setZakaz(String zakaz) {
        this.zakaz = zakaz;
    }

    public String getZakaz() {
        return zakaz;
    }

    public int getImageResource() {
        return imageResource;
    }
}

