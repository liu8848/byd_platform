package com.platform.service.auditPlan;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.platform.constant.ContentTypeConstant;
import com.platform.constant.FilePathConstant;
import com.platform.dto.auditPlan.AuditPlanCreateDTO;
import com.platform.dto.auditPlan.AuditPlanPageQueryDTO;
import com.platform.dto.auditPlan.AuditPlanUpdateDTO;
import com.platform.entity.AuditPlan;
import com.platform.exception.BaseException;
import com.platform.exception.QueryNotFoundException;
import com.platform.mapper.AuditPlanMapper;
import com.platform.result.PageResult;
import com.platform.result.UpdateResult;
import com.platform.utils.files.FileUploadUtils;
import com.platform.utils.files.FileUtils;
import com.platform.vo.auditPlan.AuditPlanDisplayVO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    /**
     * 通过主键获取审核方案信息
     * @param id 主键id
     * @return 审核方案
     */
    @Override
    public AuditPlanDisplayVO getById(Long id) {
        AuditPlanPageQueryDTO query = new AuditPlanPageQueryDTO();
        query.setId(id);
        Page<AuditPlanDisplayVO> VOS = auditPlanMapper.queryPageAuditPlan(query);
        if(VOS.isEmpty()){
            throw new QueryNotFoundException("未查找到编号为{"+id+"}的审核方案");
        }
        return VOS.get(0);
    }

    /***
     * 通过主键Id删除审核方案
     * @param id 主键
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        //检索对应审核方案
        AuditPlan auditPlan = auditPlanMapper.selectById(id);
        if(auditPlan==null){
            throw new  QueryNotFoundException("编号{"+id+"} 审核方案不存在");
        }
        //删除审核方案
        try{
            auditPlanMapper.deleteById(id);
        }catch (Exception ex){
            throw new BaseException(ex.getMessage());
        }
        //删除对应文件
        FileUtils.deleteFile(auditPlan.getPdfPath());
        FileUtils.deleteFile(auditPlan.getWordPath());
    }

    /***
     * 更新审核方案
     * @param id 主键
     * @param updateDTO 更新数据模型
     * @return 更新结果
     */
    @Override
    @Transactional
    public UpdateResult<AuditPlan> updateAuditPlan(Long id, AuditPlanUpdateDTO updateDTO) {
        //按id过去审核方案信息
        AuditPlan oldA = auditPlanMapper.selectById(id);
        if(oldA==null){
            throw new QueryNotFoundException("没有对应的审核方案");
        }
        //准备更新数据
        AuditPlan newA = new AuditPlan();
        newA.setId(id);
        newA.setFileName(updateDTO.getFileName());
        newA.setPublishTime(updateDTO.getPublishTime());

        //处理文件上传
        MultipartFile wordFile = updateDTO.getWordFile();
        MultipartFile pdfFile = updateDTO.getPdfFile();
        String newWordPath = null;
        String newPdfPath=null;
        try {
            if(!wordFile.isEmpty()){
                newA.setWordName(wordFile.getOriginalFilename());
                newWordPath=fileUploadUtils.upload(FilePathConstant.AUDIT_PLAN,wordFile);
                newA.setWordPath(newWordPath);
            }
            if (!pdfFile.isEmpty()){
                newA.setPdfName(pdfFile.getOriginalFilename());
                newPdfPath=fileUploadUtils.upload(FilePathConstant.AUDIT_PLAN,pdfFile);
                newA.setPdfPath(newPdfPath);
            }

            LambdaUpdateWrapper<AuditPlan> updateWrapper = new LambdaUpdateWrapper<>();



        }catch (IOException e){
            throw new RuntimeException(e);
        }catch (Exception ex){
            if(!ObjectUtils.isEmpty(newWordPath)){
                FileUtils.deleteFile(newWordPath);
            }
            if(!ObjectUtils.isEmpty(newPdfPath)){
                FileUtils.deleteFile(newPdfPath);
            }
        }


        return null;
    }
}
