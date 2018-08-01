package com.dish.springplayground.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class MathServiceTest {

    @Test
    public void calc_no_operation_returns_correct_with_add() throws Exception {
        int expected = 3;

        MathService mathService = new MathService();
        mathService.setX("1");
        mathService.setY("2");

        int actual = mathService.calc();

        assertThat(expected, is(actual));
    }

    @Test
    public void calc_add_returns_correct_with_add() throws Exception {
        int expected = 5;

        MathService mathService = new MathService();
        mathService.setOperation("add");
        mathService.setX("2");
        mathService.setY("3");

        int actual = mathService.calc();

        assertThat(expected, is(actual));
    }

    @Test
    public void calc_subtract_returns_correct_with_subtract() throws Exception {
        int expected = -1;

        MathService mathService = new MathService();
        mathService.setOperation("subtract");
        mathService.setX("2");
        mathService.setY("3");

        int actual = mathService.calc();

        assertThat(expected, is(actual));
    }

    @Test
    public void calc_multiply_returns_correct_with_multiply() throws Exception {
        int expected = 6;

        MathService mathService = new MathService();
        mathService.setOperation("multiply");
        mathService.setX("2");
        mathService.setY("3");

        int actual = mathService.calc();

        assertThat(expected, is(actual));
    }

    @Test
    public void calc_divide_returns_correct_with_divide() throws Exception {
        int expected = 5;

        MathService mathService = new MathService();
        mathService.setOperation("divide");
        mathService.setX("25");
        mathService.setY("5");

        int actual = mathService.calc();

        assertThat(expected, is(actual));
    }

    @Test
    public void calcString_returns_correct_string_with_no_operation() throws Exception {
        String expected = "1 + 2 = 3";

        MathService mathService = new MathService();
        mathService.setX("1");
        mathService.setY("2");

        String actual = mathService.calcString();

        assertThat(expected, is(actual));
    }

    @Test
    public void calcString_returns_correct_string_with_add_operation() throws Exception {
        String expected = "1 + 2 = 3";

        MathService mathService = new MathService();
        mathService.setOperation("add");
        mathService.setX("1");
        mathService.setY("2");

        String actual = mathService.calcString();

        assertThat(expected, is(actual));
    }

    @Test
    public void calcString_returns_correct_string_with_subtract_operation() throws Exception {
        String expected = "3 - 2 = 1";

        MathService mathService = new MathService();
        mathService.setOperation("subtract");
        mathService.setX("3");
        mathService.setY("2");

        String actual = mathService.calcString();

        assertThat(expected, is(actual));
    }

    @Test
    public void calcString_returns_correct_string_with_multiply_operation() throws Exception {
        String expected = "3 * 2 = 6";

        MathService mathService = new MathService();
        mathService.setOperation("multiply");
        mathService.setX("3");
        mathService.setY("2");

        String actual = mathService.calcString();

        assertThat(expected, is(actual));
    }

    @Test
    public void calcString_returns_correct_string_with_divide_operation() throws Exception {
        String expected = "24 / 3 = 8";

        MathService mathService = new MathService();
        mathService.setOperation("divide");
        mathService.setX("24");
        mathService.setY("3");

        String actual = mathService.calcString();

        assertThat(expected, is(actual));
    }

    @Test
    public void sum_returns_correct_result() throws Exception {
        List<Integer> input = asList(1,2,3);
        int expected = 6;

        int actual = MathService.sum(input);

        assertThat(expected, is(actual));
    }

    @Test
    public void sumString_returns_correct_string() throws Exception {
        List<Integer> input = asList(1,2,3);
        String expected = "1 + 2 + 3 = 6";

        String actual = MathService.sumString(input);
        assertThat(expected, is(actual));
    }
}