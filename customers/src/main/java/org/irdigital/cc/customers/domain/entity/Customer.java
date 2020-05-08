package org.irdigital.cc.customers.domain.entity;

import lombok.*;
import org.irdigital.cc.customers.domain.enums.DocumentType;
import org.irdigital.cc.customers.domain.exception.BadRequestException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
 name="customers",
 uniqueConstraints = {
    @UniqueConstraint(columnNames = {"documentType","identityDocument"}, name = "unique_identity_document")
 }
)
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="customer_id")
    private Long id;

    private String name;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    private String identityDocument;

    @OneToMany(
        mappedBy = "customer",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    @Builder.Default
    private Set<Contact> contacts = new HashSet<>();

    @OneToMany(
        mappedBy = "customer",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    @Builder.Default
    private Set<Address> addresses = new HashSet<>();

    public void addAddress(Address address) {
        addresses.add(address);
        address.setCustomer(this);
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        contact.setCustomer(this);
    }

    public void validate() {
        if(!this.documentType.validate(this.identityDocument)) {
            throw new BadRequestException("El formato  de "+this.documentType.getDescription()+" es incorrecto para :"+this.identityDocument);
        }
    }
    public void created() {
        this.registerEvent(this);
    }
}
