package test;

import org.junit.Test;
import src.LogisticsController;

import static org.junit.Assert.assertEquals;

public class ControllerTest {
    @Test
    public void testLoad() {
        LogisticsController testController = new LogisticsController();

        testController.load("data/routes.csv", "data/planes.csv");
        assertEquals(testController.getUnfilledRoutes().size(), 3);

        testController.load("data/routes1.csv", "data/planes1.csv");
        assertEquals(testController.getUnfilledRoutes().size(), 7);

        testController.load("data/hugeRoutes.csv", "data/planes1.csv");
        assertEquals(testController.getUnfilledRoutes().size(), 1739);
    }
}
