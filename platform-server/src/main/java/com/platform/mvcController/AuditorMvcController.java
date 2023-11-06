package com.platform.mvcController;

import com.platform.dto.auditors.AuditorCreateDTO;
import com.platform.dto.auditors.AuditorUpdateDTO;
import com.platform.enums.AuditorLevel;
import com.platform.enums.Education;
import com.platform.service.AuditorService;
import com.platform.vo.AuditorDisplayVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mvc/auditor")
public class AuditorMvcController {
    @Autowired
    private AuditorService auditorService;

    @GetMapping("/list")
    @Operation(summary = "获取所有审核员信息")
    public String getAllAuditor(Model model) {
        List<AuditorDisplayVO> auditorAll = auditorService.getAuditorAll();
        model.addAttribute("auditorList", auditorAll);
        return "auditor_list";
    }

    @GetMapping("/{employeeId}")
    public String getInfo(@PathVariable Long employeeId, Model model) {
        AuditorDisplayVO auditorDisplayVO = auditorService.getAuditorByEmployeeId(employeeId);
        model.addAttribute("educationEnum", Education.values());
        model.addAttribute("auditorLevelEnum", AuditorLevel.values());
        model.addAttribute("auditor", auditorDisplayVO);
        return "auditor_info";
    }

    @PostMapping("/update")
    public String updateAuditor(Long employeeId,
                                AuditorUpdateDTO updateDTO,
                                Model model) {
        auditorService.getAuditorByEmployeeId(employeeId);
        return null;
    }

    @PostMapping("/add")
    public String addAuditor(AuditorCreateDTO auditorCreateDTO) {
        Long employeeId = auditorCreateDTO.getEmployeeId();
        return null;
    }

}
