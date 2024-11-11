package de.bs14.pricecalculator;

import java.math.BigDecimal;


/**
 *  Data-container for saving queries to history
 *
 * @param number kilometer/minutes number
 * @param factor multiplication factor
 * @param result result formatted as Euros
 */
public record Calculation(int number, BigDecimal factor, String result) {
}
