package com.dish.springplayground.services;

import java.util.List;
import java.util.stream.Collectors;

public class MathService {

    private String operation = "add";
    private String x, y;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public int calc() {
        int intX = Integer.parseInt(this.getX());
        int intY = Integer.parseInt(this.getY());
        int result = 0;
        switch (this.getOperation()) {
            case "add":
                result =  intX + intY;
                break;
            case "subtract":
                result =  intX - intY;
                break;
            case "multiply":
                result =  intX * intY;
                break;
            case "divide":
                result =  intY == 0 ? 0 : intX / intY;
                break;
            default:
                break;
        }

        return result;
    }

    public String calcString() {
        System.out.println(this.toString());
        StringBuilder sb = new StringBuilder();
        switch (this.getOperation()) {
            case "add":
                sb.append(this.getX());
                sb.append(" + ");
                sb.append(this.getY());
                sb.append(" = ");
                sb.append(this.calc());
                break;
            case "subtract":
                sb.append(this.getX());
                sb.append(" - ");
                sb.append(this.getY());
                sb.append(" = ");
                sb.append(this.calc());
                break;
            case "multiply":
                sb.append(this.getX());
                sb.append(" * ");
                sb.append(this.getY());
                sb.append(" = ");
                sb.append(this.calc());
                break;
            case "divide":
                sb.append(this.getX());
                sb.append(" / ");
                sb.append(this.getY());
                sb.append(" = ");
                sb.append(this.calc());
                break;
            default:
                break;

        }

        return sb.toString();
    }

    public static String sumString(List<Integer> input) {
        StringBuilder sb = new StringBuilder();
        sb.append(input.stream()
                .map(i -> String.valueOf(i))
                .collect(Collectors.joining(" + "))
        );
        sb.append(" = ");
        sb.append(sum(input));
        return sb.toString();
    }

    public static int sum(List<Integer> input) {
        return input.stream().mapToInt(i -> i).sum();
    }

    @Override
    public String toString() {
        return "MathService{" +
                "operation='" + operation + '\'' +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                '}';
    }
}
