package mod3.service;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {
    public String saveFile(List<MultipartFile> files) throws IOException, Exception{
        StringBuilder stringBuilder = new StringBuilder();
        String folder = LocalDate.now().format(DateTimeFormatter.ofPattern("uuuu/MM/"));

        for (MultipartFile file : files) {
            String contentType = file.getContentType();
            String type;

            if (contentType.contains("image/jpeg")) {
                type = ".jpg";
            } else if (contentType.contains("image/png")) {
                type = ".png";
            } else if (contentType.contains("image/gif")) {
                type = ".gif";
            } else if (contentType.contains("image/webp")) {
                type = ".webp";
            } else if (contentType.contains("image/bmp")) {
                type = ".bmp";
            } else {
                throw new Exception("It's not picture:" + contentType);
            }

            UUID newName = UUID.randomUUID();
            String newFile = newName + type;
            String path = "/images/" + folder;

            File fileDir = new File(path);
            if(!fileDir.exists()){
                fileDir.mkdirs();
            }
            file.transferTo(new File(fileDir.getAbsolutePath() + "/" + newFile));

            stringBuilder.append(path + newFile);
            stringBuilder.append("&and&");
        }

        return stringBuilder.toString();
    }

    public byte[] getFile(String path) throws IOException {
        File filePath = new File(new File(path).getAbsolutePath());
        byte[] file = FileUtils.readFileToByteArray(filePath);

        return file;
    }
}
