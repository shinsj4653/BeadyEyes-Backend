package gdsc.pointer.service;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.*;
import com.google.cloud.vision.v1.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ImageService {

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    private final Storage storage;


    // [START vision_spring_autowire]
    //private final CloudVisionTemplate cloudVisionTemplate;
    // [END vision_spring_autowire]


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

//    public String toText() throws IOException {
//        // TODO(developer): Replace these variables before running the sample.
//        String filePath = "https://storage.googleapis.com/gdcs-sc-beadyeyes-bucket/574291a3-87f1-46b5-8a1a-6ba40d75bcea";
//
//        UrlResource gcsResource = new UrlResource(filePath);
//
//        String textFromImage =
//                cloudVisionTemplate.extractTextFromImage(gcsResource);
//        return "Text from image: " + textFromImage;
//        //return detectTextGcs(filePath);
//    }
//
//    // Detects text in the specified remote image on Google Cloud Storage.
//    public String detectTextGcs(String gcsPath) throws IOException {
//
//        List<AnnotateImageRequest> requests = new ArrayList<>();
//
//        ImageSource imgSource = ImageSource.newBuilder().setImageUri(gcsPath).build();
//        Image img = Image.newBuilder().setSource(imgSource).build();
//        Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
//        AnnotateImageRequest request =
//                AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
//        requests.add(request);
//
//        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
//            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
//            List<AnnotateImageResponse> responses = response.getResponsesList();
//
//            StringBuilder result = new StringBuilder();
//            for (AnnotateImageResponse res : responses) {
//
//                if (res.hasError()) {
//                    System.out.format("Error: %s%n", res.getError().getMessage());
//                    return null;
//                }
//
//                for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
//                    log.info(annotation.getDescription());
//                    result.append(annotation.getDescription()).append(" ");
//                }
//            }
//
//
//
//            return result.toString();
//        }
//        catch (Exception exception) {
//            return exception.getMessage();
//        }
//
//    }




}
