package com.example.realtyservice.service.impl;

import com.df.commonmodel.exceptions.CustomException;
import com.example.realtydto.dto.ImageDto;
import com.example.realtyservice.service.ProcessFileService;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

@Service
public class ProcessFileServiceImpl implements ProcessFileService {


    //   @Value("${google.service_account_email}")
    private String serviceAccountEmail="oauth2@oauth2-282307.iam.gserviceaccount.com";

    //   @Value("${google.application_name}")
    private String applicationName="oauth2";

    //   @Value("${google.service_account_key}")
    private String serviceAccountKey="oauth2-282307-8aad7fd17879.p12";

    //   @Value("${google.folder_id}")
    private String folderID="1Wxp1XUtYMGiiFU8RgFs7IfB_0EZk-kAd";

    @Override
    public ImageDto uploadFile(MultipartFile file) throws CustomException {
        try {
            String mimeType="image/jpg";
            String fileName=file.getName();
            com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
            fileMetadata.setMimeType(mimeType);
            fileMetadata.setName(fileName);
            fileMetadata.setParents(Collections.singletonList(folderID));
            com.google.api.client.http.FileContent fileContent = new FileContent(mimeType, convertToFile(file));
            com.google.api.services.drive.model.File file1= getDriveService().files().create(fileMetadata, fileContent)
                    .setFields("id,webContentLink,webViewLink").execute();
            System.out.println("get view: "+file1.getWebViewLink());
            System.out.println("get download: "+file1.getWebContentLink());
            ImageDto dto=new ImageDto();
            dto.setName(fileName);
            dto.setLinkDownload(file1.getWebContentLink());
            dto.setLinkView(file1.getWebViewLink());
            return dto;
        } catch (Exception e) {
            System.out.println(e);
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @Override
    public Byte[] dowloadFile(String url) throws CustomException {
        return new Byte[0];
    }

    public Drive getDriveService() {
        Drive service = null;
        try {

            URL resource = ProcessFileServiceImpl.class.getResource("/" + this.serviceAccountKey);
            java.io.File key = Paths.get(resource.toURI()).toFile();
            HttpTransport httpTransport = new NetHttpTransport();
            JacksonFactory jsonFactory = new JacksonFactory();

            GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport)
                    .setJsonFactory(jsonFactory).setServiceAccountId(serviceAccountEmail)
                    .setServiceAccountScopes(Collections.singleton(DriveScopes.DRIVE))
                    .setServiceAccountPrivateKeyFromP12File(key).build();
            service = new Drive.Builder(httpTransport, jsonFactory, credential).setApplicationName(applicationName)
                    .setHttpRequestInitializer(credential).build();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);

        }
        return service;

    }
    public File convertToFile(MultipartFile file) {
        File file1 = new File(file.getOriginalFilename());

        try (OutputStream os = new FileOutputStream(file1)) {
            os.write(file.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file1;
    }
}
