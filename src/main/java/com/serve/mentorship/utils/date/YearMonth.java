package com.serve.mentorship.utils.date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class YearMonth {
    private java.time.YearMonth yearMonth;

    public YearMonth(Integer year, Integer month) {
        this.yearMonth = java.time.YearMonth.of(year, month);
    }
}
