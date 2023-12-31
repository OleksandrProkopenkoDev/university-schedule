package ua.foxminded.task31.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.foxminded.task31.model.entity.Group;
import ua.foxminded.task31.service.GroupService;
import ua.foxminded.task31.service.ScheduleService;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@AllArgsConstructor
public class DataController {

    private final GroupService groupService;
    private final ScheduleService scheduleService;

    @GetMapping("/schedule")
    public String showSchedule(Model model, @RequestParam(required = false) Map<String, String> params) {
        scheduleService.prepareSchedule(model, params);
        return "schedule";
    }


    @GetMapping("/groups")
    public String showGroups(Model model) {
        List<Group> groups = groupService.findAllGroups();
        model.addAttribute("groups", groups);
        return "groups";
    }

    @GetMapping("/schedule-constructor")
    public String showScheduleConstructor(Model model) {
        return "schedule-constructor";
    }


}
