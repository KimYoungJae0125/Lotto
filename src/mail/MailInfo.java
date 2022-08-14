package mail;

public class MailInfo {
    String getHost() {
        //smtp 서버명
        //여기서는 네이버
        return "smtp.naver.com";
    }

    String getPort() {
        //smtp 서버 포트
        return "465";
    }
    String getSenderMail() {
        //보내는 사람 메일
            return "";
    }
    String getSenderMailPassword() {
        //보내는 사람 메일 패스워드
        return "";
    }
    String getRecipientMail() {
        //받을 사람 메일
        return "";
    }

}
