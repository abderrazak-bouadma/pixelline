package com.pixel.line.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class Admin extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		UserService userService = UserServiceFactory.getUserService();
		User currentUser = userService.getCurrentUser();
		if (currentUser!=null && userService.isUserAdmin()) {
			req.setAttribute("USERID", currentUser.getUserId());
			req.getRequestDispatcher("/WEB-INF/admin.jsp").forward(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}
}
