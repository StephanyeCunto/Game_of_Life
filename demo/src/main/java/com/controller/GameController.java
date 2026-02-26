package com.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;

import java.util.concurrent.*;

@Controller
public class GameController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();

    public GameController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/start")
    public void start(GameRequest request) {

        int size = request.getSize();
        int time = request.getTime();

        BoardController board = new BoardController(size);

        scheduler.scheduleAtFixedRate(() -> {
            try {

                boolean[][] snapshot = board.getBoardSnapshot();

                messagingTemplate.convertAndSend(
                        "/topic/game",
                        snapshot
                );

                board.generateTransition();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }, 0, time, TimeUnit.MILLISECONDS);
    }
}