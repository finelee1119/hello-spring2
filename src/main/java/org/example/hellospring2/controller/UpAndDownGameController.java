package org.example.hellospring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class UpAndDownGameController {
    private int randomNumber;

    @GetMapping("/gameboard")
    public String gameboard(Model model) {

        Random random = new Random();
        randomNumber = random.nextInt(100) + 1; // 1 ~ 100 사이 숫자

        System.out.println("randomNumber = " + randomNumber);

        model.addAttribute("message", "게임을 시작합니다.");
        return "board";
    }

    @PostMapping("/guess")
    public String guess(@RequestParam("guess") int guessNumber, Model model) {
        if (guessNumber < randomNumber) {
            model.addAttribute("message", "Up! 더 큰 숫자를 입력하세요");
        } else if (guessNumber > randomNumber) {
            model.addAttribute("message", "Down! 더 작은 숫자를 입력하세요");
        } else {
            model.addAttribute("message", "정답입니다!" + randomNumber + "입니다.");
        }
        return "board";
    }
}
