package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//证明是一个配置文件(使用@Component也可以,因为点入后会发现@Configuration还是使用了@Component)
@Configuration
// 证明是一个切面
@Aspect
public class ControllerAOP {
	// 环绕aop
	// execution表达式 此表达式表示扫描controller下所有类的所有方法都执行此aop
	@Around("execution (* controller..*.*(..))")
	public Object testAop(ProceedingJoinPoint pro,HttpServletRequest request) throws Throwable {
		// 获取response
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		// 核心设置
		// 允许客户端携带跨域cookie，此时origin值不能为“*”，只能为指定单一域名
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");     

		// 执行调用的方法
		Object proceed = pro.proceed();
		return proceed;
	}

}
