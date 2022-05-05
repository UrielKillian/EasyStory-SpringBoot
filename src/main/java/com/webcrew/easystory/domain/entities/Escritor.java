package com.webcrew.easystory.domain.entities;
import com.webcrew.easystory.domain.values.Client.ClientId;
import com.webcrew.easystory.domain.values.Client.FirstName;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@DiscriminatorValue(value="2")
public class Escritor extends Client {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "clientId", length = 11))
    })
    private ClientId clientId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "FirstName", length = 150))
    })
    private FirstName firstName;
}