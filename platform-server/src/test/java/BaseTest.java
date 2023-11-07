import com.platform.PlatformApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PlatformApplication.class)
public class BaseTest {
    @Test
    public void helloTest(){
        System.out.println("hello");
    }


    @Test
    public void testExcelRead(){

    }

}
