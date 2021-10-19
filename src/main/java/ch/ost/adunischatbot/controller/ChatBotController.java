package ch.ost.adunischatbot.controller;

import ch.ost.adunischatbot.model.ChatBotAnswer;
import ch.ost.adunischatbot.model.UserInput;
import ch.ost.adunischatbot.service.ChatBotService;
import ch.ost.adunischatbot.service.SoundPlayer;
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
        model.addAttribute("chatHistory", chatBotService.getChatHistory());
        return "index";
    }

	@PostMapping(value = {"/", "/index"})
	public String getBotAnswer(UserInput input
			, Model model) {

        System.out.println(input.getUserText());
        ChatBotAnswer answer = chatBotService.sendMessage(input.getUserText());
		model.addAttribute("answer", answer);
        model.addAttribute("chatHistory", chatBotService.getChatHistory());

        SoundPlayer.playClip(answer.getOutputAudio()); //ok this is a little hack ;) the audio should be played on the client, not on the server but it works for the demo :D
        return "index";
	}

}
