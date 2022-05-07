package com.webcrew.easystory.domain.entities;

import com.webcrew.easystory.domain.values.AuditTrail;
import com.webcrew.easystory.domain.values.Client.*;
import lombok.Data;
import org.axonframework.modelling.command.AggregateIdentifier;

import javax.persistence.*;

@Entity(name="Client")
@Table(name="clients")
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        discriminatorType = DiscriminatorType.INTEGER,
        name="client_type_id",
        columnDefinition = "TINYINT(1)"
)
public class Client {
    @AggregateIdentifier
    //VALID OBJECTS, son del tipo Embedded
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "id", columnDefinition = "BINARY(16)"))
    })
    private ClientId id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name= "value", column = @Column(name = "username", length = 150, nullable = false))
    })
    private Username username;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="value", column = @Column(name="password", length = 200, nullable = false))
    })
    private Password password;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name= "value", column = @Column(name = "firstname", length = 150, nullable = false))
    })
    private FirstName firstName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name= "value", column = @Column(name = "lastname", length = 150, nullable = false))
    })
    private LastName lastName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name= "value", column = @Column(name = "email", length = 150, nullable = false))
    })
    private Email email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name= "value", column = @Column(name = "telephone", length = 150, nullable = false))
    })
    private Telephone telephone;

    @Embedded
    private AuditTrail auditTrail;

    public Client(ClientId clientId, Username username, Password password, FirstName firstName, LastName lastName, Email email, Telephone telephone, AuditTrail auditTrail) {
    setId(clientId);
    setUsername(username);
    setPassword(password);
    setFirstName(firstName);
    setLastName(lastName);
    setEmail(email);
    setTelephone(telephone);
    setAuditTrail(auditTrail);
    }

    protected Client(){}
}



