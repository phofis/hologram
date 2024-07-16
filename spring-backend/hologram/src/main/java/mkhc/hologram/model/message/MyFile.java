package mkhc.hologram.model.message;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MyFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fileId;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileExtension;

    private byte[] binaryData;
}
