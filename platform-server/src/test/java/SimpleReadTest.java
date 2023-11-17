import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.platform.PlatformApplication;
import com.platform.commonModel.Dictionary;
import com.platform.constant.DictKeyConstant;
import com.platform.dto.auditors.AuditorCreateDTO;
import com.platform.utils.DictUtil;
import com.platform.utils.excelHandlers.CascadeWriteHandler;
import com.platform.utils.excelHandlers.DownListSheetWriteHandler;
import com.platform.utils.excelHandlers.SelectedSheetWriteHandler;
import com.platform.vo.factoryContact.FactoryContactExcelTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        EasyExcel.write("/home/qzliu/桌面/test.xlsx", FactoryContactExcelTemplateVO.class)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet("测试")
                .registerWriteHandler(new DownListSheetWriteHandler("事业部列表", DictKeyConstant.BUSINESSDIVISION,0,"A"))
                .registerWriteHandler(new DownListSheetWriteHandler("备案工厂列表",DictKeyConstant.FACTORY,2,"C"))
                .doWrite(list);
    }

    @Test
    public void test1(){
        String fileName="/home/qzliu/桌面/测试模板"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))+".xlsx";
        Map<Integer,List<Dictionary>> selectMap=new HashMap<>();
        List<FactoryContactExcelTemplateVO> list=new ArrayList<>();
        List<Dictionary> buDictList = DictUtil.dictMap.get(DictKeyConstant.BUSINESSDIVISION).values().stream().toList();
        List<Dictionary> factoryDictList = DictUtil.dictMap.get(DictKeyConstant.FACTORY).values().stream().toList();
        selectMap.put(0,buDictList);
        selectMap.put(2,factoryDictList);


        List<String> buList = DictUtil.dictMap.get(DictKeyConstant.BUSINESSDIVISION).values().stream().map(Dictionary::getDictName).toList();
        Map<String,List<String>> siteMap=new HashMap<>();
        siteMap.put("第二十三事业部", Arrays.asList("数字中心","通信信号研究院"));
        siteMap.put("第九事业部",Arrays.asList("第五工厂","第三工厂"));
        siteMap.put("第七事业部",new ArrayList<>());

        EasyExcel.write(fileName, FactoryContactExcelTemplateVO.class)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet("test")
                .registerWriteHandler(new CascadeWriteHandler(buList,siteMap, 0, 2))
                .registerWriteHandler(new SelectedSheetWriteHandler(selectMap))
                .doWrite(list);
    }


    @Test
    public void test2(){
        File file = new File("/home/qzliu/桌面/byd_platform/./platform-server/src/main/resources/upload/AuditPlan/2023-11-17-7c3e04c8-5712-4b6e-b71b-f4c49ab91a76.doc");

        log.info("file is exists :"+file.exists());
    }
}
