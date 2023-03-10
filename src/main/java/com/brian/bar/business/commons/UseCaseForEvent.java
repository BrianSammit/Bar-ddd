package com.brian.bar.business.commons;

import com.brian.bar.generic.DomainEvent;

import java.util.List;

public interface UseCaseForEvent <T extends DomainEvent>{
    List<DomainEvent> apply(T event);
}

