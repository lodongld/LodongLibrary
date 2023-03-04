package com.lodong.lib;

import java.text.DecimalFormat;

public class LodongLib {

    public String commaOnStringChangedFromInteger(Integer integer){
        DecimalFormat df = new DecimalFormat("###,###");
        String money = df.format(integer);

        return money;
    }

    public String commaOnStringChangedFromString(String string){
        DecimalFormat df = new DecimalFormat("###,###");
        String money = df.format(string);

        return money;
    }

}
