package com.brian.bar.business.commons;


import com.brian.bar.generic.Command;
import com.brian.bar.generic.DomainEvent;

import java.util.List;

public interface UseCaseForCommand <T extends Command>{
    List<DomainEvent> apply(T command);
}
