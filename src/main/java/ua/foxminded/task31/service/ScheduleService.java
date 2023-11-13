package ua.foxminded.task31.service;

import org.springframework.ui.Model;

import java.util.Map;

public interface ScheduleService {
    void prepareSchedule(Model model, Map<String, String> params);
}
