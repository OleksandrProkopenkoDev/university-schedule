package ua.foxminded.task31.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.foxminded.task31.model.Schedule;
import ua.foxminded.task31.model.entity.Lesson;
import ua.foxminded.task31.repository.LessonRepository;
import ua.foxminded.task31.service.ScheduleService;

import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private LessonRepository lessonRepository;

    @Override
    public Schedule getUniversitySchedule() {
        List<Lesson> lessons = lessonRepository.findAll();
        return new Schedule(lessons);
    }
}
