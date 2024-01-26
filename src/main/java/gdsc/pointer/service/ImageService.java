package gdsc.pointer.service;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.*;
import com.google.cloud.vision.v1.*;
import gdsc.pointer.dto.request.image.ImageUrlDto;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ImageService {

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    @Value("${AI_SERVER_URL}")
    private String aiServerUrl;

    private final Storage storage;


    public String uploadImage(MultipartFile file) throws IOException {

        // !!!!!!!!!!!이미지 업로드 관련 부분!!!!!!!!!!!!!!!
        String uuid = UUID.randomUUID().toString(); // Google Cloud Storage에 저장될 파일 이름
        String ext = file.getContentType(); // 파일의 형식 ex) JPG

        // Cloud에 이미지 업로드
        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(bucketName, uuid)
                        .setContentType(ext)
                        .build(),
                file.getInputStream()
        );

        return "https://storage.googleapis.com/" + bucketName + "/" + uuid;
    }

    public String toText(MultipartFile file) throws IOException {

        // GCS 사진 업로드 후, 공개 이미지 url 반환
        String image_url = uploadImage(file);

        // RestTemplate 사용하여 AI Server로 통신
        // 사진 url 전송 -> 텍스트 반환
        // POST 요청
        ResponseEntity<String> responseEntity = postWithImageUrl(image_url);
        log.info("responseEntity.getBody() = {}", responseEntity.getBody());

        String postResult = responseEntity.getBody();

        // 문자열 파싱
        String parsedResult = textParse(postResult);

        return parsedResult;
    }

    private ResponseEntity<String> postWithImageUrl(String url) {
        URI uri = UriComponentsBuilder
                .fromUriString(aiServerUrl)
                .path("/image/toText")
                .encode()
                .build()
                .toUri();

        ImageUrlDto imageUrlDto = new ImageUrlDto();
        imageUrlDto.setImageUrl(url);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                uri, imageUrlDto, String.class
        );

        return responseEntity;
    }

    private String textParse(String text) {
        // Replace "\\" with an empty string
        String parsedText = text.replace("\\", "");

        // Replace "\n" with an empty string
        parsedText = parsedText.replace("\n", "");

        return parsedText;
    }

}
