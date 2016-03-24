package todo.controllers;

import static java.lang.Math.toIntExact;
import todo.dao.TaskDao;
import java.util.List;
import org.joda.time.LocalDate;
import todo.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author dagothar
 */
@Controller
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    TaskDao taskDao;
    
//    @Autowired
//    Validator validator;

//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        binder.setValidator(validator);
//    }

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
        /* tasks for this 'date' */
        List<Task> tasks = taskDao.findTasksByAuthorIdAndDate(1, date);
        m.addAttribute("tasks", tasks);
        
        /* percentage completed */
        int completed = 0;
        for (Task task : tasks) {
            if (task.getStatus()) ++completed;
        }
        int n = tasks.size();
        int percentComplete = (int)(100.0 * completed / (n > 0 ? n : 1));
        m.addAttribute("percentCompleted", percentComplete);

        /* for pagination */
        m.addAttribute("prevDate", date.plusDays(-1));
        m.addAttribute("nowDate", date);
        m.addAttribute("nextDate", date.plusDays(1));
        
        /* new task form */
        Task newTask = new Task(0, 1, false, "", new LocalDate());
        m.addAttribute("newTask", newTask);

        return "todo";
    }

    @RequestMapping(value = "/{date}/set", method = RequestMethod.GET)
    public String setStatus(
            @PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam("id") Long id,
            @RequestParam("status") boolean status
    ) {
        taskDao.setTaskStatus(toIntExact(id), status);

        return "redirect:/tasks/" + date.toString("yyyy-MM-dd");
    }
    
    @RequestMapping(value = "/{date}/remove", method = RequestMethod.GET)
    public String removeTask(
            @PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam("id") Long id
    ) {
        taskDao.removeTask(toIntExact(id));

        return "redirect:/tasks/" + date.toString("yyyy-MM-dd");
    }
    
    @RequestMapping(value = "/{date}", method = RequestMethod.POST)
    public String addNewTask(
            @PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            Task task,
            BindingResult result,
            RedirectAttributes attr
    ) {
        
        if (result.hasErrors()) {
            //attr.addFlashAttribute("org.springframework.validation.BindingResult.task", result);
            //attr.addFlashAttribute("newTask", task);

            //return "redirect:/tasks/" + date.toString("yyyy-MM-dd");
        }
        
        task.setAuthorId(1);
        task.setStatus(false);
        task.setDate(date);
        
        taskDao.addTask(task);
        
        return "redirect:/tasks/" + date.toString("yyyy-MM-dd");
    }

}
