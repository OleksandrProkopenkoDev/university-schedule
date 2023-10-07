package ua.foxminded.task31.service.impl;

import lombok.RequiredArgsConstructor;
import ua.foxminded.task31.entity.*;
import ua.foxminded.task31.repository.StudentRepository;
import ua.foxminded.task31.service.GenerationService;

import java.util.List;

@RequiredArgsConstructor
public class GenerationServiceImpl implements GenerationService {

    @Override
    public List<Student> generateStudents(int amount) {
        return null;
    }

    @Override
    public List<Course> generateCourses(int amount) {
        return null;
    }

    @Override
    public List<Group> generateGroups(int amount) {
        return null;
    }

    @Override
    public List<Teacher> generateTeachers(int amount) {
        return null;
    }

    @Override
    public List<Classroom> generateClassrooms(int amount) {
        return null;
    }

    @Override
    public List<Curriculum> generateCurriculum(int groupsNumber, int coursesNumber) {
        return null;
    }
}
