package view;

import model.RevenueObserver;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TotalRevenueViewTest {

    @Test
    public void testTotalRevenueAccumulatesCorrectly() {
        RevenueObserver observer = new TotalRevenueView();

        // Redirect System.out
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Simulera flera betalningar
        observer.newPayment(100.0);
        observer.newPayment(50.0);
        observer.newPayment(25.0);

        // Kontrollera terminalutskrift
        String consoleOutput = output.toString();
        assertTrue(consoleOutput.contains(">> Total försäljning hittills: 175.0 kr"));

        // Återställ System.out
        System.setOut(System.out);
    }
}
