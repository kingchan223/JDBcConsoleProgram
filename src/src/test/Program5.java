package src.test;

import java.sql.SQLException;

import src.com.newlecture.app.console.NoticeConsole;

public class Program5 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		NoticeConsole console = new NoticeConsole();
		
		Exit:
		while(true){
			console.printNoticeList();
			int menu = console.inputNoticeMenu();
			
			switch(menu) {
			case 1://상세조회
				console.seeDetail();
				break;
			case 2://이전
				console.movePrevList();
				break;
			case 3://다음
				console.moveNextList();
				break;
			case 4://글쓰기
				console.insertPost();
				//console.printNoticeList();
				break;
			case 5://검색
				console.inputSearchWord();
				console.printNoticeList();
				break;
			case 6://종료
				System.out.println("Bye!!!");
				break Exit;
			default:
				System.out.println("메뉴에 없는 번호를 입려하셨습니다.");
				break;
			}
		}
	}
}
