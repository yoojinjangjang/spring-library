package ssafy.library.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 6)
    private String birth;
    @Column(nullable = false, length = 11)
    private String phone;
    @Column(nullable = false, length = 100)
    private String email;

    @Embedded
    private Address address;

    public Member(Long id, String name, String birth, String phone, String email,
            Address address) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
}

/*
https://ttl-blog.tistory.com/114
 */