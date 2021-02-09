package com.challenge.demo.util;

import com.challenge.demo.exception.InvalidMessagesException;
import com.challenge.demo.exception.MessageNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Message {

    public static String getMessage( String[] ...allMessages) throws Exception {
        List<List<String>> messages = new ArrayList<>(validateMessages(allMessages));

        //busco el mensaje con mas elementos
        List<String> finalMessage = new ArrayList<>(searchBestMessage(messages));

        //alineo el mensaje final con los siguientes
        alignMessage(messages, finalMessage);

        completeMessage(messages, finalMessage);

        removeShift(finalMessage);

        if(finalMessage.contains("")) {
            throw new MessageNotFoundException();
        }

        return String.join(" ", finalMessage);
    }

    private static List<List<String>> validateMessages(String[]... allMessages) throws Exception {
        List<List<String>> messages = new ArrayList<>();

        //delete null message
        for(String[] message : allMessages) {
            if(message != null) {
                List<String> aux = Arrays.asList(message);
                if ((aux.size() - Collections.frequency(aux, "")) > 0)
                    messages.add(aux);
            }
        }

        if(messages.isEmpty())
            throw new InvalidMessagesException();

        return messages;
    }

    private static List<String> searchBestMessage(List<List<String>> messages) {
        int maxElements = 0;
        List<String> finalMessage = new ArrayList<>();

        for(List<String> message : messages) {
            int elements = message.size() - Collections.frequency(message, "");
            if(elements > maxElements) {
                maxElements = elements;
                finalMessage = message;
            }
        }
        //lo elimino de la lista
        messages.remove(finalMessage);

        return finalMessage;
    }

    private static void deleteShift(List<String> finalMessage) {
        List<String> aux = finalMessage;

        while(finalMessage.get(0).equals("")){
            aux.remove(0);
        }
        finalMessage = aux;
    }

    private static void alignMessage(List<List<String>> messages, List<String> finalMessage) {
        messages.forEach(message -> {
            message.forEach(word -> {
                if (!word.equals("") && finalMessage.contains(word)) {
                    while ((finalMessage.indexOf(word) - message.indexOf(word) > 0) && finalMessage.get(0).equals("") )
                         finalMessage.remove(0);
                }
            });
        });
    }

    private static void removeShift(List<String> finalMessage) {
        if(finalMessage.get(0).equals("")) {
            deleteShift(finalMessage);
        } else if(finalMessage.get(finalMessage.size()-1).equals("")) {
            Collections.reverse(finalMessage);
            deleteShift(finalMessage);
            Collections.reverse(finalMessage);
        }
    }

    private static void completeMessage(List<List<String>> messages, List<String> finalMessage) throws Exception {
        List<List<String>> auxMessages = new ArrayList<>(messages);

        for(int i = 0; auxMessages.size() != 0 && i < messages.size() ; i++ ) {
            for (List<String> message : messages) {
                AtomicInteger shift = new AtomicInteger();
                AtomicBoolean hasShift = new AtomicBoolean(false);

                if (finalMessage.containsAll(message)) {
                    auxMessages.remove(message);
                } else {
                    getShift(finalMessage, message, shift, hasShift);
                    if (hasShift.get()) {
                        fillMessage(finalMessage, message, shift);
                        auxMessages.remove(message);
                    }
                    hasShift.set(false);
                }
            }
        }
        if(auxMessages.size()>0){//si hay un mensaje que no pudo ser eliminado
            throw new InvalidMessagesException();
        }
    }

    private static void fillMessage(List<String> finalMessage, List<String> message, AtomicInteger shift) {
        message.forEach((word) -> {
            if (!word.equals("") && !finalMessage.contains(word)) {
                finalMessage.set(message.indexOf(word) -shift.get(), word);
            }
        });
    }

    private static void getShift(List<String> finalMessage, List<String> message, AtomicInteger shift, AtomicBoolean hasShift) {
        message.forEach((word) -> {
            if (!word.equals("") && finalMessage.contains(word)) {
                shift.set(message.indexOf(word) - finalMessage.indexOf(word));
                hasShift.set(true);
            }
        });
    }

}
