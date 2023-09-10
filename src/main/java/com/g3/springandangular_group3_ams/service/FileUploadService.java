package com.g3.springandangular_group3_ams.service;

import com.g3.springandangular_group3_ams.model.entity.Image;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileUploadService {

    Image uploadFile(MultipartFile file) throws IOException;
    List<Image> uploadFileMultipleFile(List<MultipartFile> file) throws IOException;

    Resource load(String filename);


}
