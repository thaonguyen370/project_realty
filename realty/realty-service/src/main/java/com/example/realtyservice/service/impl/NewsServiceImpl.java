package com.example.realtyservice.service.impl;

import com.df.commonmodel.exceptions.CustomException;
import com.df.commonmodel.exceptions.ErrorCode;
import com.example.realtydto.dto.NewsDto;
import com.example.realtyservice.entity.News;
import com.example.realtyservice.mapper.NewsMapper;
import com.example.realtyservice.repository.NewsRepository;
import com.example.realtyservice.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.RollbackException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(rollbackFor = CustomException.class)
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public NewsDto save(NewsDto newsDto) throws CustomException {
        try {
            if (CollectionUtils.isEmpty(newsDto.getImages())) {
                throw new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.IMAGE_NOT_EXISTED.toString());
            }
            News news = newsMapper.newsDtoToNews(newsDto);
            return newsMapper.newsToNewsDto(newsRepository.save(news));
        }catch (CustomException c){
            throw c;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.ERROR_UNEXPECTED.toString());
        }
    }


    @Override
    public Boolean deleteByID(Integer id) throws CustomException {
        try {
            News news = newsRepository.findById(id).orElse(null);
            return false;
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.ERROR_UNEXPECTED.toString());
        }
    }


    @Override
    public NewsDto updateByID(NewsDto newsDto) throws CustomException {
        try {
            News gantry = newsRepository.findById(newsDto.getId()).orElse(null);
            if (gantry == null) {
                throw new CustomException(HttpStatus.NOT_FOUND, "news not found!");
            }
            return newsMapper.newsToNewsDto(newsRepository.save(newsMapper.newsDtoToNews(newsDto)));

        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.ERROR_UNEXPECTED.toString());
        }
    }


    @Override
    public List<NewsDto> loadAllNews() throws CustomException {
        try {
            return newsRepository.findAll().stream().map(newsMapper::newsToNewsDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.ERROR_UNEXPECTED.toString());
        }
    }

    @Override
    public NewsDto findById(int id) throws CustomException {
        try {
            return newsMapper.newsToNewsDto(newsRepository.findById(id).orElse(null));
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.ERROR_UNEXPECTED.toString());

        }
    }


    @Override
    public Page<NewsDto> loadPage(int page, int size) throws CustomException {
        try {
            Pageable temp = PageRequest.of(page, size, Sort.by("id"));
            return newsRepository.findAll(temp).map(newsMapper::newsToNewsDto);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.ERROR_UNEXPECTED.toString());
        }
    }

    @Transactional
    @Override
    public Boolean deleteAll() throws CustomException {
        try {
            List<News> newsList = newsRepository.findAll();
            newsRepository.deleteAll();
            return true;
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.ERROR_UNEXPECTED.toString());
        }
    }

    @Override
    public List<NewsDto> sixNews() throws CustomException {
        try {
            Pageable temp = PageRequest.of(0, 6, Sort.by("updatedDate"));
            return newsRepository.findAll(temp).stream().map(newsMapper::newsToNewsDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.ERROR_UNEXPECTED.toString());
        }

    }

    @Override
    public byte[] downloadFile(String source) throws CustomException {
        try {
            return FileUtils.readFileToByteArray(new File(source));
        } catch (Exception e) {
            return null;
        }

    }

}
