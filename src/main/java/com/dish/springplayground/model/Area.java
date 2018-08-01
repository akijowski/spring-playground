package com.dish.springplayground.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Area {

    private String type, radius, length, width;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Area{" +
                "type='" + type + '\'' +
                ", radius='" + radius + '\'' +
                ", length='" + length + '\'' +
                ", width='" + width + '\'' +
                '}';
    }

    public String calculateArea() {
        String areaString;
        switch(this.getType()) {
            case "circle":
                String circle_template = "Area of a circle with a radius of %s is %s";
                int r = Integer.parseInt(this.getRadius());
                double area_c = getCircleArea();
                areaString = String.format(circle_template,
                        String.valueOf(r),
                        String.valueOf(area_c));
                break;

            case "rectangle":
                String rect_template = "Area of a %sx%s rectangle is %s";
                int w = Integer.parseInt(this.getWidth());
                int l = Integer.parseInt(this.getLength());
                int area_r = w * l;
                areaString = String.format(rect_template, w, l, area_r);
                break;

            default:
                areaString = "Invalid";
                break;
        }

        return areaString;
    }

    private double getCircleArea() {
        int r = Integer.parseInt(this.getRadius());
        r = r > 0 ? r : 1;
        double area_c = Math.PI * Math.pow(r, 2);
        double area_rounded = BigDecimal.valueOf(area_c)
                .setScale(5, RoundingMode.HALF_UP)
                .doubleValue();

        return area_rounded;
    }
}
