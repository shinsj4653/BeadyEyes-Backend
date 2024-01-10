package gdsc.pointer.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "TB_USER")
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(length = 30)
    private String name;

}
