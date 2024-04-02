package bibliotecaSpark.service;

import bibliotecaSpark.model.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class ScheduleService {

    protected static final Map<Integer, Schedule> scheduleDb = new HashMap<>();

    public static void add(Schedule schedule){
        scheduleDb.put(schedule.getScheduleId(), schedule);
    }

    public static void delete (int scheduleId){
        scheduleDb.remove(scheduleId);
    }

    public static Collection<? extends Schedule> list() {
        return scheduleDb.values();
    }

    public static Schedule getById(int itemId) {
        return scheduleDb.get(itemId);
    }

}
