package com.brian.bar.business.table;

import com.brian.bar.business.commons.EventRepository;
import com.brian.bar.business.commons.UseCaseForCommand;
import com.brian.bar.domain.table.Table;
import com.brian.bar.domain.table.command.AddWaiterCommand;
import com.brian.bar.domain.table.values.TableID;
import com.brian.bar.domain.table.values.WaiterID;
import com.brian.bar.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddWaiterUseCase implements UseCaseForCommand<AddWaiterCommand> {

    private final EventRepository eventRepository;

    public AddWaiterUseCase(EventRepository eventsRepository) {
        this.eventRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(AddWaiterCommand command) {
        List<DomainEvent> tableEvents = eventRepository.findByAggregatedRootId(command.getTableID());
        Table table = Table.from(TableID.of(command.getTableID()), tableEvents);
        table.addWaiter(WaiterID.of(command.getWaiterID()), command.getName());
        return table.getUncommittedChanges().stream().map(eventRepository::saveEvent).toList();
    }
}