package mod3.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageUpdateRequestDto {
    private Long id;
    private String msg;
}
