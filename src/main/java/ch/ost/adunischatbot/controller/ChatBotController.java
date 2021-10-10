package ch.ost.adunischatbot.controller;

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
        //String answer = chatBotService.sendMessage(input.getUserText());
        QueryResult result = chatBotService.sendMessage(input.getUserText());
		model.addAttribute("botAnswer", chatBotService.getMessage(result));
		model.addAttribute("userSentiment", chatBotService.getSentiment(result));
		model.addAttribute("botConfidence", chatBotService.getConfidence(result));
        return "index";
	}

}
