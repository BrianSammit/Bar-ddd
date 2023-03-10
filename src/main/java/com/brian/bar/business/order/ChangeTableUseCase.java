package com.brian.bar.business.order;

import com.brian.bar.business.commons.EventRepository;
import com.brian.bar.business.commons.UseCaseForCommand;
import com.brian.bar.domain.order.Order;
import com.brian.bar.domain.order.command.ChangeTableCommand;
import com.brian.bar.domain.order.values.OrderID;
import com.brian.bar.domain.table.values.TableID;
import com.brian.bar.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChangeTableUseCase implements UseCaseForCommand<ChangeTableCommand> {

    private final EventRepository eventRepository;

    public ChangeTableUseCase(EventRepository eventsRepository) {
        this.eventRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ChangeTableCommand command) {
        List<DomainEvent> orderEvent = eventRepository.findByAggregatedRootId(command.getOrderID());
        Order order = Order.from(OrderID.of(command.getOrderID()), orderEvent);
        order.addTable(TableID.of(command.getTableID()), command.getTableNum(), command.getCostumerID(), command.getOrderID());
        return order.getUncommittedChanges().stream().map(eventRepository::saveEvent).toList();
    }
}