package Level_34.task3404;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Рекурсия для мат. выражения
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
    }

    public void recurse(final String expression, int countOperation) {

        SuperDeque superDeque = SuperDeque.getInstance();
        ArrayDeque<String> stack = superDeque.stack;
        ArrayDeque<String> operations = superDeque.operations;

        Matcher unoStartMatcher = Pattern.compile("^-[\\d.]*").matcher(expression);
        Matcher unoMatcher = Pattern.compile("\\(-[\\d.]*").matcher(expression);
        Matcher sinMatcher = Pattern.compile("sin").matcher(expression);
        Matcher cosMatcher = Pattern.compile("cos").matcher(expression);
        Matcher tanMatcher = Pattern.compile("tan").matcher(expression);

        String middleResult = expression
                .replaceAll("sin","s")
                .replaceAll("cos","c")
                .replaceAll("tan","t")
                .replaceAll(" ","");

        if(superDeque.isEditable){

            if(middleResult.matches("[\\d.]+")){
                printResult(expression,0);
                return;
            }

            if(middleResult.matches("\\([\\d.]+\\)")){
                printResult(expression.substring(1,expression.length()-1),0);
                return;
            }

            countOperation = middleResult.replaceAll("[\\d.)(]","").length();

            if (unoMatcher.find() || unoStartMatcher.find()|| sinMatcher.find() || cosMatcher.find() || tanMatcher.find()) {

                unoStartMatcher = Pattern.compile("^-[\\d.]*").matcher(middleResult);
                String middleResult2 = middleResult;
                if(unoStartMatcher.find()){
                    middleResult2 = middleResult.replaceFirst(unoStartMatcher.group(),"(#1)*"+unoStartMatcher.group().substring(1));
                }

                unoMatcher = Pattern.compile("\\(-[\\d.]*").matcher(middleResult2);
                String middleResult3 = middleResult2;
                String temp = middleResult2;
                while (unoMatcher.find()){
                    middleResult3 = temp.replace(unoMatcher.group(),"((#1)*"+unoMatcher.group().substring(2));
                    temp = middleResult3;
                }

                String newExpression = middleResult3;
                superDeque.isEditable=false;
                recurse(newExpression, countOperation);
                return;
            }

        }
        superDeque.isEditable=false;

        Matcher startWithDigit = Pattern.compile("^[\\d.#]+").matcher(expression);
        if(startWithDigit.find()){
            stack.addLast(startWithDigit.group());
            String newExpr = expression.replaceFirst("^[\\d.#]+","");
            recurse(newExpr,countOperation);
            return;
        }

        Matcher startWithOper = Pattern.compile("^[-+/*^cst]").matcher(expression);
        if(startWithOper.find()){
            String operNow = startWithOper.group();
            if(operations.isEmpty()||operations.peekLast().equals("(")){
                operations.addLast(startWithOper.group());
            } else {

                if(getPriority(operations.peekLast())>=getPriority(operNow)){
                    stack.addLast(operations.pollLast());
                    operations.addLast(operNow);
                }
                else {
                    operations.addLast(operNow);
                }
            }
            String newExpr = expression.replaceFirst("^[-+/*^cst]","");
            recurse(newExpr,countOperation);
            return;
        }

        Matcher startWithBracesOpen = Pattern.compile("^\\(").matcher(expression);
        if(startWithBracesOpen.find()){
            operations.addLast(startWithBracesOpen.group());
            String newExpr = expression.replaceFirst("^\\(","");
            recurse(newExpr,countOperation);
            return;
        }

        Matcher startWithBracesClose = Pattern.compile("^\\)").matcher(expression);
        if(startWithBracesClose.find()){

            while (!operations.peekLast().equals("(")){
              stack.addLast(operations.pollLast());
            }
            operations.pollLast();

            String newExpr = expression.replaceFirst("^\\)","");
            recurse(newExpr,countOperation);
            return;
        }

        if(middleResult.equals("")){
            printResult(calculate().replaceAll("#","-"),countOperation);
            SuperDeque.setInstance();
        }
        //implement
    }

    public String calculate (){
        ArrayDeque<String> stack = SuperDeque.instance.stack;
        ArrayDeque<String> operations = SuperDeque.instance.operations;

        if(operations.size()>0){
            while (!operations.isEmpty()){
                stack.addLast(operations.pollLast());
            }
        }

        while (!stack.isEmpty()){
            while (stack.peekFirst().matches("[\\d.#]+")){
                operations.addLast(stack.pollFirst());
            }

            String result = "";
            if(stack.size()>0&&(stack.peekFirst().equals("s")||stack.peekFirst().equals("c")||stack.peekFirst().equals("t"))){
                String num = operations.pollLast();
                result = trigonOperation(num,stack.pollFirst());

            } else {
            String num2 = operations.pollLast();
            String num1 = operations.pollLast();
            result = arithOperation(num1+stack.peekFirst()+num2,stack.pollFirst());
            }
            operations.addLast(result);
        }
        return operations.pollLast();
    }

    public int getPriority(String str){
        Map<String,Integer> priority = SuperDeque.instance.priority;
        priority.put("s",3); priority.put("c",3);
        priority.put("t",3);
        priority.put("^",2);

        priority.put("*",1); priority.put("/",1);
        priority.put("-",0); priority.put("+",0);
        priority.put("#",0);

        for(Map.Entry<String,Integer> x: priority.entrySet()){
            if(x.getKey().equals(str)) return x.getValue();
        }
        return 0;
    }

    private void printResult(String express, int countOperation) {
        NumberFormat form = NumberFormat.getInstance(Locale.US);
        DecimalFormat df = (DecimalFormat) form;
        df.applyPattern("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        System.out.print(df.format(Double.parseDouble(express)));
        System.out.println(" " + countOperation);
    }

    public Solution() {
        //don't delete
    }

    private String trigonOperation(String str, String operation){
        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
        String strWithMines = "";
        if (str.startsWith("#")) {
            strWithMines = str.replace("#","-");
            str = strWithMines;
        }
        double radian = Math.toRadians(Double.parseDouble(str));
        switch (operation) {
            case "s": return String.valueOf(Math.sin(radian));
            case "c": return String.valueOf(Math.cos(radian));
            case "t": return String.valueOf(Math.tan(radian));
        }
        return "";
    }

    private String arithOperation(String str, String operation) {

        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
        Matcher digitMatcher = Pattern.compile("[\\d.#]+").matcher(str);

        String str1 = "";
        String str2 = "";
        while (digitMatcher.find()) {
            if (str1.equals("")) str1 = digitMatcher.group();
            else str2 = digitMatcher.group();
        }

        if (str1.startsWith("#") && str2.startsWith("#")) {
            BigDecimal num1 = new BigDecimal(str1.substring(1),mc);
            BigDecimal num2 = new BigDecimal(str2.substring(1),mc);
            switch (operation) {
                case "^": {
                    double numD1 = Double.parseDouble(num1.toString());
                    double numD2 = Double.parseDouble(num2.toString());
                    if (numD2 % 2 == 0) return String.valueOf(Math.pow(1.0/numD1,numD2));
                    else return "#" + Math.pow(1.0/numD1, numD2);
                }
                case "*": return String.valueOf(num1.multiply(num2));
                case "/": return String.valueOf(num1.divide(num2,mc));
                case "+": return "#" + (num2.add(num1,mc));
                case "-": return String.valueOf(num2.subtract(num1,mc));
            }
        }

        if (!str1.startsWith("#") && str2.startsWith("#")) {
            BigDecimal num1 = new BigDecimal(str1,mc);
            BigDecimal num2 = new BigDecimal(str2.substring(1),mc);
            switch (operation) {
                case "^": {
                    double numD1 = Double.parseDouble(num1.toString());
                    double numD2 = Double.parseDouble(num2.toString());
                    return String.valueOf(Math.pow(1.0/numD1, numD2));
                }
                case "*": {
                    if(num1.equals(BigDecimal.ZERO)||num2.equals(BigDecimal.ZERO)){
                        return "0";
                    }
                    return "#" + (num1.multiply(num2,mc));
                }

                case "/": return "#" + (num1.divide(num2,mc));
                case "+": return String.valueOf(num1.subtract(num2,mc));
                case "-": {
                    if (num1.compareTo(num2)>0) return "#" + (num2.add(num1,mc));
                    else return String.valueOf(num2.add(num1,mc));
                }
            }
        }

        if (str1.startsWith("#") && !str2.startsWith("#")) {
            BigDecimal num1 = new BigDecimal(str1.substring(1),mc);
            BigDecimal num2 = new BigDecimal(str2,mc);
            switch (operation) {
                case "^": {
                    double numD1 = Double.parseDouble(num1.toString());
                    double numD2 = Double.parseDouble(num2.toString());
                    if (numD2 % 2 == 0) return String.valueOf(Math.pow(numD1, numD2));
                    else return "#" + Math.pow(numD1,numD2);
                }
                case "*": {
                    if (num1.equals(BigDecimal.ZERO) || num2.equals(BigDecimal.ZERO)) {
                        return "0";
                    }
                    return "#" + (num1.multiply(num2,mc));
                }
                case "/": return "#" + (num1.divide(num2,mc));
                case "+": return String.valueOf(num2.subtract(num1,mc));
                case "-": return "#" + (num1.add(num2,mc));
            }
        }

        if (!str1.startsWith("#") && !str2.startsWith("#")) {
            BigDecimal num1 = new BigDecimal(str1,mc);
            BigDecimal num2 = new BigDecimal(str2,mc);
            switch (operation) {
                case "^": {
                    double numD1 = Double.parseDouble(num1.toString());
                    double numD2 = Double.parseDouble(num2.toString());
                    return String.valueOf(Math.pow(numD1, numD2));
                }
                case "*": return String.valueOf(num1.multiply(num2,mc));
                case "/": return String.valueOf(num1.divide(num2,mc));
                case "+": return String.valueOf(num1.add(num2,mc));
                case "-": return String.valueOf(num1.subtract(num2,mc));
            }
        }
        return "";
    }

    public static class SuperDeque {
        private ArrayDeque<String> stack;
        private ArrayDeque<String> operations;
        private static SuperDeque instance;
        private boolean isEditable = true;
        private Map<String,Integer> priority;

        private SuperDeque() {
            stack = new ArrayDeque<>();
            operations = new ArrayDeque<>();
            priority = new HashMap<>();
        }

        public static SuperDeque getInstance() {
            if (instance == null) return instance = new SuperDeque();
            else return instance;
        }

        public static void setInstance(){
            instance = new SuperDeque();
        }
    }
}