package org.example.hellospring2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private static final int DIGITS = 3; // 숫자 야구의 자릿수
    private static final int MAX_NUMBER = 9; // 최대 숫자
    private List<Integer> secretNumber; // 난수

    public List<Integer> getSecretNumber() { // 난수는 직접 접근이 아니라 메서드를 통해 난수에 접근 -> public
        return secretNumber;
    }

    public Game() { // 파라미터가 없는 생성자를 default 생성자라고 부른다.
        generateSecretNumber(); // Game 객체가 생성되면서 SecretNumber 가 생성된다.
    }

    /* 난수의 조건
    * 1. 난수의 자릿수는 DIGITS을 따른다.
    * 2. 난수의 최대 숫자는 MAX_NUMBER의 범위를 따른다.
    * 3. 난수의 각 자리의 숫자는 서로 중복되지 않는다. (예를 들어 111, 122 불가)
     */


    private void generateSecretNumber() { // 난수를 생성해주는 메서드, 생성자에 의해서만 호출이 되므로 -> private
        Random random = new Random();
        secretNumber = new ArrayList<>(); // ArrayList를 secretNumber라는 이름으로 인스턴스화 했다.

        while(secretNumber.size() < DIGITS) {
            int digit = random.nextInt(MAX_NUMBER + 1); // bound 숫자 '미만'의 랜덤 숫자가 생성됨.
            if (!secretNumber.contains(digit)) { // secretNumber에 digit이 포함되어 있지 않다면,
                secretNumber.add(digit); // secretNumber에 추가한다.
            }
        }
        System.out.println("secretNumber = " + secretNumber.toString());
    }

    public int checkGuess(List<Integer> guess) {
        int strike = 0;
        int ball = 0;

        for (int i = 0; i < DIGITS; i++) {
            if (secretNumber.get(i).equals(guess.get(i))) {
                strike++;
            } else if (secretNumber.contains(guess.get(i))) {
                ball++;
            }
        }

        if (strike == DIGITS) {
            return -1; // GAME OVER
        } else {
            return strike * 10 + ball;
        }

    }
}
