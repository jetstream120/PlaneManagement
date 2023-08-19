package src;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        REPL<City, Route> repl = new REPL<>(new LogisticsController());
        repl.run();
    }
}