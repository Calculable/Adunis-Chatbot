package ch.ost.adunischatbot.controller;

import ch.ost.adunischatbot.model.UserInput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChatBotController {

    @GetMapping(value = {"/", "/index"})
	public String openHomePage(Model model) {
        return "index";
	}

	@PostMapping(value = {"/", "/index"})
	public String addComment(UserInput input
			, Model model) {

        System.out.println(input.getUserText());
		model.addAttribute("botAnswer", "Antwort");
        return "index";
	}

}
