package com.brian.bar.business.order;

import com.brian.bar.business.commons.EventRepository;
import com.brian.bar.business.commons.UseCaseForCommand;
import com.brian.bar.domain.drink.values.DrinkID;
import com.brian.bar.domain.order.Order;
import com.brian.bar.domain.order.command.CreateOrderCommand;
import com.brian.bar.domain.order.values.Modification;
import com.brian.bar.domain.order.values.OrderID;
import com.brian.bar.domain.order.values.Status;
import com.brian.bar.domain.table.values.TableID;
import com.brian.bar.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateOrderUseCase implements UseCaseForCommand<CreateOrderCommand> {

    private final EventRepository eventRepository;

    public CreateOrderUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<DomainEvent> apply(CreateOrderCommand command) {
        Order order = new Order(
                OrderID.of(command.getId()),
                new Status(command.getStatus()),
                new Modification(command.getModification()),
                TableID.of(command.getTableID()),
                DrinkID.of(command.getDrinkID())
        );
        return order.getUncommittedChanges().stream().map(eventRepository::saveEvent).collect(Collectors.toList());
    }
}
