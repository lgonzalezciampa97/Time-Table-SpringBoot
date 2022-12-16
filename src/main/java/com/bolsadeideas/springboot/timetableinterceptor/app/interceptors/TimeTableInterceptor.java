package com.bolsadeideas.springboot.timetableinterceptor.app.interceptors;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("TimeTable")
public class TimeTableInterceptor implements HandlerInterceptor {

	@Value("${config.start.time}")
	private Integer open;
	@Value("${config.close.time}")
	private Integer close;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Calendar calendar = Calendar.getInstance();
		int time = calendar.get(Calendar.HOUR_OF_DAY);

		if (time >= open && time < close) {
			StringBuilder message = new StringBuilder("Welcome to Client´s Time Table");
			message.append(", open since ");
			message.append(open);
			message.append("HS ");
			message.append(" to ");
			message.append(close);
			message.append("HS ");
			message.append(". Thanks for visit us.");
			request.setAttribute("message", message.toString());
			return true;
		}
		response.sendRedirect(request.getContextPath().concat("/closed"));
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String message = (String) request.getAttribute("message");
		if (modelAndView != null && handler instanceof HandlerMethod) {
			modelAndView.addObject("timeTable", message);
		}
	}
}
