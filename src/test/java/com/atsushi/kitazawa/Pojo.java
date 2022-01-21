package com.atsushi.kitazawa;

import java.util.List;

public class Pojo {
    private String a;
    private int b;
    private List<String> c;

    public Pojo(String a, int b, List<String> c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public List<String> c() {
        return this.c;
    }
}
