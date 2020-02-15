
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
import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.dao.proxy.BoardDaoProxy;
import sinyj0622.mytrip.dao.proxy.MemberDaoProxy;
import sinyj0622.mytrip.dao.proxy.PlanDaoProxy;
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

	String serverAddr = null;
	int port = 0;

	Deque<String> commandStack = new ArrayDeque<>();
	Queue<String> commandQueue = new LinkedList<>();

	HashMap<String, Command> commandMap;

	public ClientApp() {
		
		try {
			serverAddr = prompt.inputString("서버? ");
			port = prompt.inputInt("포트? ");

		} catch (Exception e) {
			System.out.println("서버 주소 또는 포트 번호가 유효하지 않습니다!");
			keyboard.close();
			return;
		}
		
		this.commandMap = new HashMap<>();

		BoardDao boardDao = new BoardDaoProxy(serverAddr, port);
		MemberDao memberDao = new MemberDaoProxy(serverAddr, port);
		PlanDao planDao = new PlanDaoProxy(serverAddr, port);

		commandMap.put("/board/list", new BoardListCommand(boardDao));
		commandMap.put("/board/add", new BoardAddCommand(boardDao, prompt));
		commandMap.put("/board/detail", new BoardDetailCommand(boardDao, prompt));
		commandMap.put("/board/update", new BoardUpdateCommand(boardDao, prompt));
		commandMap.put("/board/delete", new BoardDeleteCommand(boardDao, prompt));
		commandMap.put("/member/list", new MemberListCommand(memberDao));
		commandMap.put("/member/add", new MemberAddCommand(memberDao, prompt));
		commandMap.put("/member/detail", new MemberDetailCommand(memberDao, prompt));
		commandMap.put("/member/update", new MemberUpdateCommand(memberDao, prompt));
		commandMap.put("/member/delete", new MemberDeleteCommand(memberDao, prompt));
		commandMap.put("/plan/list", new PlanListCommand(planDao));
		commandMap.put("/plan/add", new PlanAddCommand(planDao, prompt));
		commandMap.put("/plan/detail", new PlanDetailCommand(planDao, prompt));
		commandMap.put("/plan/update", new PlanUpdateCommand(planDao, prompt));
		commandMap.put("/plan/delete", new PlanDeleteCommand(planDao, prompt));

		commandMap.put("/server/stop", () -> {
			try (Socket socket = new Socket(serverAddr, port);
					ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
					ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
				out.writeUTF("/server/stop");
				out.flush();
				System.out.println("서버: " + in.readUTF());
				System.out.println("안녕!");
			} catch (Exception e) {

			}
		});

	}

	public void service() {
		

		while(true) {
			String command;
			command = prompt.inputString("\n명령> ");

			if (command.length() == 0) {
				continue;
			} else if (command.equals("history")) {
				printCommandHistory(commandStack.iterator());
				continue;
			} else if (command.equals("history2")) {
				printCommandHistory(commandQueue.iterator());
				continue;
			} else if (command.equals("quit")) {
				break;
			}

			commandStack.push(command);
			commandQueue.offer(command);

			processCommand(command);

		}
		keyboard.close();
	}

	private void processCommand(String command) {

		try {
			Command commandHandler = commandMap.get(command);

			if (commandHandler == null) {
				System.out.println("실행할 수 없는 명령입니다.");
				return;
			} else {
				commandHandler.execute();
			}

		} catch (Exception e) {
			System.out.println("명령어 처리 중 예외 발생:");
			e.printStackTrace();
		}
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
