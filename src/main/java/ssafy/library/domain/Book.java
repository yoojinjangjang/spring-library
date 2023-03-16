package ssafy.library.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @Column(name="book_id", length = 10)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookStatus status;

    // 도서 정보와 다대일 관계 하나의 도서정보는 여러개의 도서를 가진다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="isbn")
    private BookInfo bookInfo;

    // 해당 도서에 대한 예약정보를 알고 싶은 경우 아래 필드 필요
    @OneToMany(mappedBy = "book")
    private List<Reservation> reservations = new ArrayList<>();
}
