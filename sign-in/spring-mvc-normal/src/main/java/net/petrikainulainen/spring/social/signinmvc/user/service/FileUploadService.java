package net.petrikainulainen.spring.social.signinmvc.user.service;

import net.petrikainulainen.spring.social.signinmvc.user.model.FileUpload;
import net.petrikainulainen.spring.social.signinmvc.user.repository.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 03.10.2016.
 */

@Service
public class FileUploadService {

    @Autowired
    FileUploadRepository fileUploadRepository;

    // Retrieve file
    public FileUpload findByFilename(String filename) {
        return fileUploadRepository.findByFilename(filename);
    }

    // Upload the file
    public void uploadFile(FileUpload doc) {
        fileUploadRepository.saveAndFlush(doc);
    }
}