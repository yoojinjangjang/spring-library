package ssafy.library.domain;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
public class Category {

    @Id
    @GeneratedValue
    @Column(name="category_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    // book_info와 연관관계 - 일대다: 하나의 카테고리에 여러개의 북 정보
    // 연관관계 주인과의 매핑을 위한 mappedBy 속성 필요
    @OneToMany(mappedBy = "category")
    // bookinfo 테이블에 있는, category 필드에 의해서 매핑이 될것이다.
    private List<BookInfo> bookInfoList = new ArrayList<>();

}
