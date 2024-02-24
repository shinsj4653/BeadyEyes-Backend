package gdsc.pointer.controller;

import gdsc.pointer.domain.User;
import gdsc.pointer.dto.request.image.ImageUploadDto;
import gdsc.pointer.dto.request.image.PointerDto;
import gdsc.pointer.dto.response.ResultDto;
import gdsc.pointer.dto.response.image.PointerResponseDto;
import gdsc.pointer.dto.response.image.PolyResponseDto;
import gdsc.pointer.service.ImageService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    // change directory name test
    private final ImageService imageService;
    //private final VisionServiceImpl visionService;

    @PostMapping("upload")
    public ResponseEntity<?> uploadImage(ImageUploadDto dto) throws Exception {
        String imageUrl = imageService.uploadImage(dto.getImage());
        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, "이미지 업로드 완료", imageUrl));
    }

    @PostMapping("toText")
    public ResponseEntity<?> toText(ImageUploadDto dto) throws Exception {
        String result = imageService.toText(dto.getImage());
        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, "이미지 to Text", result));
    }

    @PostMapping("boundingPoly")
    public ResponseEntity<?> boundingPoly(ImageUploadDto dto) throws Exception {
        PolyResponseDto result = imageService.getImageTextBoundingPoly(dto.getImage());
        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, "이미지 텍스트 좌표 값 반환", result));
    }

    @PostMapping("pointer")
    public ResponseEntity<?> pointer(PointerDto dto) throws Exception {
        String result = imageService.getWordWithPointer(dto);
        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, "손가락이 가리키는 텍스트 반환", result));
    }
}
