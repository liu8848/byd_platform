package com.platform.vo;

import com.platform.entity.Auditor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "在职审核员台账展示模型")
public class OnWorkAuditorDisplayVO implements Serializable {
    private boolean isMatch;
    private LocalDateTime warnTime;
    private int level;
    private String buName;
    private String recordFactoryName;
    private List<AuditorDisplayVO> auditorVOList;
    private Map<String,Long> auditorLevelCnt;

    public static boolean match(Map<String, Long> cntMap,int level){


        switch (level){
            case 5:
                if((cntMap.getOrDefault("SA",0L)>=2)
                &&(cntMap.getOrDefault("A",0L)+cntMap.getOrDefault("PA",0L)>=7))
                    return true;
                break;
            case 4:
                if((cntMap.getOrDefault("SA",0L)>=1)
                        &&(cntMap.getOrDefault("A",0L)+cntMap.getOrDefault("PA",0L)>=5))
                    return true;
                break;
            case 3:
                if(cntMap.getOrDefault("SA",0L)+cntMap.getOrDefault("A",0L)>=4)
                    return true;
                break;
            case 2, 1:
                if((cntMap.getOrDefault("SA",0L)+cntMap.getOrDefault("A",0L)>=2)
                        &&(cntMap.getOrDefault("A",0L)+cntMap.getOrDefault("PA",0L)
                        +cntMap.getOrDefault("PA",0L)>=4))
                    return true;
                break;
            default:
                return true;
        }
        return false;
    }
}
