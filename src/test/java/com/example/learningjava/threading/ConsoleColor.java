package com.example.learningjava.threading;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ConsoleColor {
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    BLUE("\u001B[34m"),
    CYAN("\u001B[36m");

    private String code;
}
