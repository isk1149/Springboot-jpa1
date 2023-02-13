package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("B") 애노테이션 선언하지 않으면 디폴트로 클래스 이름이 사용됨//
@Getter @Setter
public class Book extends Item {
    private String author;
    private String isbn;

    public void changeItem(String name, int price, int stockQuantity, String author, String isbn) {
        super.changeItem(name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }
}
