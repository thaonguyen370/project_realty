package com.example.realtyservice.controller;

import com.df.commonmodel.exceptions.CustomException;
import com.df.commonmodel.exceptions.FactoryException;
import com.df.commonmodel.models.ApiResponse;
import com.example.realtydto.dto.ImageDto;
import com.example.realtydto.dto.NewsDto;
import com.example.realtyservice.service.NewsService;
import com.example.realtyservice.service.ProcessFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@RestController
@Api(tags = "ProcessFile")
public class ProcessFileController {
    @Autowired
    private ProcessFileService processFileService;

    @ApiOperation(value = "upload-file")
    @PostMapping(value = "/upload-file")
    public ResponseEntity<ImageDto> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            return new ApiResponse(processFileService.uploadFile(file), HttpStatus.OK).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage(), new FactoryException()).build();
        }
    }
}
