package com.platform.mapper;

import com.github.pagehelper.Page;
import com.platform.annotaionExtend.AutoFill;
import com.platform.annotaionExtend.DictHelper;
import com.platform.annotaionExtend.DictParam;
import com.platform.constant.RedisKeyConstant;
import com.platform.dto.auditors.AuditorInspectionCreateDTO;
import com.platform.dto.auditors.AuditorPageQueryDTO;
import com.platform.entity.Auditor;
import com.platform.entity.AuditorStandingBookInWork;
import com.platform.enums.OperationType;
import com.platform.vo.AuditorDisplayVO;
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

    @DictHelper(value = {
            @DictParam(field = "factoryId", targetField = "factoryName", dictType = RedisKeyConstant.FACTORY),
            @DictParam(field = "departmentId", targetField = "departmentName", dictType = RedisKeyConstant.DEPARTMENT)
    })
    List<AuditorDisplayVO> getAuditorDisplayVO(Long record_factory_id);

    Page<AuditorStandingBookInWork> getPageQueryStandingBookInWork(AuditorPageQueryDTO pageQueryDTO);

    Auditor getAuditorByEmployeeId(Long employeeId);

    void deleteByEmployeeId(Long employeeId);

    void updateAuditorArrangement(Long employeeId, Boolean isArrange);


    @AutoFill(value = OperationType.INSERT)
    void importAuditors(List<Auditor> auditors);
}
