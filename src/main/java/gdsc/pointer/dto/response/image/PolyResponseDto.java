package gdsc.pointer.dto.response.image;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PolyResponseDto {
    private int img_width;
    private int img_height;
    private List<PolyData> boundingPoly;
}
