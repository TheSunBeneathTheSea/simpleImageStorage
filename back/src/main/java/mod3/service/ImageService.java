package mod3.service;

import lombok.RequiredArgsConstructor;
import mod3.dto.ImageResponseDto;
import mod3.dto.ImageUpdateRequestDto;
import mod3.entity.Image;
import mod3.dto.ImageSaveRequestDto;
import mod3.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;

    @Transactional
    public ImageResponseDto saveImage(ImageSaveRequestDto requestDto, String fileURI) {
        Image request = Image.builder()
                .name(requestDto.getName())
                .msg(requestDto.getMsg())
                .postedTime(LocalDateTime.now())
                .fileURI(fileURI)
                .build();

        imageRepository.save(request);

        return new ImageResponseDto(request);
    }

    //TODO 유저서비스를 구현한다면 여기에 각 유저가 소유한 이미지만 반환하는 메소드 구현
    //지금은 일단 모든 사진 반환
    @Transactional
    public List<ImageResponseDto> getImages() {
        List<ImageResponseDto> responseDtoList = imageRepository.findAllImageOrderByDateDesc().stream().map((image -> new ImageResponseDto(image))).toList();
        return responseDtoList;
    }

    @Transactional
    public String updateImageMsg(ImageUpdateRequestDto requestDto) {
        Image target = imageRepository.findById(requestDto.getId()).orElseThrow(() -> new NullPointerException("Cannot find image by id:" + requestDto.getId()));

        target.updateMsg(requestDto.getMsg());

        return target.getMsg();
    }

    @Transactional
    public String deleteImage(Long id) {
        Image target = imageRepository.findById(id).orElseThrow(() -> new NullPointerException("Cannot find image by id:" + id));

        imageRepository.delete(target);

        return "deleted:" + id;
    }
}
