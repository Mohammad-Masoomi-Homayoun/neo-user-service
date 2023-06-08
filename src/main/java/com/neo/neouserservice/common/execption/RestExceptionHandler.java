package com.neo.neouserservice.common.execption;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<MessagesResponse> handleRuntimeExceptions(Exception ex) {
        MessagesResponse response = new MessagesResponse();
        response.addMessage(new Message(ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @Data
    @NoArgsConstructor
    public static class Message {

        private String message;
        private String field;

        public Message(String message) {
            this.message = message;
        }

        public Message(String field, String message) {
            this.field = field;
            this.message = message;
        }

    }

    @Data
    @NoArgsConstructor
    public static class MessagesResponse {
        private List<Message> messages = new ArrayList<>();

        public void addMessage(Message message) {
            getMessages().add(message);
        }
    }
}
