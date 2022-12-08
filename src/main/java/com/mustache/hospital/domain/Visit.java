package com.mustache.hospital.domain;

import com.mustache.hospital.domain.dto.VisitResponse;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Visit extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String disease;
    private float amount;

    public VisitResponse toResponse() {
        return VisitResponse.builder()
                .hospitalName(this.hospital.getHospitalName())
                .userName(this.user.getUserName())
                .disease(this.disease)
                .amount(this.amount)
                .build();
    }
}
