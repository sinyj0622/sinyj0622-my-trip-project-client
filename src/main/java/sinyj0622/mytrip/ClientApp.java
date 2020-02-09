
package sinyj0622.mytrip;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import sinyj0622.mytrip.handler.BoardAddCommand;
import sinyj0622.mytrip.handler.BoardDeleteCommand;
import sinyj0622.mytrip.handler.BoardDetailCommand;
import sinyj0622.mytrip.handler.BoardListCommand;
import sinyj0622.mytrip.handler.BoardUpdateCommand;
import sinyj0622.mytrip.handler.Command;
import sinyj0622.mytrip.handler.MemberAddCommand;
import sinyj0622.mytrip.handler.MemberDeleteCommand;
import sinyj0622.mytrip.handler.MemberDetailCommand;
import sinyj0622.mytrip.handler.MemberListCommand;
import sinyj0622.mytrip.handler.MemberUpdateCommand;
import sinyj0622.mytrip.handler.PlanAddCommand;
import sinyj0622.mytrip.handler.PlanDeleteCommand;
import sinyj0622.mytrip.handler.PlanDetailCommand;
import sinyj0622.mytrip.handler.PlanListCommand;
import sinyj0622.mytrip.handler.PlanUpdateCommand;
import sinyj0622.util.Prompt;

public class ClientApp {


	  Scanner keyboard = new Scanner(System.in);
	  Prompt prompt = new Prompt(keyboard);

	  public void service() {

	    String serverAddr = null;
	    int port = 0;

	    try {
	      serverAddr = prompt.inputString("서버? ");
	      port = prompt.inputInt("포트? ");

	    } catch (Exception e) {
	      System.out.println("서버 주소 또는 포트 번호가 유효하지 않습니다!");
	      keyboard.close();
	      return;
	    }

	    try (Socket socket = new Socket(serverAddr, port);
	        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
	        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

	      System.out.println("서버와 연결완료!");

	      processCommand(out, in);

	      System.out.println("서버와 연결을 끊었음!");

	    } catch (Exception e) {
	      System.out.println("예외 발생:");
	      e.printStackTrace();
	    }

	    keyboard.close();
	  }

	  private void processCommand(ObjectOutputStream out, ObjectInputStream in) {

	    Deque<String> commandStack = new ArrayDeque<>();
	    Queue<String> commandQueue = new LinkedList<>();

	    HashMap<String, Command> commandMap = new HashMap<>();
	    commandMap.put("/board/list", new BoardListCommand(out, in));
	    commandMap.put("/board/add", new BoardAddCommand(out, in, prompt));
	    commandMap.put("/board/detail", new BoardDetailCommand(out, in, prompt));
	    commandMap.put("/board/update", new BoardUpdateCommand(out, in, prompt));
	    commandMap.put("/board/delete", new BoardDeleteCommand(out, in, prompt));
	    commandMap.put("/member/list", new MemberListCommand(out, in));
	    commandMap.put("/member/add", new MemberAddCommand(out, in, prompt));
	    commandMap.put("/member/detail", new MemberDetailCommand(out, in, prompt));
	    commandMap.put("/member/update", new MemberUpdateCommand(out, in, prompt));
	    commandMap.put("/member/delete", new MemberDeleteCommand(out, in, prompt));
	    commandMap.put("/plan/list", new PlanListCommand(out, in));
	    commandMap.put("/plan/add", new PlanAddCommand(out, in, prompt));
	    commandMap.put("/plan/detail", new PlanDetailCommand(out, in, prompt));
	    commandMap.put("/plan/update", new PlanUpdateCommand(out, in, prompt));
	    commandMap.put("/plan/delete", new PlanDeleteCommand(out, in, prompt));

	    try {
	      while (true) {
	        String command;
	        command = prompt.inputString("\n명령> ");

	        if (command.length() == 0)
	          continue;

	        if (command.equals("quit") || command.equals("/server/stop")) {
	          out.writeUTF(command);
	          out.flush();
	          System.out.println("서버: " + in.readUTF());
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
	            e.printStackTrace();
	            System.out.printf("명령어 실행 중 오류 발생: %s\n", e.getMessage());
	          }
	        } else {
	          System.out.println("실행할 수 없는 명령입니다.");
	        }
	      }
	      out.flush();
	    } catch (Exception e) {
	      System.out.println("프로그램 실행 중 오류 발생!");
	    }
	    keyboard.close();

	  }

	  private void printCommandHistory(Iterator<String> iterator) {
	    int count = 0;
	    while (iterator.hasNext()) {
	      System.out.println(iterator.next());
	      count++;

	      if ((count % 5) == 0) {
	        String str = prompt.inputString(":");
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

	}
}
