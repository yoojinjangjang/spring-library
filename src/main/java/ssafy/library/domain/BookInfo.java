package ssafy.library.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookInfo {

    @Id // 기본키 => 널 제외, 유일성 보장
    @Column(name="isbn", length = 13)
    private String isbn;

    @Column(nullable = false, length = 50)
    private String author; // 저자
    @Column(nullable = false)
    private String name;  // 도서명
    @Column(nullable = false)
    private String publisher; // 출판사명
    @Column(nullable = false)
    private LocalDateTime createdDate; // 등록일자

    // 도서분류 PK 관계 매핑 실행 필요
    //다대일: 연관관계의 주인 (N) , mappedBy 속성을 가지지 않고, @JoinColumn을 사용하여 FK 매핑 수행
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category; // 도서분류 PK -> 외래키로써 여러개의 책 정보는 하나의 카테고리를 가진다.


}
