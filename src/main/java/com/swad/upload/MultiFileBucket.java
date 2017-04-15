package com.swad.upload;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gonghaiyu on 27/03/2017.
 */
public class MultiFileBucket {
    List<FileBucket> files = new ArrayList<FileBucket>();

    public MultiFileBucket() {
        files.add(new FileBucket());
        files.add(new FileBucket());
        files.add(new FileBucket());
    }

    public List<FileBucket> getFiles() {
        return files;
    }

    public void setFiles(List<FileBucket> files) {
        this.files = files;
    }
}
