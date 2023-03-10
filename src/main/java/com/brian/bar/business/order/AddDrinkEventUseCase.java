package com.brian.bar.business.order;

import com.brian.bar.business.commons.EventRepository;
import com.brian.bar.business.commons.UseCaseForEvent;
import com.brian.bar.domain.drink.values.DrinkID;
import com.brian.bar.domain.order.Order;
import com.brian.bar.domain.order.event.DrinkAdded;
import com.brian.bar.domain.order.values.OrderID;
import com.brian.bar.generic.DomainEvent;

import java.util.List;
import java.util.stream.Collectors;

public class AddDrinkEventUseCase implements UseCaseForEvent<DrinkAdded> {

    private final EventRepository eventRepository;
    public AddDrinkEventUseCase(EventRepository eventsRepository) {
        this.eventRepository = eventsRepository;
    }
    @Override
    public List<DomainEvent> apply(DrinkAdded event) {
        List<DomainEvent> orderEvents = eventRepository.findByAggregatedRootId(event.getOrderID());
        Order order = Order.from(OrderID.of(event.getOrderID()), orderEvents);
        order.addDrink(DrinkID.of(event.getDrinkID()), event.getName(), event.getPrice(), event.getModification(), event.getOrderID());
        return order.getUncommittedChanges().stream().map(eventRepository::saveEvent).collect(Collectors.toList());

    }
}
