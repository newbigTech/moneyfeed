package com.newhope.moneyfeed.mq.cmd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.newhope.moneyfeed.mq.adapter.Channel;
import com.newhope.moneyfeed.mq.adapter.event.CommonEvent;
import org.apache.commons.collections.CollectionUtils;

import com.newhope.moneyfeed.mq.adapter.Message;
import com.newhope.moneyfeed.mq.common.Parameter;


public class Command implements Serializable {
	
	private static final long serialVersionUID = -6432043931146737303L;
	private String source;
	private String processor;
	private String messageTemplateCode;
	private Message message;
	private Channel channel;
	private List<Parameter> parameters = new ArrayList<Parameter>();
	
	private CommonEvent event;

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public CommonEvent getEvent() {
		return event;
	}

	public void setEvent(CommonEvent event) {
		this.event = event;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public String getMessageTemplateCode() {
		return messageTemplateCode;
	}

	public void setMessageTemplateCode(String messageTemplateCode) {
		this.messageTemplateCode = messageTemplateCode;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public Parameter getParameter(String name) {
		if (CollectionUtils.isNotEmpty(parameters)) {
			for (Parameter param : parameters) {
				if (param.getName().equals(name)) {
					return param;
				}
			}
		}
		return null;
	}

	public Parameter getParameter(int position) {
		return getParameter("param" + position);
	}
	public Object getParameterValue(String name) {
		Parameter param = getParameter(name);
		if (param != null) {
			return param.getValue();
		}
		return null;
	}
	public Object getParameterValue(int position) {
		return getParameter(position).getValue();
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public void addAllParameters(List<Parameter> parameters) {
		this.parameters.addAll(parameters);
	}

	public void addParameters(Parameter parameter) {
		this.parameters.add(parameter);
	}
}
