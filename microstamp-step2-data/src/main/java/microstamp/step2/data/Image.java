package microstamp.step2.data;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Base64;

@Entity(name = "Image")
@Table(name = "images")
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Lob
    @Column(columnDefinition="LONGBLOB")
    private byte[] data;

    public String getBase64() {
        return Base64.getEncoder().encodeToString(data);
    }

}
