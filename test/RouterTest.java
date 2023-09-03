package test;

import org.junit.Test;
import src.City;
import src.LogisticsController;
import src.Route;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RouterTest {
    @Test
    public void testRouter(){
        LogisticsController testController = new LogisticsController();

        testController.load("data/routes.csv", "data/planes.csv");
        HashMap<String, List<Route>> results = testController.findRoutes();
        assertEquals(results.size(), 1);
        assertEquals(results.get("N145QA").size(), 3);
        assertEquals(testController.getUnfilledRoutes().size(), 0);

        testController.load("data/routes1.csv", "data/planes1.csv");
        HashMap<String, List<Route>> results1 = testController.findRoutes();
        assertEquals(results1.size(), 2);
        assertEquals(results1.get("N275SY").size(), 5);
        assertEquals(results1.get("N1111").size(), 2);
        assertEquals(testController.getUnfilledRoutes().size(), 0);

        testController.load("data/routes2.csv", "data/planes2.csv");
        HashMap<String, List<Route>> results2 = testController.findRoutes();
        assertEquals(results2.size(), 1);
        assertEquals(results2.get("N275SY").size(), 0);
        assertEquals(testController.getUnfilledRoutes().size(), 2);

        testController.load("data/hugeRoutes.csv", "data/hugePlanes.csv");
        HashMap<String, List<Route>> results3 = testController.findRoutes();
        assertEquals(results3.size(), 9);
        assertEquals(results3.get("N13011").size(), 2);
        assertEquals(results3.get("N1111").size(), 4);
        assertEquals(results3.get("N1112").size(), 4);
        assertEquals(results3.get("N1113").size(), 4);
        assertEquals(results3.get("N1114").size(), 4);
        assertEquals(results3.get("N1115").size(), 4);
        assertEquals(results3.get("N1116").size(), 4);
        assertEquals(results3.get("N1117").size(), 5);
        assertEquals(results3.get("N1118").size(), 4);
        assertEquals(testController.getUnfilledRoutes().size(),
                1739 - (2 + (4 * 7) + 5));
    }
}
