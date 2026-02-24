
package com.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.*;

@RestController
public class GameController {

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @GetMapping("/start")
    public SseEmitter start(@RequestParam int size,@RequestParam int time) {
        SseEmitter emitter = new SseEmitter();
        BoardController board = new BoardController(size);

        scheduler.scheduleAtFixedRate(() -> {
            try {
                boolean[][] boardSnapshot = board.getBoardSnapshot();
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < size; i++){
                    for (int j = 0; j < size; j++) {
                        sb.append(boardSnapshot[i][j] ? "* " : "- ");
                    }
                    sb.append("\n");
                }

                emitter.send(sb.toString());

                board.generateTransition();

            } catch (Exception e) {
                emitter.complete();
            }

        }, 0, time, TimeUnit.MILLISECONDS);

        return emitter;
    }
}