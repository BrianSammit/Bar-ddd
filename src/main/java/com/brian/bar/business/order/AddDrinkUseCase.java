package com.brian.bar.business.order;

import com.brian.bar.business.commons.EventRepository;
import com.brian.bar.business.commons.UseCaseForCommand;
import com.brian.bar.domain.drink.values.DrinkID;
import com.brian.bar.domain.order.Order;
import com.brian.bar.domain.order.command.AddDrinkCommand;
import com.brian.bar.domain.order.values.OrderID;
import com.brian.bar.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddDrinkUseCase implements UseCaseForCommand<AddDrinkCommand> {

    private final EventRepository eventRepository;

    public AddDrinkUseCase(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }
    @Override
    public List<DomainEvent> apply(AddDrinkCommand command) {
        List<DomainEvent> orderEvents = eventRepository.findByAggregatedRootId(command.getOrderID());
        Order order = Order.from(OrderID.of(command.getOrderID()), orderEvents);
        order.addDrink(DrinkID.of(command.getDrinkID()), command.getName(), command.getPrice(), command.getModification(), command.getOrderID());
        return order.getUncommittedChanges().stream().map(eventRepository::saveEvent).collect(Collectors.toList());
    }
}
