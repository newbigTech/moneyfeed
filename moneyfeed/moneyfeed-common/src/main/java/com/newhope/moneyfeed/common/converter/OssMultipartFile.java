package com.newhope.moneyfeed.common.converter;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 对MultipartFile进行代理，实现我们可以直接对流进行操作
 */
public class OssMultipartFile  implements MultipartFile  {
    MultipartFile multipartFile;

    private InputStream inputStream;

    public OssMultipartFile(MultipartFile multipartFile) throws IOException {

        this.multipartFile = multipartFile;
        this.inputStream = multipartFile.getInputStream();
    }


    public void setInputStream(InputStream inputStream){
        this.inputStream = inputStream;
    }
    @Override
    public String getName() {
        return multipartFile.getName();
    }

    @Override

    public String getOriginalFilename() {
        return multipartFile.getOriginalFilename();
    }

    @Override
    public String getContentType() {
        return multipartFile.getContentType();
    }

    @Override
    public boolean isEmpty() {
        return multipartFile.isEmpty();
    }

    @Override
    public long getSize() {
        return multipartFile.getSize();
    }

    @Override
    public byte[] getBytes() throws IOException {
        return FileCopyUtils.copyToByteArray(inputStream);
    }
    @Override
    public InputStream getInputStream() throws IOException {
        return inputStream;
    }

    @Override
    public void transferTo(File file) throws IOException, IllegalStateException {
        multipartFile.transferTo(file);
    }
}
