package com.brian.bar.business.drink;

import com.brian.bar.business.commons.EventRepository;
import com.brian.bar.domain.drink.command.CreateDrinkCommand;
import com.brian.bar.domain.drink.event.DrinkCreated;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateDrinkUseCaseTest {
    @Mock
    private EventRepository eventRepository;
    private CreateDrinkUseCase createDrinkUseCase;

    @BeforeEach
    void setup(){
        createDrinkUseCase = new CreateDrinkUseCase(eventRepository);
    }
    @Test
    void successfulScenario() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        CreateDrinkCommand createDrinkCommand = new CreateDrinkCommand(
                "drinkID",
                "Drink name",
                6.30f,
                "Special modification"
        );
        DrinkCreated drinkCreated = new DrinkCreated(
                "Drink name",
                6.30f,
                "Special modification"
        );

        drinkCreated.setAggregateRootId("drinkID");
        Mockito.when(eventRepository.saveEvent(ArgumentMatchers.any(DrinkCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return  invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEvents = createDrinkUseCase.apply(createDrinkCommand);


        Assertions.assertEquals(1, domainEvents.size());
        Assertions.assertNotEquals(2, domainEvents.size());
        Assertions.assertEquals("drinkID", domainEvents.get(0).aggregateRootId());
        Assertions.assertNotEquals("test1", domainEvents.get(0).aggregateRootId());
        Assertions.assertEquals("Special modification", domainEvents.get(0).getClass().getMethod("getModification").invoke(domainEvents.get(0)));
        Assertions.assertNotEquals("Modification", domainEvents.get(0).getClass().getMethod("getModification").invoke(domainEvents.get(0)));
    }

}
