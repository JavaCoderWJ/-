package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Status;

public interface AdminService {

	public Status adminLogin(HttpServletRequest request, String adminName, String adminPassword);

	public Status getAllItems();

	public Status getAllFeedback();

	public Status getFeedbackById(int feedback_id);

	public Status getAllItemsCheckPojo();

	public Status insertIntoItems(HttpServletRequest request, HttpServletResponse response, int check_id);

	public Status itemsNotPass(HttpServletRequest request, HttpServletResponse response, int check_id);
}
