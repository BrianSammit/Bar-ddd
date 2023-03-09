package com.brian.bar.business.order;

import com.brian.bar.business.commons.EventRepository;
import com.brian.bar.domain.order.command.RemoveDrinkCommand;
import com.brian.bar.domain.order.event.DrinkAdded;
import com.brian.bar.domain.order.event.DrinkRemoved;
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
class RemoveDrinkUseCaseTest {
    @Mock
    private EventRepository eventsRepository;
    private RemoveDrinkUseCase removeDrinkUseCase;

    @BeforeEach
    void setup(){
        removeDrinkUseCase = new RemoveDrinkUseCase(eventsRepository);
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

        DrinkAdded drinkAdded = new DrinkAdded("drink1", "name", 4.3f, "special modification");
        DrinkAdded drink2Added = new DrinkAdded("drink2", "name", 8.3f, "special modification");

        RemoveDrinkCommand removeDrinkCommand = new RemoveDrinkCommand("drink1", "orderID");
        Mockito.when(eventsRepository.findByAggregatedRootId(removeDrinkCommand.getOrderID()))
                .thenAnswer(invocationOnMock ->  {
                    List<DomainEvent> eventList = new ArrayList<DomainEvent>();
                    eventList.add(orderCreated);
                    eventList.add(drinkAdded);
                    eventList.add(drink2Added);
                    return eventList;
                });

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(DrinkRemoved.class)))
                .thenAnswer(invocationOnMock -> {
                    return  invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = removeDrinkUseCase.apply(removeDrinkCommand);

        Assertions.assertEquals(1,domainEventList.size());
        Assertions.assertNotEquals(3,domainEventList.size());
        Assertions.assertEquals("orderID",domainEventList.get(0).aggregateRootId());
        Assertions.assertNotEquals("order",domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals("drink1", domainEventList.get(0).getClass().getMethod("getDrinkID").invoke(domainEventList.get(0)));
        Assertions.assertNotEquals("drink ", domainEventList.get(0).getClass().getMethod("getDrinkID").invoke(domainEventList.get(0)));
    }
}
