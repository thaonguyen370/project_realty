package com.example.realtyservice.service.impl;

import com.df.commonmodel.exceptions.CustomException;
import com.df.commonmodel.exceptions.ErrorCode;
import com.example.realtydto.dto.RecruitmentDto;
import com.example.realtyservice.entity.Recruitment;
import com.example.realtyservice.mapper.RecruitmentMapper;

import com.example.realtyservice.repository.RecruitmentRepository;
import com.example.realtyservice.service.RecruitmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RecruitmentImpl implements RecruitmentService {
    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Autowired
    private RecruitmentMapper recruitmentMapper;

    @Override
    public RecruitmentDto save(RecruitmentDto recruitmentDto) throws CustomException {
        try {
            return recruitmentMapper.recruitmentToRecruitmentDto(recruitmentRepository.save(recruitmentMapper.recruitmentDtoToRecruitment(recruitmentDto)));
        }catch (Exception e){
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.ERROR_UNEXPECTED.toString());
        }
    }

    @Override
    public Boolean deleteByID(Integer id) throws CustomException {
        Recruitment recruitment=recruitmentRepository.findById(id).orElse(null);
        if (recruitment==null){
            throw new CustomException(HttpStatus.NOT_FOUND, ErrorCode.ERROR_NOT_FOUND.toString());
        }
        recruitmentRepository.deleteById(id);
        return true;
    }

    @Override
    public RecruitmentDto updateByID(RecruitmentDto recruitmentDto) throws CustomException {
        Recruitment recruitment=recruitmentRepository.findById(recruitmentDto.getId()).orElse(null);
        if (recruitment==null){
            throw new CustomException(HttpStatus.NOT_FOUND, ErrorCode.ERROR_NOT_FOUND.toString());
        }
        return recruitmentMapper.recruitmentToRecruitmentDto(recruitmentRepository.
                save(recruitmentMapper.recruitmentDtoToRecruitment(recruitmentDto)));
    }

    @Override
    public List<RecruitmentDto> loadAllRecruitment() throws CustomException {
        try {
            return recruitmentRepository.findAll().stream().map(recruitmentMapper::recruitmentToRecruitmentDto).collect(Collectors.toList());
        }catch (Exception e){
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.ERROR_UNEXPECTED.toString());

        }
    }

    @Override
    public RecruitmentDto findById(int id) throws CustomException {
        Recruitment recruitment=recruitmentRepository.findById(id).orElse(null);
        if (recruitment==null){
            throw new CustomException(HttpStatus.NOT_FOUND, ErrorCode.ERROR_NOT_FOUND.toString());
        }
        return recruitmentMapper.recruitmentToRecruitmentDto(recruitment);
    }

    @Override
    public Page<RecruitmentDto> loadPage(int page, int size) throws CustomException {
        try {
            Pageable temp = PageRequest.of(page, size, Sort.by("id"));
            return recruitmentRepository.findAll(temp).map(recruitmentMapper::recruitmentToRecruitmentDto);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.ERROR_UNEXPECTED.toString());
        }
    }

    @Override
    public Boolean deleteAll() throws CustomException {
        try {
            recruitmentRepository.deleteAll();
            return true;
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.ERROR_UNEXPECTED.toString());
        }
    }
}
