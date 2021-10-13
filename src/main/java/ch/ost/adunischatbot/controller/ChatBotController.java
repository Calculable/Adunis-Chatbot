package ch.ost.adunischatbot.controller;

import ch.ost.adunischatbot.model.ChatBotAnswer;
import ch.ost.adunischatbot.model.UserInput;
import ch.ost.adunischatbot.service.ChatBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.google.cloud.dialogflow.v2.QueryResult;

@Controller
public class ChatBotController {

    @Autowired
    ChatBotService chatBotService;

    @GetMapping(value = {"/", "/index"})
	public String openHomePage(Model model) {
        return "index";
	}

	@PostMapping(value = {"/", "/index"})
	public String addComment(UserInput input
			, Model model) {

        System.out.println(input.getUserText());
        ChatBotAnswer answer = chatBotService.sendMessage(input.getUserText());
		model.addAttribute("answer", answer);
        return "index";
	}

}
