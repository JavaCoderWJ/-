package util;

import java.util.List;

import pojo.UserCommentPojo;

public class Status1 {

	/* 0：失败，1：成功 用于快速判断该请求是否成功以及处理显示错误信息 */
	private Integer statue;
	/* 用于描述状态信息，比如如果请求出错用于描述错误信息等 */
	private String message;
	/* 将用于返回的信息，如果需要返回的话 */
	private Object data;
	// 统计返回的条数
	private int numbers = 0;

	/* 返回的状态码 */
	// 执行成功
	public static int SUCCESS = 1;
	// 执行失败
	public static int FAILED = 0;
	// 参数错误
	public static int PARAMETER_ERROR = -100;

	public Status1(){};

	public Status1(Integer statue, String message, Object data, int numbers) {
		this.statue = statue;
		this.message = message;
		this.data = data;
		this.numbers=numbers;
	}

	/**
	 * @return the statue
	 */
	public Integer getStatue() {
		return statue;
	}

	/**
	 * @param statue
	 *            the statue to set
	 */
	public void setStatue(Integer statue) {
		this.statue = statue;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the numbers
	 */
	public int getNumbers() {
		return numbers;
	}

	/**
	 * @param numbers
	 *            the numbers to set
	 */
	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}

	/**
	 * @return the sUCCESS
	 */
	public static int getSUCCESS() {
		return SUCCESS;
	}

	/**
	 * @param sUCCESS
	 *            the sUCCESS to set
	 */
	public static void setSUCCESS(int sUCCESS) {
		SUCCESS = sUCCESS;
	}

	/**
	 * @return the fAILED
	 */
	public static int getFAILED() {
		return FAILED;
	}

	/**
	 * @param fAILED
	 *            the fAILED to set
	 */
	public static void setFAILED(int fAILED) {
		FAILED = fAILED;
	}

	/**
	 * @return the pARAMETER_ERROR
	 */
	public static int getPARAMETER_ERROR() {
		return PARAMETER_ERROR;
	}

	/**
	 * @param pARAMETER_ERROR
	 *            the pARAMETER_ERROR to set
	 */
	public static void setPARAMETER_ERROR(int pARAMETER_ERROR) {
		PARAMETER_ERROR = pARAMETER_ERROR;
	}

	public Status1 itemsCheck(List<UserCommentPojo> listUserCommentPojo) {
		numbers = listUserCommentPojo.size();
		if (numbers != 0) {
			return new Status1(1, "获取成功", listUserCommentPojo, numbers);
		} else {
			return new Status1(1, "返回为空", listUserCommentPojo, 0);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Status1 [statue=" + statue + ", message=" + message + ", data=" + data + ", numbers=" + numbers + "]";
	}

}
