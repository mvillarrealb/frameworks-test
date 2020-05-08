package org.irdigital.cc.customers.domain.entity;

import lombok.*;

import javax.persistence.*;


@Data
@Entity
@EqualsAndHashCode(exclude={"customer"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="customer_address")
public class Address extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;

    private String department;

    private String province;

    private String district;

    private String address;

    private Double latitude;

    private Double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
