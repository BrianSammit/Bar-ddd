package com.brian.bar.business.order;

import com.brian.bar.business.commons.EventRepository;
import com.brian.bar.business.commons.UseCaseForCommand;
import com.brian.bar.domain.drink.values.DrinkID;
import com.brian.bar.domain.order.Order;
import com.brian.bar.domain.order.command.RemoveDrinkCommand;
import com.brian.bar.domain.order.values.OrderID;
import com.brian.bar.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RemoveDrinkUseCase implements UseCaseForCommand<RemoveDrinkCommand> {
    private final EventRepository eventRepository;

    public RemoveDrinkUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    @Override
    public List<DomainEvent> apply(RemoveDrinkCommand command) {
        List<DomainEvent> orderEvents = eventRepository.findByAggregatedRootId(command.getOrderID());
        Order order = Order.from(OrderID.of(command.getOrderID()), orderEvents);
        order.removeDrink(command.getDrinkID());
        return order.getUncommittedChanges().stream().map(eventRepository::saveEvent).collect(Collectors.toList());
    }
}
