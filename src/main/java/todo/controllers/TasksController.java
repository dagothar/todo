package todo.controllers;

import java.util.List;
import javax.validation.Valid;
import org.joda.time.LocalDate;
import todo.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import todo.models.CurrentUser;
import todo.services.TaskService;

/**
 *
 * @author dagothar
 */
@Controller
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    TaskService taskService;

    @Autowired
    Validator validator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    /* SHOW TASKS */
    @RequestMapping(value = "/{date}", method = RequestMethod.GET)
    public String showTasksForDate(
            @PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            Model m,
            Authentication auth
    ) {
        CurrentUser user = (CurrentUser) auth.getPrincipal();

        /* tasks for this 'date' */
        List<Task> tasks = taskService.findTasksByAuthorAndDate(user.getId(), date);
        m.addAttribute("tasks", tasks);

        /* percentage completed */
        int progress = taskService.calculatePercentageOfCompletedTasks(tasks);
        m.addAttribute("progress", progress);

        /* for pagination */
        m.addAttribute("prevDate", date.plusDays(-1));
        m.addAttribute("nowDate", date);
        m.addAttribute("nextDate", date.plusDays(1));
        m.addAttribute("nowMonth", date.toString("yyyy/MM"));

        /* new task form */
        if (!m.containsAttribute("newTask")) {
            Task newTask = new Task(0l, 0l, false, "", date);
            m.addAttribute("newTask", newTask);
        }

        return "todo";
    }

    @RequestMapping(value = "/today", method = RequestMethod.GET)
    public String showTasksForToday() {

        LocalDate today = new LocalDate();

        return "redirect:/tasks/" + today.toString("yyyy-MM-dd");
    }

    /* EDIT TASKS */
    @RequestMapping(value = "/{date}/set", method = RequestMethod.GET)
    public String setTaskStatus(
            @PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam("id") Long id,
            @RequestParam("status") boolean status
    ) {
        Task task = taskService.findTaskById(id);
        task.setStatus(!task.getStatus());
        taskService.updateTask(task);

        return "redirect:/tasks/" + date.toString("yyyy-MM-dd");
    }

    /* REMOVE TASKS */
    @RequestMapping(value = "/{date}/remove", method = RequestMethod.GET)
    public String removeTask(
            @PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam("id") Long id
    ) {
        Task task = taskService.findTaskById(id);
        taskService.removeTask(task);

        return "redirect:/tasks/" + date.toString("yyyy-MM-dd");
    }

    /* ADD TASKS */
    @RequestMapping(value = "/{date}", method = RequestMethod.POST)
    public String addTask(
            @PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @Valid Task task,
            BindingResult result,
            RedirectAttributes attr,
            Authentication auth
    ) {
        
        if (result.hasErrors()) {
            
            attr.addFlashAttribute("org.springframework.validation.BindingResult.newTask", result);
            attr.addFlashAttribute("newTask", task);

            return "redirect:/tasks/" + date.toString("yyyy-MM-dd");
        }

        CurrentUser user = (CurrentUser) auth.getPrincipal();
        task.setAuthor(user.getId());
        taskService.addTask(task);

        return "redirect:/tasks/" + date.toString("yyyy-MM-dd");
    }

}
