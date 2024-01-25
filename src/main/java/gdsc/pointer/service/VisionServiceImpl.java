//package gdsc.pointer.service;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class VisionServiceImpl implements VisionService {
//
//
//    private final CloudVisionTemplate cloudVisionTemplate;
//
//
//    @Override
//    public String extractTextFromImage(MultipartFile file) {
//
//        String textFromImage = cloudVisionTemplate.
//                extractTextFromImage(file.getResource());
//
//        return textFromImage;
//    }
//
//
//
//}
