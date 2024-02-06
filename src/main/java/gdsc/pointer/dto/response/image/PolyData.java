package gdsc.pointer.dto.response.image;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@Getter
@AllArgsConstructor
public class PolyData {
    private String text;
    private List<PolyVertex> vertices;
}
