package com.brian.bar.business.table;

import com.brian.bar.business.commons.EventRepository;
import com.brian.bar.business.order.CreateOrderUseCase;
import com.brian.bar.domain.order.command.CreateOrderCommand;
import com.brian.bar.domain.order.event.OrderCreated;
import com.brian.bar.domain.table.command.CreateTableCommand;
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
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CreateTableUseCaseTest {
    @Mock
    private EventRepository eventRepository;
    private CreateTableUseCase createTableUseCase;

    @BeforeEach
    void setup(){
        createTableUseCase = new CreateTableUseCase(eventRepository);
    }
    @Test
    void successfulScenario() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        CreateTableCommand createTableCommand = new CreateTableCommand(
                "tableID",
                5,
                "costumer1"
        );
        TableCreated tableCreated = new TableCreated(
                5,
                "costume1"
        );

        tableCreated.setAggregateRootId("tableID");
        Mockito.when(eventRepository.saveEvent(ArgumentMatchers.any(TableCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return  invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEvents = createTableUseCase.apply(createTableCommand);


        Assertions.assertEquals(1, domainEvents.size());
        Assertions.assertNotEquals(2, domainEvents.size());
        Assertions.assertEquals("tableID", domainEvents.get(0).aggregateRootId());
        Assertions.assertNotEquals("test1", domainEvents.get(0).aggregateRootId());
        Assertions.assertEquals(5, domainEvents.get(0).getClass().getMethod("getTableNum").invoke(domainEvents.get(0)));
        Assertions.assertNotEquals(2, domainEvents.get(0).getClass().getMethod("getTableNum").invoke(domainEvents.get(0)));
    }

}