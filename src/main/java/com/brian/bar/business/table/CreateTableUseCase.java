package com.brian.bar.business.table;

import com.brian.bar.business.commons.EventRepository;
import com.brian.bar.business.commons.UseCaseForCommand;
import com.brian.bar.domain.table.Table;
import com.brian.bar.domain.table.command.CreateTableCommand;
import com.brian.bar.domain.table.values.CostumerID;
import com.brian.bar.domain.table.values.TableID;
import com.brian.bar.domain.table.values.TableNum;
import com.brian.bar.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateTableUseCase implements UseCaseForCommand<CreateTableCommand> {

    private final EventRepository eventRepository;

    public CreateTableUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    @Override
    public List<DomainEvent> apply(CreateTableCommand command) {
        Table table = new Table(
                TableID.of(command.getId()),
                new TableNum(command.getTableNum()),
                CostumerID.of(command.getCostumerID())
        );
        return table.getUncommittedChanges().stream().map(eventRepository::saveEvent).collect(Collectors.toList());
    }
}
