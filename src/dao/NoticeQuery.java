package dao;

public class NoticeQuery {
	public static final String SELECT_NOTICE_LISTS = "SELECT notice_no noticeNo, notice_title noticeTitle, create_date createDate FROM notice ORDER BY createDate DESC LIMIT 0,5";
}
