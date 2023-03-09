package com.brian.bar.business.table;

import com.brian.bar.business.commons.EventRepository;
import com.brian.bar.business.commons.UseCaseForCommand;
import com.brian.bar.domain.table.Table;
import com.brian.bar.domain.table.command.RemoveCostumerCommand;
import com.brian.bar.domain.table.values.TableID;
import com.brian.bar.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RemoveCostumerUseCase implements UseCaseForCommand<RemoveCostumerCommand> {
    private final EventRepository eventRepository;

    public RemoveCostumerUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    @Override
    public List<DomainEvent> apply(RemoveCostumerCommand command) {
        List<DomainEvent> tableEvent = eventRepository.findByAggregatedRootId(command.getTableID());
        Table table = Table.from(TableID.of(command.getTableID()), tableEvent);
        table.removeCostumer(command.getCostumerID());
        return table.getUncommittedChanges().stream().map(eventRepository::saveEvent).collect(Collectors.toList());
    }
}
