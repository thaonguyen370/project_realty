package com.example.realtyservice.controller;

import com.df.commonmodel.exceptions.CustomException;
import com.df.commonmodel.exceptions.FactoryException;
import com.df.commonmodel.models.ApiResponse;
import com.example.realtydto.dto.NewsDto;
import com.example.realtyservice.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@RestController
@Api(tags = "News")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @ApiOperation(value = "Save news")
    @PostMapping(value = "/news")
    public ResponseEntity<NewsDto> saveNews(@RequestBody NewsDto newsDto) {
        try {
            return new ApiResponse(newsService.save(newsDto), HttpStatus.CREATED).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage(), new FactoryException()).build();
        }
    }

    @ApiOperation(value = "Find all news")
    @GetMapping(value = {"/news", "/test/news"})
    public ResponseEntity<List<NewsDto>> findAllNews() {

        try {
            return new ApiResponse<>(newsService.loadAllNews(), HttpStatus.OK).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage(), new FactoryException()).build();
        }

    }

    @ApiOperation(value = "Delete news")
    @DeleteMapping(value = "/news/{id}")
    public ResponseEntity deleteScanner(@PathVariable Integer id) {
        try {
            return new ApiResponse<>(newsService.deleteByID(id), HttpStatus.OK).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage(), new FactoryException()).build();
        }
    }

    @ApiOperation(value = "Update news")
    @PutMapping(value = "/news")
    public ResponseEntity<NewsDto> updateNew(@RequestBody NewsDto newsDto) {
        try {
            return new ApiResponse(newsService.updateByID(newsDto), HttpStatus.OK).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage(), new FactoryException()).build();
        }

    }

    @ApiOperation(value = "Find news by id")
    @GetMapping(value = {"/news/{id}", "test/news/{id}"})
    public ResponseEntity<NewsDto> findByID(@PathVariable Integer id) {
        try {
            return new ApiResponse(newsService.findById(id), HttpStatus.OK).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage(), new FactoryException()).build();
        }

    }


    @ApiOperation(value = "load page news by pageNumer and pageSize")
    @GetMapping(value = {"/news/list","test/news/list"})
    public ResponseEntity<Page<NewsDto>> loadPage(@RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            return new ApiResponse(newsService.loadPage(pageNumber, pageSize), HttpStatus.OK).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage(), new FactoryException()).build();
        }


    }

    @ApiOperation(value = "Find news by id")
    @DeleteMapping(value = "/news/deleteAll")
    public ResponseEntity deleteAll() {
        try {
            return new ApiResponse(newsService.deleteAll(), HttpStatus.OK).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage(), new FactoryException()).build();
        }

    }

    @ApiOperation(value = "six news")
    @GetMapping(value = {"/news/sixNews", "test/sixNews"})
    public ResponseEntity<List<NewsDto>> sixNews() {
        try {
            return new ApiResponse(newsService.sixNews(), HttpStatus.OK).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage(), new FactoryException()).build();
        }

    }


    @ApiOperation(value = "download file from ftp server")
    @GetMapping("test/ftp/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam(defaultValue = "") String source) {
        try {
            byte[] file = newsService.downloadFile(source);
            String filename = FilenameUtils.getName(source);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .body(file);
        } catch (CustomException c) {
            return ResponseEntity.notFound().build();
        }
    }
}
