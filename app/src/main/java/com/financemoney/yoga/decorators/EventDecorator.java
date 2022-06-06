package com.financemoney.yoga.decorators;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;
import java.util.Collection;
import java.util.HashSet;

public class EventDecorator implements DayViewDecorator {
    private int color;
    private HashSet<CalendarDay> dates;

    public EventDecorator(int i, Collection<CalendarDay> collection) {
        this.color = i;
        this.dates = new HashSet<>(collection);
    }

    public boolean shouldDecorate(CalendarDay calendarDay) {
        return this.dates.contains(calendarDay);
    }

    public void decorate(DayViewFacade dayViewFacade) {
        dayViewFacade.addSpan(new DotSpan(5.0f, this.color));
    }
}
