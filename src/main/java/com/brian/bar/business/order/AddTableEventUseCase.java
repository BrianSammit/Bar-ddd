package com.brian.bar.business.order;

import com.brian.bar.business.commons.EventRepository;
import com.brian.bar.business.commons.UseCaseForEvent;
import com.brian.bar.domain.order.Order;
import com.brian.bar.domain.order.event.TableAdded;
import com.brian.bar.domain.order.values.OrderID;
import com.brian.bar.domain.table.values.TableID;
import com.brian.bar.generic.DomainEvent;

import java.util.List;
import java.util.stream.Collectors;

public class AddTableEventUseCase implements UseCaseForEvent<TableAdded> {
    private final EventRepository eventRepository;
    public AddTableEventUseCase(EventRepository eventsRepository) {
        this.eventRepository = eventsRepository;
    }
    @Override
    public List<DomainEvent> apply(TableAdded event) {
        List<DomainEvent> orderEvents = eventRepository.findByAggregatedRootId(event.getOrderID());
        Order order = Order.from(OrderID.of(event.getOrderID()), orderEvents);
        order.addTable(TableID.of(event.getTableID()), event.getTableNum(), event.getCostumerID(), event.getOrderID());
        return order.getUncommittedChanges().stream().map(eventRepository::saveEvent).collect(Collectors.toList());

    }
}
