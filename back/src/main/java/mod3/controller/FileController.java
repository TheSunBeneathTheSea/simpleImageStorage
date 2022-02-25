package mod3.controller;

import lombok.RequiredArgsConstructor;
import mod3.service.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class FileController {
    private final FileService fileService;

    @GetMapping("/images/**")
    public ResponseEntity<byte[]> getFile(HttpServletRequest request) throws IOException {
        String uri = request.getRequestURI();
        byte[] file = fileService.getFile(uri);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<byte[]>(file, headers, HttpStatus.OK);
    }
}
