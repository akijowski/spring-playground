package com.dish.springplayground.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MathController.class)
public class MathControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void math_calculate_endpoint_with_no_operation_should_return_add() throws Exception {
        String expected = "30 + 5 = 35";

        this.mockMvc.perform(get("/math/calculate?x=30&y=5"))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    public void math_calculate_endpoint_with_add_operation_should_return_add() throws Exception {
        String expected = "4 + 6 = 10";

        this.mockMvc.perform(get("/math/calculate?operation=add&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    public void math_calculate_endpoint_with_subtract_operation_should_return_subtract() throws Exception {
        String expected = "4 - 6 = -2";

        this.mockMvc.perform(get("/math/calculate?operation=subtract&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    public void math_calculate_endpoint_with_multiply_operation_should_return_multiply() throws Exception {
        String expected = "4 * 6 = 24";

        this.mockMvc.perform(get("/math/calculate?operation=multiply&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    public void math_calculate_endpoint_with_divide_operation_should_return_divide() throws Exception {
        String expected = "30 / 5 = 6";

        this.mockMvc.perform(get("/math/calculate?operation=divide&x=30&y=5"))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    public void math_sum_endpoint_returns_sum_of_n() throws Exception {
        String expected = "4 + 5 + 6 = 15";

        this.mockMvc.perform(post("/math/sum?n=4&n=5&n=6"))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    public void math_volume_endpoint_get_returns_volume_of_rectangle() throws Exception {
        String expected = "The volume of a 2x3x4 rectangle is 24";

        this.mockMvc.perform(get(String.format("/math/volume/%d/%d/%d", 2, 3, 4)))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    public void math_volume_endpoint_post_returns_volume_of_rectangle() throws Exception {
        String expected = "The volume of a 2x3x4 rectangle is 24";

        this.mockMvc.perform(post(String.format("/math/volume/%d/%d/%d", 2, 3, 4)))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    public void math_volume_endpoint_patch_returns_volume_of_rectangle() throws Exception {
        String expected = "The volume of a 2x3x4 rectangle is 24";

        this.mockMvc.perform(patch(String.format("/math/volume/%d/%d/%d", 2, 3, 4)))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    public void math_volume_endpoint_put_returns_volume_of_rectangle() throws Exception {
        String expected = "The volume of a 2x3x4 rectangle is 24";

        this.mockMvc.perform(put(String.format("/math/volume/%d/%d/%d", 2, 3, 4)))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

}