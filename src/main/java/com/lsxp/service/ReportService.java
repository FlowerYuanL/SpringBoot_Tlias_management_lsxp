package com.lsxp.service;

import com.lsxp.pojo.GenderOption;
import com.lsxp.pojo.JobOption;

import java.util.List;

public interface ReportService {
    JobOption getEmpJobData();

    List<GenderOption> getEmpGenderData();
}
