//importing libraries
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;

//fileTest
public class fileTest {

    @Test
    public void sampleTest() {

        //making object
        Spider sp = new Spider();

        File tmpFile = new File("No_actual_directory_a_random_test");

        //testing
        assertEquals(false, sp.crawler(tmpFile));

    }
}