package jtm.activity18;

import jakarta.servlet.http.HttpServletResponse;
import jtm.activity17.Teacher;
import jtm.activity17.TeacherManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/", produces = "text/html;charset=UTF-8")
public class JettyController {

    TeacherManager manager = new TeacherManager();

    @GetMapping("")
    @ResponseBody
    public String homePage(@RequestParam(value = "name", required = false) String name,
                           HttpServletResponse response) {
        String sb = """
                <a href='/insertTeacher'>Insert teacher<a><br/>
                <a href='/findTeacher'>Find teacher<a><br/>
                <a href='/deleteTeacher'>Delete teacher<a><br/>
                """;
        response.setStatus(HttpServletResponse.SC_OK);
        return sb;
    }

    @GetMapping("insertTeacher")
    @ResponseBody
    public String insertTeacher(@RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "surname", required = false) String surname,
                                HttpServletResponse response) {
        if (name == null && surname == null) {
            return """
                    <form action=''>
                    Name: <input type='text' name='name' value=''><br/>
                    Surname: <input type='text' name='surname' value=''><br/>
                    <input type='submit' value='Insert'></form><br/>
                    <a href='/'>Back</a>\n""";
        }

        if (name == null || surname == null || name.isEmpty() || surname.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "false<br/>\n<a href='/'>Back</a>\n";
        }

        boolean result = manager.insertTeacher(name, surname, "Undefined");
        response.setStatus(result ? HttpServletResponse.SC_OK : HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return result + "<br/>\n<a href='/'>Back</a>\n";
    }

    @GetMapping("findTeacher")
    @ResponseBody
    public String findTeacher(@RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "surname", required = false) String surname,
                              HttpServletResponse response) {
        if (name == null && surname == null) {
            return """
                    <form action=''>
                    Name: <input type='text' name='name' value=''><br/>
                    Surname: <input type='text' name='surname' value=''><br/>
                    <input type='submit' value='Find'></form><br/>
                    <a href='/'>Back</a>\n""";
        }

        List<Teacher> teachers = manager.findTeacher(name, surname);

        StringBuilder result = new StringBuilder("""
                <form action=''>
                Name: <input type='text' name='name' value=''><br/>
                Surname: <input type='text' name='surname' value=''><br/>
                <input type='submit' value='Find'></form><br/>
                <table>\n""");

        for (Teacher t : teachers) {
            result.append("<tr>\n")
                    .append("<td>").append(t.getId()).append("</td>\n")
                    .append("<td>").append(t.getFirstName()).append("</td>\n")
                    .append("<td>").append(t.getLastName()).append("</td>\n")
                    .append("</tr>\n");
        }

        result.append("</table><br>\n<a href='/'>Back</a>\n");
        return result.toString();
    }

    @GetMapping("deleteTeacher")
    @ResponseBody
    public String deleteTeacher(@RequestParam(value = "id", required = false) String idStr,
                                HttpServletResponse response) {
        if (idStr == null) {
            return """
                    <form action=''>
                    ID: <input type='text' name='id' value=''><br/>
                    <input type='submit' value='Delete'></form><br/>
                    <a href='/'>Back</a>\n""";
        }

        try {
            int id = Integer.parseInt(idStr);
            boolean result = manager.deleteTeacher(id);
            response.setStatus(HttpServletResponse.SC_OK);
            return result + "<br/>\n<a href='/'>Back</a>\n";
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "false<br/>\n<a href='/'>Back</a>\n";
        }
    }
}
