package com.guzz.ide.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StrUtil {


    private static final Pattern pattern_LetterOrDigit = Pattern.compile("[A-Za-z0-9]+");
    private static final Pattern patternVar = Pattern.compile("^[A-Za-z_$][A-Za-z0-9-_$]*$");


    // 验证是否符合变量命名规范
    // 字母（A-Z 或 a-z）、美元符号（$）或下划线（_）开头
    // 变量名可以包含字母、数字、美元符号和下划线
    public static boolean isValidVariableName(String name) {
        return !isBlank(name) && patternVar.matcher(name.strip()).matches();
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static List<String> cutVariateToArray(String word) {
        word = word.strip();
        List<String> parts = new ArrayList<>();
        StringBuilder part = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (!Character.isLetterOrDigit(c) ||
                    (Character.isUpperCase(c) && (i == 0 || !Character.isUpperCase(word.charAt(i - 1)))) ||
                    (Character.isLetter(c) && (i == 0 || !Character.isLetterOrDigit(word.charAt(i - 1))))) {

                if (!part.isEmpty()) {
                    parts.add(part.toString());
                    part.setLength(0);
                }
            }
            part.append(c);

        }
        // 处理最后一个部分
        if (!part.isEmpty()) {
            parts.add(part.toString());
        }
        return parts.stream().filter(s -> pattern_LetterOrDigit.matcher(s).matches())
                .map(String::toLowerCase).toList();
    }

    public static String toCamelCase(List<String> words) {
        return words.stream().reduce((a, b) -> a + upperFirst(b)).orElse(null);
    }

    public static String toSnakeCase(List<String> words) {
        return words.stream().reduce((a, b) -> a + "_" + b).orElse(null);
    }

    public static String toPascalCase(List<String> words) {
        return words.stream().reduce((a, b) -> upperFirst(a) + upperFirst(b)).orElse(null);
    }

    public static String toSnakeUpperCase(List<String> words) {
        return words.stream().reduce((a, b) -> a.toUpperCase() + "_" + b.toUpperCase()).orElse(null);
    }

    public static String toSnakePascalCase(List<String> words) {
        return words.stream().reduce((a, b) -> upperFirst(a) + "_" + upperFirst(b)).orElse(null);
    }

    public static String upperFirst(String input) {
        return Character.toUpperCase(input.charAt(0)) + input.substring(1);
    }


    public static void main(String[] args) {
        List<String> words = cutVariateToArray("camelCaseBaaa");

        System.out.println(toCamelCase(words));
        System.out.println(toPascalCase(words));
        System.out.println(toSnakeCase(words));
        System.out.println(toSnakePascalCase(words));
        System.out.println(toSnakeUpperCase(words));

    }

}
