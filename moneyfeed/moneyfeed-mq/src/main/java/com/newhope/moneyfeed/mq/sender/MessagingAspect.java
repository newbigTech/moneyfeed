package com.newhope.moneyfeed.mq.sender;
//package com.newhope.message.sender;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.newhope.message.cmd.Command;
//import com.newhope.message.common.Parameter;
//import com.newhope.message.util.ContextUtil;
//
//@Component
//@Aspect
//public class MessagingAspect {
//	private static final Logger logger = LoggerFactory.getLogger(MessagingAspect.class);
//
//	@Autowired
//	CommandSender commandSender;
//
//	@Around("@annotation(messagingIndicator)")
//	public Object intercept(ProceedingJoinPoint pjp, MessagingIndicator messagingIndicator) throws Throwable {
//		long startTime = System.currentTimeMillis();
//
//		Object returnObj = pjp.proceed();
//		if (messagingIndicator.checkReturn()) {
//			if (returnObj instanceof Boolean) {
//				boolean result = (Boolean) returnObj;
//				if (!result) {
//					logger.info("method invocation result is  false, no need to send message");
//					return returnObj;
//				}
//			} else if (returnObj instanceof Integer) {
//				int result = (Integer) returnObj;
//				if (result <= 0) {
//					logger.info("method invocation result " + result + ", no need to send message");
//					return returnObj;
//				}
//			}
//		}
//
//		List<Parameter> parameters = new ArrayList<Parameter>();
//		Object[] args = pjp.getArgs();
//		if (args != null) {
//			int counter = 0;
//			for (Object arg : args) {
//				logger.info("argument:" + arg);
//				Parameter param = new Parameter();
//				param.setName("param" + counter);
//				param.setValue(arg);
//				parameters.add(param);
//				counter++;
//			}
//		}
//
//		Command command = new Command();
//		command.setSource(messagingIndicator.source());
//		command.setMessageTemplateCode(messagingIndicator.messageTemplateCode());
//		command.setProcessor(messagingIndicator.processor());
//
//		Parameter method = new Parameter();
//		method.setName("method");
//		method.setValue(pjp.getSignature().getName());
//		parameters.add(method);
//
//		logger.info("messagingIndicator.processor=" + messagingIndicator.processor());
//		Map<String, Object> additionalParams = ContextUtil.getCurrentParameters();
//		for (String parameter : additionalParams.keySet()) {
//			logger.info("add parameter(" + parameter + ")=" + additionalParams.get(parameter));
//			Parameter param = new Parameter();
//			param.setName(parameter);
//			param.setValue(additionalParams.get(parameter));
//			parameters.add(param);
//		}
//
//		command.setParameters(parameters);
//		commandSender.send(command);
//
//		Long totalTime = System.currentTimeMillis() - startTime;
//		logger.info("method invocation duration:" + totalTime);
//		return returnObj;
//	}
//}
