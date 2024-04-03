package bibliotecaSpark.service;

import bibliotecaSpark.exception.EntityNotFoundException;
import bibliotecaSpark.model.Schedule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class ScheduleService {

    protected static final Map<Integer, Schedule> scheduleDb = new HashMap<>();

    public static void deleteById(int itemId) throws EntityNotFoundException {
        if(scheduleDb.get(itemId) == null)
            throw new EntityNotFoundException("Schedule not found");
        scheduleDb.remove(itemId);
    }

    public static Collection<? extends Schedule> list() {
        return scheduleDb.values();
    }

}
