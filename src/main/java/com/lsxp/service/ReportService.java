package com.lsxp.service;

import com.lsxp.pojo.DegreeOption;
import com.lsxp.pojo.GenderOption;
import com.lsxp.pojo.JobOption;
import com.lsxp.pojo.StudentOption;

import java.util.List;

public interface ReportService {
    JobOption getEmpJobData();

    List<GenderOption> getEmpGenderData();

    StudentOption getStudentCountData();

    List<DegreeOption> getStudentDegreeData();
}
