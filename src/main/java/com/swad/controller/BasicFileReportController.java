package com.swad.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * Created by gonghaiyu on 26/03/2017.
 */
@RestController
public class BasicFileReportController {


    @GetMapping("/singleFileExportById/{file_id}")
    public ResponseEntity singleFileExportById(HttpServletResponse response, @PathVariable("file_id") String file_id) throws IOException {


            File file = null;
            URL xmlpath = this.getClass().getClassLoader().getResource("");
            String INTERNAL_FILE = xmlpath.getPath()+ "exportTemplate/auth_declaration.doc";
           if(file_id.equalsIgnoreCase("1")){
                ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                file = new File(classloader.getResource(INTERNAL_FILE).getFile());
            }else{
                file = new File(INTERNAL_FILE);
            }

            if(!file.exists()){
                String errorMessage = "Sorry. The file you are looking for does not exist";
                System.out.println(errorMessage);
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
                outputStream.close();
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

            String mimeType= URLConnection.guessContentTypeFromName(file.getName());
            if(mimeType==null){
                System.out.println("mimetype is not detectable, will take default");
                mimeType = "application/octet-stream";
            }

            System.out.println("mimetype : "+mimeType);

            response.setContentType(mimeType);

            /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser
            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));


            /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
            //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));

            response.setContentLength((int)file.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            //Copy bytes from source to destination(outputstream in this example), closes both streams.
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        return new ResponseEntity(HttpStatus.OK);

    }
}
