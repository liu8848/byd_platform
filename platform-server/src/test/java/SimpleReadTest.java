import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.platform.PlatformApplication;
import com.platform.commonModel.Dictionary;
import com.platform.dto.FactoryContact.FactoryContactCreateDTO;
import com.platform.dto.auditors.AuditorCreateDTO;
import com.platform.utils.excelHandlers.DownListSheetWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void test(){

        List<Dictionary> list=new ArrayList<>();
        EasyExcel.write("/home/qzliu/桌面/test.xlsx", FactoryContactCreateDTO.class)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet("测试")
                .registerWriteHandler(new DownListSheetWriteHandler())
                .doWrite(list);
    }
}
