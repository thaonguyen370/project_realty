package com.example.realtyservice.controller;

import com.df.commonmodel.exceptions.CustomException;
import com.df.commonmodel.exceptions.FactoryException;
import com.df.commonmodel.models.ApiResponse;

import com.example.realtydto.dto.RecruitmentDto;
import com.example.realtyservice.service.RecruitmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@Api(tags = "Recruitment")
public class RecruitmentController {
    @Autowired
    private RecruitmentService recruitmentService;

    @ApiOperation(value = "Save Recruitment")
    @PostMapping(value = "/recruitments")
    public ResponseEntity<RecruitmentDto> saveNews(@RequestBody RecruitmentDto recruitmentDto) {
        try {
            return new ApiResponse(recruitmentService.save(recruitmentDto), HttpStatus.OK).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage(), new FactoryException()).build();
        }
    }

    @ApiOperation(value = "Find all Recruitment")
    @GetMapping(value = {"/recruitments","/test/recruitments"})
    public ResponseEntity<List<RecruitmentDto>> findAllRecruitment() {

        try {
            return new ApiResponse<>(recruitmentService.loadAllRecruitment(), HttpStatus.OK).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage(), new FactoryException()).build();
        }

    }

    @ApiOperation(value = "Delete Recruitment")
    @DeleteMapping(value = "/recruitments/{id}")
    public ResponseEntity deleteRecruitment(@PathVariable Integer id) {
        try {
            return new ApiResponse<>(recruitmentService.deleteByID(id), HttpStatus.OK).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage(), new FactoryException()).build();
        }
    }

    @ApiOperation(value = "Update Recruitment")
    @PutMapping(value = "/recruitments")
    public ResponseEntity<RecruitmentDto> updateNew(@RequestBody RecruitmentDto recruitmentDto) {
        try {
            return new ApiResponse(recruitmentService.updateByID(recruitmentDto), HttpStatus.OK).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage(), new FactoryException()).build();
        }

    }

    @ApiOperation(value = "Find Recruitment by id")
    @GetMapping(value = {"/recruitments/{id}","/test/recruitments/{id}"})
    public ResponseEntity<RecruitmentDto> findByID(@PathVariable Integer id) {
        try {
            return new ApiResponse(recruitmentService.findById(id), HttpStatus.OK).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage(), new FactoryException()).build();
        }

    }


    @ApiOperation(value = "load page Recruitment by pageNumer and pageSize")
    @GetMapping(value = {"/recruitments/list","/test/recruitments/list"})
    public ResponseEntity<Page<RecruitmentDto>> loadPage(@RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            return new ApiResponse(recruitmentService.loadPage(pageNumber, pageSize), HttpStatus.OK).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage(), new FactoryException()).build();
        }


    }

    @ApiOperation(value = "Find Recruitment by id")
    @DeleteMapping(value = "/recruitments/deleteAll")
    public ResponseEntity deleteAll() {
        try {
            return new ApiResponse(recruitmentService.deleteAll(), HttpStatus.OK).build();
        } catch (CustomException e) {
            return new ApiResponse(e.getCode(), e.getMessage(), new FactoryException()).build();
        }

    }

}
