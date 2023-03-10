package com.brian.bar.business.drink;

import com.brian.bar.business.commons.EventRepository;
import com.brian.bar.business.commons.UseCaseForCommand;
import com.brian.bar.domain.drink.Drink;
import com.brian.bar.domain.drink.command.CreateDrinkCommand;
import com.brian.bar.domain.drink.values.DrinkID;
import com.brian.bar.domain.drink.values.Name;
import com.brian.bar.domain.drink.values.Price;
import com.brian.bar.domain.order.values.Modification;
import com.brian.bar.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateDrinkUseCase implements UseCaseForCommand<CreateDrinkCommand> {

    private final EventRepository eventRepository;

    public CreateDrinkUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<DomainEvent> apply(CreateDrinkCommand command) {
        Drink drink = new Drink(
                DrinkID.of(command.getId()),
                new Name(command.getName()),
                new Price(command.getPrice()),
                new Modification(command.getModification())
        );
        return drink.getUncommittedChanges().stream().map(eventRepository::saveEvent).collect(Collectors.toList());
    }
}