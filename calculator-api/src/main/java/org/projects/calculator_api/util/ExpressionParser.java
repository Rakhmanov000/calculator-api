package org.projects.calculator_api.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpressionParser {
    private int findClosingParethesis(List<String> list, int index) {
        for (int i = index; i < list.size(); i ++) {
            if (list.get(i).equals(")")) {
                return i;
            }
        }
        return -1;
    }

    public double parseExpressions(String exam) {
        exam = exam.replaceAll(" +","");
        exam = exam.replaceAll("(?<=[\\d)])(?=\\()", "*");
        exam = exam.replaceAll("(?<=\\))(?=\\d)", "*");

        String[] array = exam.split("((?<=[-+*/()])|(?=[-+*/()]))");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(array));

        // Обрабатываем скобки
        while (list.contains("(")) {
            int startIndex = list.lastIndexOf("(");
            int endIndex = findClosingParethesis(list, startIndex);
            if (endIndex == -1) {
                throw new IllegalArgumentException("Неправильное расположение скобки");
            }
            operation1(list.subList(startIndex + 1, endIndex));
            list.remove(startIndex);
            list.remove(startIndex + 1);
        }
        return operation1(list);
    }

    private double operation1(List<String> list) {
        list = operation2(list, "*", "/");
        list = operation2(list, "+", "-");
        return Double.parseDouble(list.get(0));
    }

    private List<String> operation2(List<String> list, String op1, String op2) {
        while (list.contains(op1) || list.contains(op2)) {
            int index1 = list.indexOf(op1);
            int index2 = list.indexOf(op2);
            int index = (index1 != -1 && (index2 == -1 || index1 < index2)) ? index1 : index2;
            list.set(index - 1, String.valueOf(Operation.operation(list.get(index - 1), list.get(index + 1), list.get(index))));
            list.remove(index);
            list.remove(index);
        }
        return list;
    }
}
