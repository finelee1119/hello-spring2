package org.example.hellospring2.controller;

import org.springframework.stereotype.Controller;
import org.example.hellospring2.model.Game;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/baseball")
public class BaseBallController {
    private Game game;

    @GetMapping("")
    public String goBoard(Model model) {
        // 화면을 넘겨주면서 난수를 생성한다.
        game = new Game();
        model.addAttribute("message", "숫자야구 게임을 시작합니다! 0부터 9까지의 숫자로 이루어진 3자리 숫자를 맞춰보세요.");
        return "baseball";
    }

    @PostMapping("/guess")
    public String guessNumber(@RequestParam("guess[0]") int digit1, @RequestParam("guess[1]") int digit2, @RequestParam("guess[2]") int digit3, Model model) {

        List<Integer> guessNumber = Arrays.asList(digit1, digit2, digit3);
        int result = game.checkGuess(guessNumber);

        if (result == -1) {
            model.addAttribute("message", "정답입니다!");
        } else {
            int strike = result / 10;
            int ball = result % 10;
            model.addAttribute("message", "스트라이크: " + strike + " 볼: " + ball);
        }

        return "baseball";
    }
}
