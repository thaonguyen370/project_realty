package com.example.realtyservice.service;

import com.df.commonmodel.exceptions.CustomException;
import com.example.realtydto.dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProcessFileService {
    public ImageDto uploadFile(MultipartFile file) throws CustomException;

    public Byte[] dowloadFile(String url) throws CustomException;
}
