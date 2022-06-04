package com.webcrew.easystory.command.domain.entities;
import com.webcrew.easystory.command.domain.values.AuditTrail;
import com.webcrew.easystory.command.domain.values.Client.*;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@DiscriminatorValue(value="2")
public class Escritor extends Client {

    public Escritor(ClientId clientId, Username username, Password password, FirstName firstName, LastName lastName, Email email, Telephone telephone, AuditTrail auditTrail) {
        super(clientId, username, password, firstName, lastName, email, telephone, auditTrail);

    }

    protected Escritor(){}



}
