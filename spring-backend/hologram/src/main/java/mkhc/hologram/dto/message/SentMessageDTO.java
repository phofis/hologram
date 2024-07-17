package mkhc.hologram.dto.message;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class SentMessageDTO {
    private Long senderId;
    private Long conversationId;
    private String text;
    private MultipartFile file;
    private Boolean isForwarded;
    private ReplyToDTO replyTo;
}
