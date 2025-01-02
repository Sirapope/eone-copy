package th.or.set.setportal.eonereport.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


@Slf4j()
@Service
public class SmdService {

    public static LocalDateTime getEndDate(int year, String fiscal) {
        return convertToLocalDateTimeViaInstant(DateUtil.calcDateAsOf(year, 9, fiscal));
    }

    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
