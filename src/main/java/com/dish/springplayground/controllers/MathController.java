package com.dish.springplayground.controllers;

import com.dish.springplayground.services.MathService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
