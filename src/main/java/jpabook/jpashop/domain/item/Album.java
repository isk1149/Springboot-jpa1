package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("A") 애노테이션 선언하지 않으면 디폴트로 클래스 이름이 사용됨//
@Getter @Setter
public class Album extends Item {
    private String artist;
    private String etc;
}
