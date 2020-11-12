package com.wusd.familyfinanceapi.rest;

import com.wusd.familyfinanceapi.FamilyFinanceApiApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

public class RestTest extends FamilyFinanceApiApplicationTests {
    @Autowired
    private RestTemplate rest;

    @Test
    public void configTrue() {
        String forObject = rest.getForObject("http://www.baidu.com", String.class);
        Assert.assertTrue(forObject.contains("<html>"));
    }

    @Test
    public void ossToken() {
        String forObject = rest.getForObject("http://yf.119.gov.cn/obs/proof/policy?scheme=http", String.class);
        http://yf.119.gov.cn/obs/proof?scheme=http
        System.out.println(forObject);
    }

    @Test
    public void uploadObject() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        String str = "wusd上传";
        Path path = Paths.get("upload.tmp");
        Files.write(path, str.getBytes());
        FileSystemResource fileSystemResource = new FileSystemResource(path);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileSystemResource);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

        String url = "http://yf.119.gov.cn/obs/obsObject?key=app/20201029/1603887097469/3.txt";
        ResponseEntity<String> stringResponseEntity = rest.postForEntity(url, entity, String.class);
        System.out.println(stringResponseEntity);

    }

}
