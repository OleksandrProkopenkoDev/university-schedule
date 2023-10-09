package ua.foxminded.task31.service;

import ua.foxminded.task31.dto.UniversityDataDto;

public interface InitializationService {

    UniversityDataDto generateInitialData();

    void generateSchedule();

    void fillDbWithGeneratedData();

}
