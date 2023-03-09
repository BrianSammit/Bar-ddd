package com.brian.bar.business.table;

import com.brian.bar.business.commons.EventRepository;
import com.brian.bar.business.commons.UseCaseForCommand;
import com.brian.bar.domain.drink.values.DrinkID;
import com.brian.bar.domain.order.Order;
import com.brian.bar.domain.order.command.AddDrinkCommand;
import com.brian.bar.domain.order.values.OrderID;
import com.brian.bar.domain.table.Table;
import com.brian.bar.domain.table.command.AddCostumerCommand;
import com.brian.bar.domain.table.values.CostumerID;
import com.brian.bar.domain.table.values.TableID;
import com.brian.bar.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddCostumerUseCase implements UseCaseForCommand<AddCostumerCommand> {

    private final EventRepository eventRepository;

    public AddCostumerUseCase(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }
    @Override
    public List<DomainEvent> apply(AddCostumerCommand command) {
        List<DomainEvent> tableEvents = eventRepository.findByAggregatedRootId(command.getTableID());
        Table table = Table.from(TableID.of(command.getTableID()), tableEvents);
        table.addCostumer(CostumerID.of(command.getCostumerID()), command.getName());
        return table.getUncommittedChanges().stream().map(eventRepository::saveEvent).collect(Collectors.toList());
    }
}
