import org.junit.Test;

import java.io.IOException;

public class SolverTest {

    Partenza partenza = new Partenza();

    @Test(timeout = 181000)
    public void ch130() throws IOException {
        partenza.doIt(0);
    }

    @Test(timeout = 181000)
    public void d198() throws IOException {
        partenza.doIt(1);
    }

    @Test(timeout = 181000)
    public void eil76()  throws IOException {
        partenza.doIt(3);
    }

    @Test(timeout = 181000)
    public void fl1577()  throws IOException {
        partenza.doIt(7);
    }

    @Test(timeout = 181000)
    public void kroA100() throws IOException {
        partenza.doIt(3);
    }

    @Test(timeout = 181000)
    public void lin318() throws IOException {
        partenza.doIt(4);
    }

    @Test(timeout = 181000)
    public void pcb442() throws IOException {
        partenza.doIt(6);
    }

    @Test(timeout = 181000)
    public void pr439()  throws IOException {
        partenza.doIt(5);
    }

    @Test(timeout = 181000)
    public void rat783()  throws IOException {
        partenza.doIt(9);
    }

    @Test(timeout = 181000)
    public void u1060()  throws IOException {
        partenza.doIt(8);
    }
}

