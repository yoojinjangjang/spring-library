package ssafy.library.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue
    @Column(name="reservation_id")
    private Long id;

    @Column(nullable = false)
    private LocalDateTime reservationDate;

    // 회원과 다대일 관계 (하나의 회원에 여러개의 예약이 가능)
    // 다 쪽이 의존관계의 주인이다. mappedBy 사용 X
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    // 도서와의 다대일 관계 (하나의 도서가 여러개의 예약이 가능)
    // 다쪽이 의존관계 주인이다. mappedBy 사용 x
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_id")
    private Book book;

}
