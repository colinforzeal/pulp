package com.pulp.user.repository;

import com.pulp.user.model.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 03.10.2016.
 */
@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
    FileUpload findByFilename(String filename);
}

