package org.mvillarreal.domain.entity;

import lombok.*;
import org.mvillarreal.domain.enums.ContactType;
import org.mvillarreal.domain.exception.BadRequestException;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(exclude={"customer"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="customer_contact")
public class Contact extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contactId;

    @Enumerated(EnumType.STRING)
    private ContactType contactType;

    private String contact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public void validate() {
        if(!this.contactType.validate(this.contact)) {
            throw new BadRequestException("El formato  de "+this.contactType.getDescription()+" es incorrecto para :"+this.contact);
        }
    }
}
