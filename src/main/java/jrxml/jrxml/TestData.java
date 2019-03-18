package jrxml.jrxml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TestData {
    private static final String FIELD_NAME = "aField";
    public static List<Map<String, Object>> tableData() {
        List<Map<String, Object>> data = new ArrayList<>();

        data.add(Collections.singletonMap(FIELD_NAME, "shrdlu"));
        data.add(Collections.singletonMap(FIELD_NAME, "cmfwyp"));

        return data;
    }
}
