package fr.juliuselgringo.demospringwebapp.controller;

import fr.juliuselgringo.demospringwebapp.model.Person;
import fr.juliuselgringo.demospringwebapp.repository.PersonRepository;
import fr.juliuselgringo.demospringwebapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping(value={"/","/home"})
    public String home(Model model) {
        Iterable<Person> listPersons = personService.getAllPersons();
        model.addAttribute("listPersons", listPersons);
        return "home";
    }

    @GetMapping(value="/createperson")
    public String createPerson(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "createperson";
    }

    @GetMapping(value="/updateperson/{id}")
    public String updatePerson(@PathVariable("id") final Integer id, Model model) {
        Person person = personService.getPerson(id);
        model.addAttribute("person", person);
        return "updateperson";
    }

    @GetMapping(value="/deleteperson/{id}")
    public ModelAndView deletePerson(@PathVariable("id") final Integer id) {
        personService.deletePerson(id);
        return new ModelAndView("redirect:/");
    }

    @PostMapping(value="/saveperson")
    public ModelAndView savePerson(@ModelAttribute Person person) {
        personService.savePerson(person);
        return new ModelAndView("redirect:/");
    }


}
