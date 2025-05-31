package view;

import model.RevenueObserver;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tester för TotalRevenueView-klassen.
 */
public class TotalRevenueViewTest {

    /**
     * Testar att total försäljning summeras korrekt och skrivs ut i terminalen.
     */
    @Test
    public void testTotalRevenueAccumulatesCorrectly() {
        RevenueObserver observer = new TotalRevenueView();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));

        try {
            observer.newPayment(100.0);
            observer.newPayment(50.0);
            observer.newPayment(25.0);

            String consoleOutput = output.toString();
            String[] outputLines = consoleOutput.split("\\r?\\n");

            boolean found = false;
            for (String line : outputLines) {
                if (line.contains("Total försäljning hittills:") && line.contains("175")) {
                    found = true;
                    break;
                }
            }

            assertTrue(found, "Total försäljning ska summeras korrekt och skrivas ut till terminalen.");
        } finally {
            System.setOut(originalOut);
        }
    }
}
