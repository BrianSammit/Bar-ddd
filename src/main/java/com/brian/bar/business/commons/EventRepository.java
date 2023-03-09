package com.brian.bar.business.commons;

import com.brian.bar.generic.DomainEvent;

import java.util.List;

public interface EventRepository {
    DomainEvent saveEvent(DomainEvent event);
    List<DomainEvent> findByAggregatedRootId(String aggregatedRootId);
}
