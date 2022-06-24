package SOLID_Code_IT;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Pomodoro {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello! Введите команду");
        String[] cmd = new Scanner(System.in).nextLine().split(" ");

        int workMinets = 25;
        int breakMinets = 5;
        int count = 1;
        int doubler = 2;

        int sizePrint = 30;

        boolean isCallHelp = false;

        for (int i = 0; i < cmd.length; i++) {
            switch (cmd[i]) {
                case "-help" -> {
                    printHelpMsg();
                    isCallHelp = true;
                }
                case "-w" -> workMinets = Integer.parseInt(cmd[++i]);
                case "-b" -> breakMinets = Integer.parseInt(cmd[++i]);
                case "-count" -> count = Integer.parseInt(cmd[++i]);
                case "-m" -> doubler = Integer.parseInt(cmd[++i]);
            }
        }

        if (!isCallHelp) {
            System.out.printf("Работаем %d min " +
                    "отдыхаем %d min, кол-во подходов %d\n", workMinets, breakMinets, count);
            long startTime = System.currentTimeMillis();
            for (int i = 1; i <= count; i++) {
                timer(workMinets, breakMinets, sizePrint);
                if (count > 1) {
                    workMinets *= doubler;
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Pomodoro таймер истёк " + (endTime - startTime) / (1060));
        }
    }

    private static void printHelpMsg() {
        System.out.println(
                "\n\nPomodoro - сделай свое время более эффективным\n");
        System.out.println(
                "	-w <time>: время работы, сколько хочешь работать.\n");
        System.out.println(
                "	-b <time>: время отдыха, сколько хочешь отдыхать.\n");
        System.out.println(
                "	-count <count>: количество итераций.\n");
        System.out.println(
                "	--help: меню помощи.\n");
    }

    private static void timer(int workTime, int breakTime, int sizeProgressbar) throws InterruptedException {
        printProgress("Рабочее время:: ", workTime, sizeProgressbar);

        printProgress("Свободное время:: ", breakTime, sizeProgressbar);
    }

    private static void printProgress(String process, int time, int size) throws InterruptedException {
        int length;
        int rep;
        length = 60 * time / size;
        rep = 60 * time / length;
        int stretchb = size / (3 * time);
        for (int i = 1; i <= rep; i++) {
            double x = i;
            x = 1.0 / 3.0 * x;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            double w = time * stretchb;
            double percent = (x / w) * 1000;
            x /= stretchb;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            percent = Math.round(percent);
            percent /= 10;
            System.out.print(process + percent + "% " + (" ").repeat(5 - (String.valueOf(percent).length())) + "[" + ("#").repeat(i) + ("-").repeat(rep - i) + "]    ( " + x + "min / " + time + "min )" + "\r");
            TimeUnit.SECONDS.sleep(length);
        }
    }
}