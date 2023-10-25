package com.platform.mapper;

import com.platform.annotaionExtend.AutoFill;
import com.platform.dto.auditors.AuditorInspectionCreateDTO;
import com.platform.entity.Auditor;
import com.platform.entity.AuditorStandingBookInWork;
import com.platform.enums.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuditorMapper {
    List<Auditor> getAuditor();

    int countAuditorById(long employeeId);


    @AutoFill(value = OperationType.INSERT)
    void insert(Auditor auditor);

    void deleteAuditorInspectionByEmployeeId(Long employeeId);

    void insertAuditorInspection(List<AuditorInspectionCreateDTO> inspectionList);

    AuditorStandingBookInWork getStandingBookInWorkByRecordFactoryId(Long recordFactoryId);

    List<Auditor> getAuditorByRecordFactoryId(Long recordFactoryId);

    void insertAuditorStandingBook(AuditorStandingBookInWork asibw);

    void updateAuditorStandingBook(AuditorStandingBookInWork asibw);

    List<AuditorStandingBookInWork> getStandingBookInWork();
}
