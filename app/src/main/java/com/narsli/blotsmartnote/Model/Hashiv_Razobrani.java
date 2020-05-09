package com.narsli.blotsmartnote.Model;

//model clas e, vori mej razshifrovka enq anum ExtraOrderBtnDialog-ic stacvac order string
//popoxakann, bajanum enq 4 masi, vorn use enq ExtraButtonDialog-clasi mej
public class Hashiv_Razobrani {
    private int
            xosacac_tiv,
    //            mast,//ete =4-i, apa boy e
    quansh;//2 kam 4
    private boolean
            kapuyt;//25 kam 0

    //cons -n avtomat kareli e sarqel alt+enter
    public Hashiv_Razobrani(int xosacac_tiv, int quansh, boolean kapuyt) {
        this.xosacac_tiv = xosacac_tiv;
        this.quansh = quansh;
        this.kapuyt = kapuyt;
    }

    // datark cons
    public Hashiv_Razobrani() {
    }

    public void setXosacac_tiv(int xosacac_tiv) {
        this.xosacac_tiv = xosacac_tiv;
    }

    public void setQuansh(int quansh) {
        this.quansh = quansh;
    }

    public void setKapuyt(boolean kapuyt) {
        this.kapuyt = kapuyt;
    }

    public int getXosacac_tiv() {
        return xosacac_tiv;
    }

    public int getQuansh() {
        return quansh;
    }

    public boolean getKapuyt() {
        return kapuyt;
    }
}
