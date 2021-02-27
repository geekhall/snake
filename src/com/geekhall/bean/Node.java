package com.geekhall.bean;


import com.geekhall.utils.Constants;
import com.sun.tools.internal.jxc.ap.Const;

import java.util.Random;

/**
 * @author yiny
 * @Type Node.java
 * @Desc
 * @date 2/27/21 9:47 PM
 */
public class Node {
    private int x;
    private int y;

    public Node(){

    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void random(){
        Random r = new Random();

        this.x = r.nextInt(Constants.WIDTH_COUNT);
        this.y = r.nextInt(Constants.HEIGHT_COUNT);
    }

}
