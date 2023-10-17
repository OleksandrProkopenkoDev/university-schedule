package ua.foxminded.task31.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.foxminded.task31.dataGeneration.impl.GenerationServiceImpl;
import ua.foxminded.task31.model.dto.UniversityDataDto;
import ua.foxminded.task31.entity.Group;
import ua.foxminded.task31.entity.Lesson;
import ua.foxminded.task31.model.Position;
import ua.foxminded.task31.model.Schedule;
import ua.foxminded.task31.service.InitializationService;

import java.util.Map;

class InitializationServiceImplTest {

    private InitializationService underTest;

    @BeforeEach
    void setUp() {

        underTest = new InitializationServiceImpl(new GenerationServiceImpl());
    }

    @Test
    void _shouldGenerateSchedule() {
        // Given
        UniversityDataDto universityDataDto = underTest.generateInitialData();
        // When
        Schedule schedule = underTest.generateSchedule(universityDataDto);
        Group group = universityDataDto.getGroups().get(0);
        // Then
        System.out.println("University schedule:");
        schedule.print();

        System.out.println("\nschedule for "+group.getName()+":");
        Map<Position, Lesson> lessonMap = schedule.forGroup(group);
        schedule.printForGroup(lessonMap);
    }
}