package src.com.newlecture.app.console;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import src.com.newlecture.app.entity.Notice;
import src.com.newlecture.app.service.NoticeService;

public class NoticeConsole {
	
	private NoticeService service;
	private int page;
	private String searchWord;
	private String searchFeild;
	
	
	public NoticeConsole() {
		service = new NoticeService();
		page = 1;
		searchWord = "";
		searchFeild = "TITLE";
	}

	public void printNoticeList() throws ClassNotFoundException, SQLException {
		List<Notice> list = service.getList( page, searchFeild, searchWord);
		int count = service.getCount();
		int lastPage = (count/10);
		lastPage = count%10 == 0 ? lastPage:lastPage+1;
		System.out.println("-------------------------------------");
		System.out.printf("<공지사항> 총 %d개 게시글\n",count);
		System.out.println("-------------------------------------");
		
		for(Notice n: list) {
			System.out.printf("%d. %s / %s / %s \n",
					n.getId(),
					n.getTitle(),
					n.getWriterId(),
					n.getRegDate());
		}
		System.out.println("-------------------------------------");
		System.out.printf("            %d/%d pages\n",page,lastPage);

	}

	public int inputNoticeMenu() {
		Scanner scan = new Scanner(System.in);
		System.out.print(" 1.상세조회/ 2.이전 / 3.다음 / 4.글쓰기 /  5.검색 / 6.종료 / >숫자를 정수형으로 입력.>>>");
		System.out.println();
		System.out.println();
		System.out.println();
		String menu_ = scan.nextLine();
		int menu = Integer.parseInt(menu_);
		
		
		
		return menu;
	}

	public void movePrevList() {
		if(page == 1) {
			System.out.println("!!!!!이미 첫 페이지 입니다!!!!!");
			return;
		}
		page--;
		
	}

	public void moveNextList() throws ClassNotFoundException, SQLException {
		int count = service.getCount();
		int lastPage = (count/10);
		lastPage = count%10 == 0 ? lastPage:lastPage+1;
		
		if(page == lastPage) {
			System.out.println("!!!!!이미 마지막 페이지 입니다!!!!!");
			return;
		}
		page++;
		
	}

	public void inputSearchWord() {
		Scanner scan = new Scanner(System.in);
		System.out.println("검색 범주(글 제목, 내용, 작성자)중 하나를 입력하세요.");
		System.out.println(">>>");
		searchFeild = scan.nextLine();
		
		System.out.println("검색어를 입력하세요.");
		searchWord = scan.nextLine();
	}

	public void insertPost() throws ClassNotFoundException, SQLException {
		String writerId = "";
		String title = "";
		String content = "";
		
		Scanner scan = new Scanner(System.in);
		System.out.println("작성자ID를 입력하세요:");
		writerId = scan.nextLine();
		System.out.println("글 제목을 입력하세요:");
		title = scan.nextLine();
		System.out.println("내용을 입력하세요:");
		content = scan.nextLine();
		
		Notice insertNotice = new Notice();
		insertNotice.setWriterId(writerId);
		insertNotice.setTitle(title);
		insertNotice.setContent(content);
		if(1==service.insert(insertNotice))
			System.out.println("글이 성공적으로 등록되었습니다.");
		else
			System.out.println("글 등록에 실패하였습니다.");
	}

	public void seeDetail() throws ClassNotFoundException, SQLException {
		int postId = 0;
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("상세조회하고 싶은 글의 번호를 입력하세요:");
		postId = scan.nextInt();
		Notice post = service.getPost(postId);
		System.out.println("---------------------------");
//		id,
//		title,
//		writerId,
//		regDate,
//		content,
//		hit,
//		files
		System.out.println("글 번호:"+post.getId());
		System.out.println("글 제목:"+post.getTitle());
		System.out.println("작성자:"+post.getWriterId());
		System.out.println("작성일:"+post.getRegDate());
		System.out.println("조회수:"+post.getHit());
		System.out.println("내용:");
		System.out.println(post.getContent());
		service.upHit(postId, post.getHit());
	}
}