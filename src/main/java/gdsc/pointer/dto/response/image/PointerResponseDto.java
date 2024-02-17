package gdsc.pointer.dto.response.image;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PointerResponseDto {
    private List<String> words;
}
