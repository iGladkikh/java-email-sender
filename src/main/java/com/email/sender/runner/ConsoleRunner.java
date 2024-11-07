package com.email.sender.runner;

import com.email.sender.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner {
    private final Scanner scanner = new Scanner(System.in);
    private final MailSenderService mailSender;

    @Autowired
    public ConsoleRunner(MailSenderService mailSender) {
        this.mailSender = mailSender;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void consoleRun() {
        while (true) {
            try {
                startMainDialog();

                String command = scanner.nextLine();
                switch (command) {
                    case "1":
                        startMailSenderDialog();
                        System.out.println("Готово!");
                        break;
                    case "0":
                        System.out.println("Завершение работы");
                        return;
                }
            } catch (Exception e) {
                System.out.println("Ошибочка вышла");
                System.out.println(e.getMessage());
            }
        }
    }

    private void startMainDialog() {
        System.out.println("\nВыберите команду:");
        System.out.println("1 - Отправить письмо");
        System.out.println("0 - Выход");
    }

    private void startMailSenderDialog() {
        System.out.println("Введите email получателя");
        String email = scanner.nextLine();
        System.out.println("Введите тему");
        String subject = scanner.nextLine();
        System.out.println("Введите сообщение");
        String body = scanner.nextLine();
        System.out.println("Отправка...");

        mailSender.send(email, subject, body);
    }
}
