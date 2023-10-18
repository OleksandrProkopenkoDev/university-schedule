package ua.foxminded.task31.service;

import ua.foxminded.task31.model.dto.UniversityDataDto;
import ua.foxminded.task31.model.Schedule;

public interface InitializationService {

    UniversityDataDto generateInitialData();


    Schedule generateSchedule(UniversityDataDto universityDataDto);

    void fillDbWithGeneratedData(UniversityDataDto universityDataDto, Schedule globalSchedule);

}
