import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        // 서울의 시간대를 설정
        ZoneId seoulZone = ZoneId.of("Asia/Seoul");
        
        // 현재 날짜를 가져옴
        LocalDate today = LocalDate.now(seoulZone);
        
        // 날짜를 "YYYY-MM-DD" 형식으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);
        
        // 날짜 출력
        System.out.println(formattedDate);
    }
}
