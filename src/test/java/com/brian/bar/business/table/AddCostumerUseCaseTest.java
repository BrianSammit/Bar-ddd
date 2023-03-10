package com.brian.bar.business.table;

import com.brian.bar.business.commons.EventRepository;
import com.brian.bar.domain.table.command.AddCostumerCommand;
import com.brian.bar.domain.table.event.CostumerAdded;
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
class AddCostumerUseCaseTest {
    @Mock
    private EventRepository eventRepository;
    private AddCostumerUseCase addCostumerUseCase;

    @BeforeEach
    void setup(){
        addCostumerUseCase = new AddCostumerUseCase(eventRepository);
    }

    @Test
    void successfulScenario() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TableCreated tableCreated = new TableCreated(
                5,
                "costumerID"
        );
        tableCreated.setAggregateRootId("tableID");

        AddCostumerCommand addCostumerCommand = new AddCostumerCommand(
                "costumerID",
                "Costumer name",
                "tableID"
        );

        Mockito.when(eventRepository.findByAggregatedRootId(addCostumerCommand.getTableID()))
                .thenAnswer(invocationOnMock ->  {
                    List<DomainEvent> eventList = new ArrayList<DomainEvent>();
                    eventList.add(tableCreated);
                    return eventList;
                });

        Mockito.when(eventRepository.saveEvent(ArgumentMatchers.any(CostumerAdded.class)))
                .thenAnswer(invocationOnMock -> {
                    return  invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = addCostumerUseCase.apply(addCostumerCommand);

        Assertions.assertEquals(1,domainEventList.size());
        Assertions.assertNotEquals(3,domainEventList.size());
        Assertions.assertEquals("tableID",domainEventList.get(0).aggregateRootId());
        Assertions.assertNotEquals("table",domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals("Costumer name", domainEventList.get(0).getClass().getMethod("getName").invoke(domainEventList.get(0)));
        Assertions.assertNotEquals( "name", domainEventList.get(0).getClass().getMethod("getName").invoke(domainEventList.get(0)));
    }

}