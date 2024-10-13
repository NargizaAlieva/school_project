package org.example.school_project.entity;

public class YourDataClass {
    private String field1;
    private String field2;

    public String getField1() {
        return field1;
    }

    public YourDataClass setField1(String field1) {
        this.field1 = field1;
        return this;
    }

    public String getField2() {
        return field2;
    }

    public YourDataClass setField2(String field2) {
        this.field2 = field2;
        return this;
    }

    @Override
    public String toString() {
        return "Field1: " + field1 + ", Field2: " + field2;
    }
}
