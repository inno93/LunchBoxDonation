package com.lunchbox.lunchboxdonation.entity.Review;

import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBox;
import com.lunchbox.lunchboxdonation.entity.Timestamp;
import com.lunchbox.lunchboxdonation.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
@Table(name = "TBL_REVIEW")
public class Review extends Timestamp {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String reviewContent;

    //    멤버 연관 관계
    // 다대일 관계: 여러 개의 리뷰는 하나의 멤버와 연관될 수 있음
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID") // 이 부분은 외래키(Foreign Key) 컬럼의 이름을 지정.
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LUNCHBOX_ID")
    private LunchBox lunchbox;

    @Builder
    public Review(Long id, String reviewContent) {
        this.id = id;
        this.reviewContent = reviewContent;
    }


//    도시락 연관 관계

//    @Builder는 Lombok 어노테이션 중 하나로, 주로 빌더 패턴을 자동으로 생성해주는 역할을 합니다.
//    빌더 패턴은 객체를 생성할 때 사용하는 디자인 패턴 중 하나로, 객체 생성에 사용되는 매개변수가 많을 때 코드의 가독성과 유지보수성을 향상시키기 위해 사용됩니다.
//    @Builder 어노테이션을 클래스에 추가하면, 해당 클래스에 대해 빌더 패턴을 사용할 수 있는 빌더 클래스를 자동으로 생성해줍니다.

//    이렇게 생성된 빌더 클래스는 원하는 필드만을 선택적으로 설정할 수 있도록 만들어주며, 빌더 메서드를 통해 인스턴스를 생성할 수 있게 됩니다.
//    @Builder 어노테이션의 역할은 다음과 같습니다:

//    빌더 패턴 자동 생성: @Builder를 사용하면 Lombok이 자동으로 빌더 패턴을 생성해주기 때문에 개발자가 직접 빌더 패턴을 구현할 필요가 없습니다.
//    선택적 필드 설정: 빌더 클래스를 통해 인스턴스를 생성할 때, 필요한 필드만 선택적으로 설정할 수 있어서 코드를 간결하고 가독성 있게 만들어줍니다.

//    불변성 보장: 빌더 패턴을 사용하면 생성된 인스턴스가 불변성을 가질 수 있도록 도와줍니다. 즉, 생성된 후에는 값을 변경할 수 없는 불변 객체로 만들 수 있습니다.
//    @Builder 어노테이션을 사용하면 생성자에 비해 훨씬 명시적이고 간결한 방법으로 객체를 생성할 수 있으며, 특히 많은 매개변수를 가지는 클래스의 경우 더욱 유용하게 사용됩니다.
}
