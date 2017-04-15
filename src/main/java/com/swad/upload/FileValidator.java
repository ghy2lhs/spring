package com.swad.upload;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by gonghaiyu on 27/03/2017.
 */
@Component
public class FileValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return FileBucket.class.isAssignableFrom(clazz);
    }

    /**
     * We are using some Validators to verify that
     * user have indeed selected a file to be uploaded.
     * @param o
     * @param errors
     */
    @Override
    public void validate(Object o, Errors errors) {
        FileBucket file = (FileBucket) o;
        if(file.getFile()!=null){
            if(file.getFile().getSize()==0){
                errors.rejectValue("file","misssing.file");
            }
        }

    }
}
