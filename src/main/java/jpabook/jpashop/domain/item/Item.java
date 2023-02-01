package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn //@DiscriminatorColumn(name = "TYPE") ()내용 빼면 default로 DTYPE이 사용됨//
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    //실무에서는 사용X
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
