package de.bs14.pricecalculator;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public final class Controller {

    //formats the numeric value to look like a monetary value
    public static final DecimalFormat DEC_FORMAT = new DecimalFormat(",####,##0.00");

    private final Map<String, List<Calculation>> userCalculations = new HashMap<>();

    @PostMapping("/login")
    public void login(@RequestParam final String name) {
        userCalculations.putIfAbsent(name, new ArrayList<>());
    }

    @PostMapping("/calculate")
    public Calculation calculate(
            @RequestParam final String name,
            @RequestParam final int number,
            @RequestParam final BigDecimal factor
    ) {
        final var result = multiply(number, factor);
        final var calculation = new Calculation(number, factor, result);

        userCalculations.get(name).add(calculation);
        return calculation;
    }

    @GetMapping("/multiply")
    public String multiply(@RequestParam final int number, @RequestParam final BigDecimal factor) {
        final var num = BigDecimal.valueOf(number);
        final var result = num.multiply(factor);
        final var rounded = result.setScale(2, RoundingMode.HALF_EVEN);
        return DEC_FORMAT.format(rounded);
    }

    @GetMapping("/calculations")
    public List<Calculation> getCalculations(@RequestParam final String name) {
        return userCalculations.getOrDefault(name, new ArrayList<>());
    }

}