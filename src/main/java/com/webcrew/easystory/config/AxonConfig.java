package com.webcrew.easystory.config;

import com.webcrew.easystory.command.domain.entities.Client;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.spring.config.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.axonframework.modelling.command.Repository;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class AxonConfig {
    @Bean
    public CommandBus commandBus(PlatformTransactionManager platformTransactionManager){
        return SimpleCommandBus.builder()
                .transactionManager(new SpringTransactionManager(platformTransactionManager))
                .build();
    }

    @Bean
    public AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor(){
        return new AnnotationCommandHandlerBeanPostProcessor();
    }

    @Bean
    public EventBus eventBus() {
        return SimpleEventBus.builder().build();
    }

    @Bean
    public Repository<Client>eventSourcingRepository(EventStore eventStore){
        return EventSourcingRepository.builder(Client.class)
                .eventStore(eventStore)
                .build();
    }
}
