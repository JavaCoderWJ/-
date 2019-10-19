package util;

/**
 * @description: 用于返回数据使用，一个固定的格式，便于实现接口的交互
 */
public class Status {
	/* 0：失败，1：成功 用于快速判断该请求是否成功以及处理显示错误信息 */
	private Integer statue;
	/* 用于描述状态信息，比如如果请求出错用于描述错误信息等 */
	private String message;
	/* 将用于返回的信息，如果需要返回的话 */
	private Object data;	

	/* 返回的状态码 */
	// 执行成功
	public static int SUCCESS = 1;
	// 执行失败
	public static int FAILED = 0;
	// 参数错误
	public static int PARAMETER_ERROR = -100;

	public Status(){};

	public Status(Integer statue, String message, Object data) {
		this.statue = statue;
		this.message = message;
		this.data = data;
	}

	public Integer getStatue() {
		return statue;
	}

	public void setStatue(Integer statue) {
		this.statue = statue;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	
	//用于普通数据为空检查
	public Status check(Object object){
		if (object != null&&!object.equals("")) {
			return new Status(this.SUCCESS, "返回成功", object);
		} else {
			return new Status(this.FAILED, "返回失败", null);
		}
	}

	// 用于搜索关键字分析
	public String analyze(String[] object, String goods_type) {
		for (int i = 0; i < object.length;) {
			if (goods_type.indexOf(object[i]) < 0) {
				i++;
			} else {
				goods_type = "美妆美护";
			}
		}
		return goods_type;
	}

	@Override
	public String toString() {
		return "Status{" + "statue=" + statue + ", message='" + message + '\'' + ", data=" + data + '}';
	}

	// 检验参数
	public int checkParam(Object object) {
		if (object == null || object.equals("")) {
			return 0;
		} else {
			return 1;
		}
	}
}
