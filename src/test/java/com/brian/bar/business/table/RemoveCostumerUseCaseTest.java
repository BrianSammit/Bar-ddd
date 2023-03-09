package com.brian.bar.business.table;

import com.brian.bar.business.commons.EventRepository;
import com.brian.bar.domain.table.command.RemoveCostumerCommand;
import com.brian.bar.domain.table.event.CostumerAdded;
import com.brian.bar.domain.table.event.CostumerRemoved;
import com.brian.bar.domain.table.event.TableCreated;
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
class  RemoveCostumerUseCaseTest{
    @Mock
    private EventRepository eventsRepository;
    private RemoveCostumerUseCase removeCostumerUseCase;

    @BeforeEach
    void setup(){
        removeCostumerUseCase = new RemoveCostumerUseCase(eventsRepository);
    }

    @Test
    void successfulScenario() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TableCreated tableCreated = new TableCreated(
                3,
                "Waiter name",
                "costumerID"
        );
        tableCreated.setAggregateRootId("tableID");

        CostumerAdded costumerAdded = new CostumerAdded("costumer1", "name 1");
        CostumerAdded costumerAdded1 = new CostumerAdded("costumer2", "name 2");

        RemoveCostumerCommand removeCostumerCommand = new RemoveCostumerCommand("costumer1", "tableID");
        Mockito.when(eventsRepository.findByAggregatedRootId(removeCostumerCommand.getTableID()))
                .thenAnswer(invocationOnMock ->  {
                    List<DomainEvent> eventList = new ArrayList<DomainEvent>();
                    eventList.add(tableCreated);
                    eventList.add(costumerAdded);
                    eventList.add(costumerAdded1);
                    return eventList;
                });

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(CostumerRemoved.class)))
                .thenAnswer(invocationOnMock -> {
                    return  invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = removeCostumerUseCase.apply(removeCostumerCommand);

        Assertions.assertEquals(1,domainEventList.size());
        Assertions.assertNotEquals(3,domainEventList.size());
        Assertions.assertEquals("tableID",domainEventList.get(0).aggregateRootId());
        Assertions.assertNotEquals("table",domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals("costumer1", domainEventList.get(0).getClass().getMethod("getCostumerID").invoke(domainEventList.get(0)));
        Assertions.assertNotEquals("costumer ", domainEventList.get(0).getClass().getMethod("getCostumerID").invoke(domainEventList.get(0)));
    }
}
