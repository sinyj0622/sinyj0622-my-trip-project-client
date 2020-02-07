
package sinyj0622.mytrip;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import sinyj0622.mytrip.handler.Command;
import sinyj0622.util.Prompt;

public class ClientApp {

  Scanner keyboard = new Scanner(System.in);
  Prompt prompt = new Prompt(keyboard);

  public void service() {

    Deque<String> commandStack = new ArrayDeque<>();
    Queue<String> commandQueue = new LinkedList<>();

    HashMap<String, Command> commandMap = new HashMap<>();

    String command;
    while (true) {
      System.out.print("명령> ");
      command = keyboard.nextLine();


      if (command.length() == 0)
        continue;

      if (command.equals("quit")) {
        System.out.println("안녕!");
        break;
      } else if (command.equals("history")) {
        printCommandHistory(commandStack.iterator());
        continue;
      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue.iterator());
        continue;
      }

      commandStack.push(command);

      commandQueue.offer(command);


      Command commandHandler = commandMap.get(command);

      if (commandHandler != null) {
        try {
          commandHandler.execute();
        } catch (Exception e) {
          System.out.printf("명령어 실행 중 오류 발생: %s\n", e.getMessage());
        }
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
    }

    keyboard.close();
  }

  public void printCommandHistory(Iterator<String> iterator) {
    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      count++;

      if ((count % 5) == 0) {
        System.out.print(":");
        String str = keyboard.nextLine();
        if (str.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }

  public static void main(String[] args) {


    System.out.println("클라이언트 여행 관리 시스템입니다");

    ClientApp app = new ClientApp();
    app.service();


    /*
     * String serverAddr = null; int port = 0;
     *
     * Scanner keyScan = new Scanner(System.in); Prompt prompt = new Prompt(keyScan);
     *
     * try { serverAddr = prompt.inputString("서버? ");
     *
     * port = prompt.inputInt("포트? ");
     *
     *
     * } catch (Exception e) { System.out.println("서버 주소 또는 포트 번호가 유효하지 않습니다!"); keyScan.close();
     * return; }
     *
     * try (Socket socket = new Socket(serverAddr, port); PrintStream out = new
     * PrintStream(socket.getOutputStream()); Scanner in = new Scanner(socket.getInputStream())) {
     *
     *
     * System.out.println("서버와 연결되었음!");
     *
     *
     * System.out.print("서버에 보낼 메세지: "); String sendMsg = keyScan.nextLine();
     *
     *
     * out.println(sendMsg); System.out.println("서버에 메세지를 전송하였음!");
     *
     *
     * String message = in.nextLine(); System.out.println("서버로부터 메세지를 수신하였음!");
     *
     *
     * System.out.println("서버: " + message);
     *
     *
     * System.out.println("서버와 연결을 끊었음"); } catch (Exception e) { System.out.println("예외 발생 :");
     * e.printStackTrace(); }
     *
     * keyScan.close();
     */
  }
}
