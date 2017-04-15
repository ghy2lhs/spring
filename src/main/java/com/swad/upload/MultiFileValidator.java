package com.swad.upload;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by gonghaiyu on 27/03/2017.
 */
@Component
public class MultiFileValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return MultiFileBucket.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MultiFileBucket multiBucket = (MultiFileBucket) o;

        int index=0;

        for(FileBucket file : multiBucket.getFiles()){
            if(file.getFile()!=null){
                if (file.getFile().getSize() == 0) {
                    errors.rejectValue("files["+index+"].file", "missing.file");
                }
            }
            index++;
        }

    }


}
