package com.brian.bar.business.order;

import com.brian.bar.business.commons.EventRepository;
import com.brian.bar.domain.order.event.OrderCreated;
import com.brian.bar.domain.order.event.TableAdded;
import com.brian.bar.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class AddTableEventUseCaseTest {

    @Mock
    private EventRepository eventRepository;
    private AddTableEventUseCase addTableEventUseCase;

    @BeforeEach
    void setup(){
        addTableEventUseCase = new AddTableEventUseCase(eventRepository);
    }

    @Test
    void successfulScenario() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        OrderCreated orderCreated = new OrderCreated(
                "Pending",
                "Order modification",
                "tableID",
                "drinkID"
        );
        orderCreated.setAggregateRootId("orderID");

        TableAdded tableAdded = new TableAdded("table1", 7, "costumer1", "orderID");
        Mockito.when(eventRepository.findByAggregatedRootId(tableAdded.getOrderID()))
                .thenAnswer(invocationOnMock ->  {
                    List<DomainEvent> eventList = new ArrayList<DomainEvent>();
                    eventList.add(orderCreated);
                    return eventList;
                });

        Mockito.when(eventRepository.saveEvent(ArgumentMatchers.any(TableAdded.class)))
                .thenAnswer(invocationOnMock -> {
                    return  invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = addTableEventUseCase.apply(tableAdded);

        Assertions.assertEquals(1,domainEventList.size());
        Assertions.assertNotEquals(3,domainEventList.size());
        Assertions.assertEquals("orderID",domainEventList.get(0).aggregateRootId());
        Assertions.assertNotEquals("order",domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(7, domainEventList.get(0).getClass().getMethod("getTableNum").invoke(domainEventList.get(0)));
        Assertions.assertNotEquals(5, domainEventList.get(0).getClass().getMethod("getTableNum").invoke(domainEventList.get(0)));
    }
}
