package com.icboluo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * json数据转换为java实体类
 *
 * @author icboluo
 * @since 2022-11-26 9:49
 */
public class JsonToClass {
    private static final String FILE_PATH = ".idea/httpRequests/";
    private static final String FILE_NAME = "2022-11-26T111111.200.json";
    /**
     * 是否驼峰命名
     */
    private static final boolean IS_HUMP_NOMENCLATURE = true;
    private static final boolean IS_SORT = true;
    private static final String GENERATE_FILE_NAME = "abc.java";

    private static final UnaryOperator<JSONObject> GET_DATA = jsonObject -> {
        JSONArray data = (JSONArray) jsonObject.get("data");
        return data.getJSONObject(0);
    };

    public static void main(String[] args) throws IOException {
        File jsonFile = new File(FILE_PATH + FILE_NAME);
        if (!jsonFile.exists()) {
            throw new IcBoLuoI18nException("file.not.exist");
        }
        JSONObject jsonObject;
        try (FileReader fr = new FileReader(jsonFile)) {
            StringBuilder sb = IoHelper.readFileReader1(fr);
            jsonObject = JSON.parseObject(sb.toString());
        }

        File javaFile = new File(FILE_PATH + GENERATE_FILE_NAME);
        try (FileWriter fw = new FileWriter(javaFile)) {
            JSONObject data = GET_DATA.apply(jsonObject);
            List<String> fields = data.keySet().stream()
                    .map(field -> IS_HUMP_NOMENCLATURE ? humpNomenclature(field) : field)
                    .toList();
            if (IS_SORT) {
                Collections.sort(fields);
            }
            for (String field : fields) {
                fw.write("private String " + field + ";\n");
            }
            System.out.println("write successful");
        }
    }

    /**
     * 驼峰命名
     *
     * @param str
     * @return
     */
    private static String humpNomenclature(String str) {
        String res = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '_') {
                i++;
                res += Character.toUpperCase(str.charAt(i));
            } else {
                res += str.charAt(i);
            }
        }
        return res;
    }
}
