package test;

import org.junit.Test;
import src.LogisticsController;

public class ControllerTest {
    @Test
    public void testLoad() {
        LogisticsController testController = new LogisticsController();
        testController.load("data/routes1.csv", "data/planes1.csv");
    }
}
