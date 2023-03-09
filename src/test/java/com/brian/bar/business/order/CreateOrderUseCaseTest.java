package com.brian.bar.business.order;

import com.brian.bar.business.commons.EventRepository;
import com.brian.bar.domain.order.command.CreateOrderCommand;
import com.brian.bar.domain.order.event.OrderCreated;
import com.brian.bar.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseTest {

    @Mock
    private EventRepository eventRepository;
    private CreateOrderUseCase createOrderUseCase;

    @BeforeEach
    void setup(){
        createOrderUseCase = new CreateOrderUseCase(eventRepository);
    }
    @Test
    void successfulScenario(){
        CreateOrderCommand createOrderCommand = new CreateOrderCommand(
                "orderID",
                "Pending",
                "Especial modification",
                "table1",
                "drink1"
        );
        OrderCreated orderCreated = new OrderCreated(
                "Pending",
                "Especial modification",
                "table1",
                "drink1"
        );

        orderCreated.setAggregateRootId("orderID");
        Mockito.when(eventRepository.saveEvent(ArgumentMatchers.any(OrderCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return  invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEvents = createOrderUseCase.apply(createOrderCommand);


        Assertions.assertEquals(1, domainEvents.size());
        Assertions.assertNotEquals(2, domainEvents.size());
        Assertions.assertEquals("orderID", domainEvents.get(0).aggregateRootId());
        Assertions.assertNotEquals("test1", domainEvents.get(0).aggregateRootId());
    }

}