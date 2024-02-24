package gdsc.pointer.dto.request.image;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor()
@AllArgsConstructor
public class PointerAIDto {
    private String imageUrl;
}
