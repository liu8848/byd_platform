import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import com.platform.PlatformApplication;
import com.platform.dto.auditors.AuditorCreateDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PlatformApplication.class)
@Slf4j
public class SimpleReadTest {

    @Test
    public void simpleRead(){
        String fileName="/home/qzliu/桌面/2.xlsx";

        EasyExcel.read(fileName, AuditorCreateDTO.class,new PageReadListener<AuditorCreateDTO>(dataList->{
            for (AuditorCreateDTO dto:dataList){
                log.info("读取一条数据{}", JSON.toJSONString(dto));
            }
        })).sheet().doRead();
    }
}
