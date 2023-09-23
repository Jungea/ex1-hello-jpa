package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Member {

    @Id
    private Long id;

    @Column(name = "name")
    private String username;

    private int age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    @Lob
    private String description;

    @Transient
    private int temp;

    public Member() {

    }
}
