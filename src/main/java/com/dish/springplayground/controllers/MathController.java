package com.dish.springplayground.controllers;

import com.dish.springplayground.services.MathService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/math")
public class MathController {

    @GetMapping("/calculate")
    public String calculate(MathService mathService) {
        return mathService.calcString();
    }

    @PostMapping("/sum")
    public String sum(@RequestParam List<Integer> n) {
//        System.out.println("here is n: ");
//        n.stream().forEach(el -> System.out.println(el));
        return MathService.sumString(n);
    }

    @RequestMapping("/volume/{length}/{width}/{height}")
    public String volume(
            @PathVariable Map pathVariables
    ) {
        int l = Integer.parseInt(pathVariables.get("length").toString());
        int w = Integer.parseInt(pathVariables.get("width").toString());
        int h = Integer.parseInt(pathVariables.get("height").toString());
        int vol = l * w * h;
        return String.format("The volume of a %dx%dx%d rectangle is %d", l, w, h, vol);
    }
}
