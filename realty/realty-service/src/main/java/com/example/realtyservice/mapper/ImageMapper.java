package com.example.realtyservice.mapper;

import com.example.realtydto.dto.ImageDto;
import com.example.realtydto.dto.NewsDto;
import com.example.realtyservice.entity.Images;
import com.example.realtyservice.entity.News;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageDto entityToDto(Images images);

    Images dtoToEntity(ImageDto imageDto);
}
