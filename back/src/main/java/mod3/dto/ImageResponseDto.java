package mod3.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import mod3.entity.Image;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class ImageResponseDto {
    private Long id;
    private List<String> fileURI;
    private String name;
    private String msg;
    private LocalDateTime postedTime;

    public ImageResponseDto(Image image) {
        this.id = image.getId();
        this.fileURI = Arrays.stream(image.getFileURI().split("&and&")).toList();
        this.name = image.getName();
        this.msg = image.getMsg();
        this.postedTime = image.getPostedTime();
    }
}
