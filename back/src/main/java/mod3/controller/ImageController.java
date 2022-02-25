package mod3.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mod3.dto.ImageResponseDto;
import mod3.dto.ImageSaveRequestDto;
import mod3.dto.ImageUpdateRequestDto;
import mod3.service.FileService;
import mod3.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(("/images"))
@RequiredArgsConstructor
@RestController
public class ImageController {
    private final ImageService imageService;
    private final FileService fileService;

    @PostMapping
    public ResponseEntity<ImageResponseDto> save(@RequestParam("files") List<MultipartFile> files, @RequestParam("requestDto") String requestDto) throws JsonProcessingException, IOException, Exception {
        ObjectMapper mapper = new ObjectMapper();
        ImageSaveRequestDto saveRequestDto = mapper.readValue(requestDto, ImageSaveRequestDto.class);
        String fileURI = fileService.saveFile(files);
        ImageResponseDto responseDto = imageService.saveImage(saveRequestDto, fileURI);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<ImageResponseDto>> read() throws IOException {

        return ResponseEntity.status(HttpStatus.OK).body(imageService.getImages());
    }

    @PutMapping
    public ResponseEntity<String> edit(Long id, String newMsg) {
        ImageUpdateRequestDto requestDto = new ImageUpdateRequestDto();
        requestDto.setId(id);
        requestDto.setMsg(newMsg);

        return ResponseEntity.status(HttpStatus.OK).body(imageService.updateImageMsg(requestDto));
    }

    @DeleteMapping
    public void delete(Long id) {
        imageService.deleteImage(id);
    }
}
