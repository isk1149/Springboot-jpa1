package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.matcher.FilterableList;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    /*
    cascade = ALL을 사용함으로써
    persist(orderItemA); persist(orderItemA); persist(orderItemA); persist(order); 
    ->
    persist(order);만 해도 persist(orderItemA); persist(orderItemA); persist(orderItemA); 됨
    delete도 마찬가지
     */
    @OneToMany(mappedBy = "order", cascade = ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDateTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // ORDER, CANCEL

    /**********************************************************************/
    /*** 연관관계 편의메소드. 두 엔티티 중에 좀 더 많이 사용하는 쪽에 작성하면 편리 ***/
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
    /**********************************************************************/
}
