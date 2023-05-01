package com.example.realtyservice.mapper;

import com.example.realtydto.dto.RecruitmentDto;
import com.example.realtyservice.entity.Recruitment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecruitmentMapper {
    RecruitmentDto recruitmentToRecruitmentDto(Recruitment recruitment);

    Recruitment recruitmentDtoToRecruitment(RecruitmentDto recruitmentDto);
}
