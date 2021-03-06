package pl.lukakan.mvcfirstproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ResponseBody
    @GetMapping("/users")
    public String usersList() {
        List<User> users = userRepository.getAll();
        String result = "";
        result = users.stream()
                .map(User::toString)
                .collect(Collectors.joining("</br>"));
        return result;
    }

    @GetMapping("/add")
    public String addByUrl(@RequestParam(name = "imie", defaultValue = "") String firstName,
                           @RequestParam(name = "nazwisko") String lastName,
                           @RequestParam(name = "wiek") int age) {
        return validateAndAdd(firstName, lastName, age);
    }

    @PostMapping("/add")
    public String addByForm(@RequestParam(name = "imie", defaultValue = "") String firstName,
                            @RequestParam(name = "nazwisko") String lastName,
                            @RequestParam(name = "wiek") int age) {
        return validateAndAdd(firstName, lastName, age);
    }

    private String validateAndAdd(String firstName, String lastName, int age) {
        if (firstName.equals("")) {
            return "redirect:/err.html";
        } else {
            userRepository.add(new User(firstName, lastName, age));
            return "redirect:/success.html";
        }
    }
}
