package com.example.realtyservice.service;

import com.df.commonmodel.exceptions.CustomException;
import com.example.realtydto.dto.NewsDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsService {
    NewsDto save(NewsDto newsDto) throws CustomException;

    Boolean deleteByID(Integer id) throws CustomException;

    NewsDto updateByID(NewsDto newsDto) throws CustomException;

    List<NewsDto> loadAllNews() throws CustomException;

    NewsDto findById(int id) throws CustomException;

    Page<NewsDto> loadPage(int page, int size) throws CustomException;

    Boolean deleteAll() throws CustomException;

    List<NewsDto> sixNews() throws CustomException;

    byte[] downloadFile(String source) throws CustomException;
}
