package ua.foxminded.task31.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import ua.foxminded.task31.model.entity.Group;
import ua.foxminded.task31.model.entity.Lesson;
import ua.foxminded.task31.model.Position;
import ua.foxminded.task31.model.Schedule;
import ua.foxminded.task31.model.dto.UniversityDataDto;
import ua.foxminded.task31.repository.LessonRepository;

import java.util.Map;

@AllArgsConstructor
@Slf4j
@Service
public class StartApp implements ApplicationRunner {

    private InitializationService initializationService;
    private LessonRepository lessonRepository;

    @Override
    public void run(ApplicationArguments args) {
        log.info("Starting app data initialization...");
        if (isDataExist()) {
            log.info("App initialization finished.");
        } else {
            UniversityDataDto universityDataDto = initializationService.generateInitialData();
            Schedule schedule = initializationService.generateSchedule(universityDataDto);
            initializationService.fillDbWithGeneratedData(universityDataDto, schedule);
            log.info("App initialization finished.");

            //demo output:
            Group group = universityDataDto.getGroups().get(0);
            System.out.println("University schedule:");
            schedule.print();

            System.out.println("\nschedule for " + group.getName() + ":");
            Map<Position, Lesson> lessonMap = schedule.forGroup(group);
            schedule.printForGroup(lessonMap);
        }
    }

    private boolean isDataExist() {
        return lessonRepository.count() != 0;
    }
}
