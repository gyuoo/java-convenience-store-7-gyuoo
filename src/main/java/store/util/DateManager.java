package store.util;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;

public class DateManager {

    public LocalDateTime getNow() {
        return DateTimes.now();
    }
}
