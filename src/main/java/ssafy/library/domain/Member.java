package ssafy.library.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    // 회원과 예약은 일대 다의 관계 하나의 회원이 여러개의 예약을 가진다.
    // 멤버를 조회할때 예약정보를 불러오고 싶은 경우 해당 필드 정의해야 함
    // => 그럴필요 없는 경우 없어도됨
    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations = new ArrayList<>();


    // 회원과 대출은 일대 다의 관계 하나의 회원이 여러개의 예약을 가진다.
    // 멤버를 조회할때 대출정보를 불러오고 싶은 경우 해당 필드 정의해야 함
    // => 그럴필요 없는 경우 없어도됨
    @OneToMany(mappedBy = "member")
    private List<Loan> loans = new ArrayList<>();

}

/*
https://ttl-blog.tistory.com/114
 */