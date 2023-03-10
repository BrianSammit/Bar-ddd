package com.brian.bar.business.order;

import com.brian.bar.business.commons.EventRepository;
import com.brian.bar.business.table.AddWaiterUseCase;
import com.brian.bar.domain.order.command.AddTableCommand;
import com.brian.bar.domain.order.event.OrderCreated;
import com.brian.bar.domain.order.event.TableAdded;
import com.brian.bar.domain.table.command.AddWaiterCommand;
import com.brian.bar.domain.table.event.TableCreated;
import com.brian.bar.domain.table.event.WaiterAdded;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddTableUseCaseTest {

    @Mock
    private EventRepository eventRepository;
    private AddTableUseCase addTableUseCase;

    @BeforeEach
    void setup(){
        addTableUseCase = new AddTableUseCase(eventRepository);
    }

    @Test
    void successfulScenario() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        OrderCreated orderCreated = new OrderCreated(
                "Pending",
                "Special modification",
                "tableID",
                "drinkID"
        );
        orderCreated.setAggregateRootId("orderID");

        AddTableCommand addTableCommand = new AddTableCommand(
                "tableID",
                5,
                "costumerID",
                "orderID"
        );

        Mockito.when(eventRepository.findByAggregatedRootId(addTableCommand.getOrderID()))
                .thenAnswer(invocationOnMock ->  {
                    List<DomainEvent> eventList = new ArrayList<DomainEvent>();
                    eventList.add(orderCreated);
                    return eventList;
                });

        Mockito.when(eventRepository.saveEvent(ArgumentMatchers.any(TableAdded.class)))
                .thenAnswer(invocationOnMock -> {
                    return  invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = addTableUseCase.apply(addTableCommand);

        Assertions.assertEquals(1,domainEventList.size());
        Assertions.assertNotEquals(3,domainEventList.size());
        Assertions.assertEquals("orderID",domainEventList.get(0).aggregateRootId());
        Assertions.assertNotEquals("order",domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(5, domainEventList.get(0).getClass().getMethod("getTableNum").invoke(domainEventList.get(0)));
        Assertions.assertNotEquals( 8, domainEventList.get(0).getClass().getMethod("getTableNum").invoke(domainEventList.get(0)));
    }

}
