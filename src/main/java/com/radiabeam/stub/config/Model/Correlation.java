package com.radiabeam.stub.config.Model;

public class Correlation {
    private String name;
    private String regexp;
    private String replace;

    @Override
    public String toString() {
        return "Correlation{" +
                "name='" + name + '\'' +
                ", regexp='" + regexp + '\'' +
                ", replace='" + replace + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegexp() {
        return regexp;
    }

    public void setRegexp(String regexp) {
        this.regexp = regexp;
    }

    public String getReplace() {
        return replace;
    }

    public void setReplace(String replace) {
        this.replace = replace;
    }
}
