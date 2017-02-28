package time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class HumanReadableDuration {

	
	public static void main(String[] args) {
		
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime then = now.plusSeconds(31535973);
		LocalDateTime otherThen = now.plusSeconds(3600 * 8760);
		LocalDate thenDate = then.toLocalDate();
		LocalDate nowDate = now.toLocalDate();
		
		Period p = Period.between(nowDate, thenDate);
		
		int years = Math.abs(p.getYears());
		int months = Math.abs(p.getMonths());
		int days = Math.abs(p.getDays());
		
		Duration d = Duration.ofSeconds(Math.abs(now.until(then, ChronoUnit.SECONDS))).minusSeconds(Math.abs(ChronoUnit.SECONDS.between(now, then)));
		
		long hours = d.toHours();
		long minutes = d.minusHours(hours).toMinutes();
		
		StringBuilder builder = new StringBuilder();
		
		boolean useMinutes = true;
		if(years > 0) {
			builder.append(years).append(" year").append(years > 1 ? "s " : " ");
			useMinutes = false;
		}
		
		if(months > 0) {
			builder.append(months).append(" month").append(months > 1 ? "s " : " ");
			useMinutes = false;
		}
		
		if(days > 0) {
			builder.append(days).append(" day").append(days > 1 ? "s " : " ");
			useMinutes = false;
		}
		
		if(hours > 0) {
			builder.append(hours).append(" hour").append(hours > 1 ? "s " : " ");
		}
		
		if(minutes > 0 && useMinutes) {
			builder.append(minutes).append(" minute").append(minutes > 1 ? "s " : " ");
		}
		
		if(now.isAfter(then)) {
			builder.append("in the past");
		}
		
		if(then.isAfter(now)) {
			builder.append("in the future");
		}
		
		System.out.println(builder.toString());
	}
	
}
