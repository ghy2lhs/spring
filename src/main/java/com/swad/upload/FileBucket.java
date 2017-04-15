package com.swad.upload;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by gonghaiyu on 27/03/2017.
 */
public class FileBucket {

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    MultipartFile file;

}
