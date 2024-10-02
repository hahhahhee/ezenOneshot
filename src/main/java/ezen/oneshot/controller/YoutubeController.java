package ezen.oneshot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class YoutubeController {

    @GetMapping("/youtube")
    public String youtubeForm() {
        return "/youtubeBoard";
    }

    @GetMapping("/youtube/detail")
    public String youtubeDetail() {
        return "/youtubeDetail";
    }

}
