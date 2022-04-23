package com.webcrew.easystory.domain.entities;

import com.webcrew.easystory.domain.values.Client.ClientId;
import com.webcrew.easystory.domain.values.Client.Username;
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

    private Password password;

    private FirstName firstName;

    private LastName lastName;

    private Email email;

    private Telephone telephone;

}
