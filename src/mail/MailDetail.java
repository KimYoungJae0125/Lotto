package mail;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class MailDetail {

    public String subject() {
        return "자바 프로그램";
    }
    public String body() {
        return String.join(
                System.getProperty("line.separator")
                , "<h1>금주의 로또 번호<h1>"
                , "<p>" + lottoNumber() + "<p>"
        );
    }

    private String lottoNumber() {
        Set<Integer> lottoNumbers = new HashSet<>();
        while(lottoNumbers.size() < 6) {
            lottoNumbers.add(new Random().ints(1, 45).findAny().getAsInt());
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        sb.append(lottoNumbers.stream().sorted().map(String::valueOf).collect(Collectors.joining(", ")));
        sb.append(" ]");

        return sb.toString();
    }
}
