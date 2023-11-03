package com.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.platform.enums.AuditorLevel;
import com.platform.enums.LevelMatch;
import com.platform.vo.AuditorDisplayVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "auditor_standing_book_in_work")
public class AuditorStandingBookInWork implements Serializable {
    private Long id;
    private Long buId;
    private Long recordFactoryId;
    private LevelMatch levelMatch;
    private int level;
    private long sa;
    private long a;
    private long pa;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime warnTime;


    private List<AuditorDisplayVO> auditors;


    public static boolean match(Map<Integer, Long> cntMap, int level){


        switch (level){
            case 5:
                if((cntMap.getOrDefault(AuditorLevel.SA,0L)>=2)
                        &&(cntMap.getOrDefault(AuditorLevel.A,0L)+cntMap.getOrDefault(AuditorLevel.PA,0L)>=7))
                    return true;
                break;
            case 4:
                if((cntMap.getOrDefault(AuditorLevel.SA,0L)>=1)
                        &&(cntMap.getOrDefault(AuditorLevel.A,0L)+cntMap.getOrDefault(AuditorLevel.PA,0L)>=5))
                    return true;
                break;
            case 3:
                if(cntMap.getOrDefault(AuditorLevel.SA,0L)+cntMap.getOrDefault(AuditorLevel.A,0L)>=4)
                    return true;
                break;
            case 2, 1:
                if((cntMap.getOrDefault(AuditorLevel.SA,0L)+cntMap.getOrDefault(AuditorLevel.A,0L)>=2)
                        &&(cntMap.getOrDefault(AuditorLevel.A,0L)+cntMap.getOrDefault(AuditorLevel.PA,0L)
                        +cntMap.getOrDefault(AuditorLevel.PA,0L)>=4))
                    return true;
                break;
            default:
                return true;
        }
        return false;
    }
}
