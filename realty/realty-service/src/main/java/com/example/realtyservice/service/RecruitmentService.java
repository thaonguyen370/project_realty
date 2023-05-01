package com.example.realtyservice.service;

import com.df.commonmodel.exceptions.CustomException;
import com.example.realtydto.dto.RecruitmentDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RecruitmentService {
    RecruitmentDto save(RecruitmentDto recruitmentDto) throws CustomException;

    Boolean deleteByID(Integer id) throws CustomException;

    RecruitmentDto updateByID(RecruitmentDto newsDto) throws CustomException;

    List<RecruitmentDto> loadAllRecruitment() throws CustomException;

    RecruitmentDto findById(int id) throws CustomException;

    Page<RecruitmentDto> loadPage(int page, int size) throws CustomException;

    Boolean deleteAll() throws CustomException;
}
