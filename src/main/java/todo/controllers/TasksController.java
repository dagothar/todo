package todo.controllers;

import static java.lang.Math.toIntExact;
import todo.dao.TaskDao;
import java.util.List;
import java.util.Optional;
import org.joda.time.LocalDate;
import todo.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author dagothar
 */
@Controller
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    TaskDao taskDao;
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String tasks() {
        LocalDate date = new LocalDate();
        
        return "redirect:/tasks/" + date.toString("yyyy-MM-dd");
    }

    @RequestMapping(value = "/{date}", method = RequestMethod.GET)
    public String tasksForDate(
            @PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            Model m
    ) {
        List<Task> tasks = taskDao.findTasksByAuthorIdAndDate(1, date);
        m.addAttribute("tasks", tasks);

        /* for pagination */
        m.addAttribute("prevDate", date.plusDays(-1));
        m.addAttribute("nowDate", date);
        m.addAttribute("nextDate", date.plusDays(1));

        return "todo";
    }

    @RequestMapping(value = "/setStatus", method = RequestMethod.GET)
    public String setStatus(
            @RequestParam("id") Long id,
            @RequestParam("status") boolean status
    ) {
        taskDao.setTaskStatus(toIntExact(id), status);

        return "redirect:/tasks";
    }

}
