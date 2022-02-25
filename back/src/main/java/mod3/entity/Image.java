package mod3.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String fileURI;

    private String msg;

    private LocalDateTime postedTime;

    @Builder
    public Image(String name, String fileURI, String msg, LocalDateTime postedTime) {
        this.name = name;
        this.fileURI = fileURI;
        this.msg = msg;
        this.postedTime = postedTime;
    }

    public void updateMsg(String newMsg) {
        this.msg = newMsg;
    }
}
