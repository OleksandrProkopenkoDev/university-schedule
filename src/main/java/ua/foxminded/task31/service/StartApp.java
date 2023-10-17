package ua.foxminded.task31.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import ua.foxminded.task31.entity.Group;
import ua.foxminded.task31.entity.Lesson;
import ua.foxminded.task31.model.Position;
import ua.foxminded.task31.model.Schedule;
import ua.foxminded.task31.model.dto.UniversityDataDto;

import java.util.Map;

@AllArgsConstructor
@Slf4j
@Service
public class StartApp implements ApplicationRunner {

    private InitializationService initializationService;

    @Override
    public void run(ApplicationArguments args) {
        log.info("Starting app data initialization...");
        UniversityDataDto universityDataDto = initializationService.generateInitialData();
        Schedule schedule = initializationService.generateSchedule(universityDataDto);

        Group group = universityDataDto.getGroups().get(0);
        System.out.println("University schedule:");
        schedule.print();

        System.out.println("\nschedule for "+group.getName()+":");
        Map<Position, Lesson> lessonMap = schedule.forGroup(group);
        schedule.printForGroup(lessonMap);
    }
}
