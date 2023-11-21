package com.platform.service.auditPlan;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.platform.constant.ContentTypeConstant;
import com.platform.constant.FilePathConstant;
import com.platform.dto.auditPlan.AuditPlanCreateDTO;
import com.platform.dto.auditPlan.AuditPlanPageQueryDTO;
import com.platform.entity.AuditPlan;
import com.platform.exception.BaseException;
import com.platform.mapper.AuditPlanMapper;
import com.platform.result.PageResult;
import com.platform.utils.FileUploadUtils;
import com.platform.vo.auditPlan.AuditPlanDisplayVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class AuditServiceImpl implements AuditPlanService{

    @Autowired
    private AuditPlanMapper auditPlanMapper;
    @Autowired
    private FileUploadUtils fileUploadUtils;

    /***
     * 创建审核方案
     * @param fileMap 上传的文件HashMap，key为文件类型，value为所上传文件，必须包含Word和PDF
     * @param createDTO 审核方案数据模型
     * @return 创建的审核方案记录
     * @throws IOException IO错误
     */
    @Override
    public AuditPlan createAuditPlan(Map<String, List<MultipartFile>> fileMap, AuditPlanCreateDTO createDTO) throws IOException {
        //查询是否存在该审核方案
        QueryWrapper<AuditPlan> wrapper = new QueryWrapper<>();
        wrapper.eq("file_name",createDTO.getFileName());
        AuditPlan oldPlan = auditPlanMapper.selectOne(wrapper);
        if(oldPlan!=null){
            throw new BaseException("审核方案：{"+createDTO.getFileName()+"} 已存在,无法重复创建，请更新或删除");
        }

        //创建上传对象AuditPlan
        AuditPlan auditPlan = new AuditPlan();
        BeanUtils.copyProperties(createDTO,auditPlan);
        auditPlan.setPublishTime(LocalDateTime.parse(createDTO.getPublishTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        //处理Word文件
        MultipartFile wordFile = (fileMap.getOrDefault(ContentTypeConstant.DOC_CONTENT_TYPE, null) == null ?
                fileMap.get(ContentTypeConstant.DOCX_CONTENT_TYPE) : fileMap.get(ContentTypeConstant.DOC_CONTENT_TYPE)).get(0);
        String wordName = wordFile.getOriginalFilename();
        String wordPath = fileUploadUtils.upload(FilePathConstant.AUDIT_PLAN, wordFile);
        auditPlan.setWordPath(wordPath);
        auditPlan.setWordName(wordName);

        //处理PDF文件
        List<MultipartFile> files = fileMap.getOrDefault(ContentTypeConstant.PDF_CONTENT_TYPE, null);
        if (files==null){
            throw new IOException("文件丢失");
        }
        MultipartFile pdfFile = files.get(0);
        String pdfName = pdfFile.getOriginalFilename();
        String pdfPath = fileUploadUtils.upload(FilePathConstant.AUDIT_PLAN, pdfFile);
        auditPlan.setPdfName(pdfName);
        auditPlan.setPdfPath(pdfPath);

        //数据库添加审核方案记录
        auditPlanMapper.create(auditPlan);

        return auditPlan;
    }

    /***
     *
     * @param pageQueryDTO 分页检索条件模型
     * @return 查询结果
     */
    @Override
    public PageResult<AuditPlanDisplayVO> queryPage(AuditPlanPageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getSize());

        Page<AuditPlanDisplayVO>voPage= auditPlanMapper.queryPageAuditPlan(pageQueryDTO);

        long total = voPage.getTotal();
        List<AuditPlanDisplayVO> result = voPage.getResult();

        return new PageResult<>(total,result);
    }
}
