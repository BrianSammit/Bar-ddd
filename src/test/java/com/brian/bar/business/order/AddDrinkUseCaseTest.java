package com.brian.bar.business.order;

import com.brian.bar.business.commons.EventRepository;
import com.brian.bar.domain.order.command.AddDrinkCommand;
import com.brian.bar.domain.order.event.DrinkAdded;
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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class AddDrinkUseCaseTest {

    @Mock
    private EventRepository eventRepository;
    private AddDrinkUseCase addDrinkUseCase;

    @BeforeEach
    void setup(){
        addDrinkUseCase = new AddDrinkUseCase(eventRepository);
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

        AddDrinkCommand addDrinkCommand = new AddDrinkCommand(
                "drinkID",
                "Drink name",
                5.20f,
                "Drink modification",
                "orderID"
        );

        Mockito.when(eventRepository.findByAggregatedRootId(addDrinkCommand.getOrderID()))
                .thenAnswer(invocationOnMock ->  {
                    List<DomainEvent> eventList = new ArrayList<DomainEvent>();
                    eventList.add(orderCreated);
                    return eventList;
                });

        Mockito.when(eventRepository.saveEvent(ArgumentMatchers.any(DrinkAdded.class)))
                .thenAnswer(invocationOnMock -> {
                    return  invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = addDrinkUseCase.apply(addDrinkCommand);

        Assertions.assertEquals(1,domainEventList.size());
        Assertions.assertNotEquals(3,domainEventList.size());
        Assertions.assertEquals("orderID",domainEventList.get(0).aggregateRootId());
        Assertions.assertNotEquals("order",domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(5.2f, domainEventList.get(0).getClass().getMethod("getPrice").invoke(domainEventList.get(0)));
        Assertions.assertNotEquals(7.2f, domainEventList.get(0).getClass().getMethod("getPrice").invoke(domainEventList.get(0)));
    }
}