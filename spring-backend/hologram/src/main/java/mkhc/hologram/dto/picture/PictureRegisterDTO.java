package mkhc.hologram.dto.picture;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PictureRegisterDTO {
    private String fileName;

    private String fileExtension;

    private byte[] binaryData;
}
