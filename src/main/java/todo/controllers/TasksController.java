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
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import todo.dao.TaskDao;
import todo.models.CurrentUser;

/**
 *
 * @author dagothar
 */
@Controller
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    TaskDao taskDao;

    @Autowired
    Validator validator;
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String tasks() {
        return "redirect:/tasks/today";
    }
    
    @RequestMapping(value = "/yesterday", method = RequestMethod.GET)
    public String yesterday() {
        LocalDate date = new LocalDate().minusDays(1);

        return "redirect:/tasks/" + date.toString("yyyy-MM-dd");
    }
    
    @RequestMapping(value = "/today", method = RequestMethod.GET)
    public String today() {
        LocalDate date = new LocalDate();

        return "redirect:/tasks/" + date.toString("yyyy-MM-dd");
    }
    
    @RequestMapping(value = "/tomorrow", method = RequestMethod.GET)
    public String tomorrow() {
        LocalDate date = new LocalDate().plusDays(1);

        return "redirect:/tasks/" + date.toString("yyyy-MM-dd");
    }

    @RequestMapping(value = "/{date}", method = RequestMethod.GET)
    public String tasksForDate(
            @PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            Model m,
            Authentication auth
    ) {
        /* who is the user? */
        CurrentUser user = (CurrentUser)auth.getPrincipal();
        
        /* tasks for this 'date' */
        List<Task> tasks = taskDao.findTasksByAuthorIdAndDate(user.getId(), date);
        m.addAttribute("tasks", tasks);

        /* percentage completed */
        int completed = 0;
        for (Task task : tasks) {
            if (task.getStatus()) {
                ++completed;
            }
        }
        int n = tasks.size();
        int percentComplete = (int) (100.0 * completed / (n > 0 ? n : 1));
        m.addAttribute("percentCompleted", percentComplete);

        /* for pagination */
        m.addAttribute("prevDate", date.plusDays(-1));
        m.addAttribute("nowDate", date);
        m.addAttribute("nextDate", date.plusDays(1));

        /* new task form */
        if (!m.containsAttribute("newTask")) {
            Task newTask = new Task(0l, user.getId(), false, "", new LocalDate());
            m.addAttribute("newTask", newTask);
        }

        return "todo";
    }

    @RequestMapping(value = "/{date}/set", method = RequestMethod.GET)
    public String setTaskStatus(
            @PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam("id") Long id,
            @RequestParam("status") boolean status
    ) {
        taskDao.setTaskStatus(id, status);

        return "redirect:/tasks/" + date.toString("yyyy-MM-dd");
    }

    @RequestMapping(value = "/{date}/remove", method = RequestMethod.GET)
    public String removeTask(
            @PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam("id") Long id,
            Authentication auth
    ) {
        CurrentUser user = (CurrentUser)auth.getPrincipal();
        
        taskDao.removeTask(id);

        return "redirect:/tasks/" + date.toString("yyyy-MM-dd");
    }

    @RequestMapping(value = "/{date}", method = RequestMethod.POST)
    public String addNewTask(
            @PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @Valid Task task,
            BindingResult result,
            RedirectAttributes attr,
            Authentication auth
    ) {
        CurrentUser user = (CurrentUser)auth.getPrincipal();

        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println(error);
            }
            attr.addFlashAttribute("org.springframework.validation.BindingResult.newTask", result);
            attr.addFlashAttribute("newTask", task);

            return "redirect:/tasks/" + date.toString("yyyy-MM-dd");
        }

        task.setAuthorId(user.getId());
        task.setStatus(false);
        task.setDate(date);

        taskDao.addTask(task);

        return "redirect:/tasks/" + date.toString("yyyy-MM-dd");
    }

}
