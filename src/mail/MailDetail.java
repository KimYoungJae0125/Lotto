package mail;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class MailDetail {

    public String subject() {
        return "자바 프로그램";
    }
    public String body() {
        String[] body = new String[6];
        body[0] = "<h1>금주의 로또 번호</h1>";
        for(int i = 0; i<5; i++) {
            body[i+1] = "<p>" + lottoNumber() + "</p>";
        }

        return String.join(
                System.getProperty("line.separator")
                , body
        );
    }

    private String lottoNumber() {
        return new HashSet<>(){{
            while(size() < 6) {
                add(new Random().ints(1, 45).findAny().getAsInt());
            }
        }}.stream()
          .sorted()
          .map(String::valueOf)
          .collect(Collectors.joining(", ", "[ ", " ]"));
    }
}
