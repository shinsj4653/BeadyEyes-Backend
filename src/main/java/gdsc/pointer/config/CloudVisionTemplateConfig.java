//package gdsc.pointer.config;
//import com.google.auth.Credentials;
//import com.google.auth.oauth2.GoogleCredentials;
//
//import com.google.cloud.vision.v1.ImageAnnotatorClient;
//import com.google.cloud.vision.v1.ImageAnnotatorSettings;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//import java.io.IOException;
//import java.io.InputStream;
//
//
//@Configuration
//public class CloudVisionTemplateConfig {
//
//    @Value("${spring.cloud.gcp.vision.credentials.location}")
//    private String keyPath;
//
//    @Bean
//    public CloudVisionTemplate cloudVisionTemplate() throws IOException {
//        ImageAnnotatorClient imageAnnotatorClient = createImageAnnotatorClient();
//        return new CloudVisionTemplate(imageAnnotatorClient);
//    }
//
//    @Bean
//    public ImageAnnotatorClient createImageAnnotatorClient() throws IOException {
//        InputStream serviceAccount = getClass().getResourceAsStream(keyPath);
//        assert serviceAccount != null;
//        Credentials credentials = GoogleCredentials.fromStream(serviceAccount);
//
//        return ImageAnnotatorClient.create(ImageAnnotatorSettings.newBuilder()
//                .setCredentialsProvider(() -> credentials)
//                .build());
//    }
//}
