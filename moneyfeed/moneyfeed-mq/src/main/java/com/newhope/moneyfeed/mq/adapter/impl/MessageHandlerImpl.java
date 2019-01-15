package com.newhope.moneyfeed.mq.adapter.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.newhope.moneyfeed.common.util.PackageUtil;
import com.newhope.moneyfeed.mq.adapter.Channel;
import com.newhope.moneyfeed.mq.adapter.EventAdapter;
import com.newhope.moneyfeed.mq.adapter.MessageAdapter;
import com.newhope.moneyfeed.mq.adapter.MessageHandler;
import com.newhope.moneyfeed.mq.adapter.event.CommonEvent;
import com.newhope.moneyfeed.mq.adapter.event.EventType;
import com.newhope.moneyfeed.mq.exception.MessageProcessException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.newhope.moneyfeed.mq.adapter.Message;

@Component
public class MessageHandlerImpl implements MessageHandler {
	private static final Logger logger = LoggerFactory.getLogger(MessageHandlerImpl.class);
	
	List<MessageAdapter> adapters = new ArrayList<MessageAdapter>();
	List<EventAdapter> eventAdapters = new ArrayList<EventAdapter>();
	//adpater类扫描路径
	@Value("${newhope.rabbitmq.adapter.classpath}")
	private String adapterClassPath;
	private final String adapterClassPattern = "^[a-zA-Z.]+Adapter$";
	//event adpter匹配模式
	private final String eventAdptClassPattern = "^[a-zA-Z.]+EventAdpt$";
	
	@Autowired
	private ApplicationContext context;
	
	private DefaultMessageAdapter defaultMessageAdapter = new DefaultMessageAdapter();

	/**
	 * 将adapter bean放入adapters
	 * 通过包扫描,反射动态加载adapter
	 * @throws ClassNotFoundException 
	 * 
	 */
	@PostConstruct
	private void setAdapters() throws ClassNotFoundException {
		if(StringUtils.isEmpty(adapterClassPath)){
			return;
		}
		List<Class<?>> classList = PackageUtil.getClass(adapterClassPath, adapterClassPattern);
		for (Class<?> clazz : classList) {
			if (MessageAdapter.class.isAssignableFrom(clazz)) {
				adapters.add((MessageAdapter) context.getBean(clazz));
			}
		}
	}
	
	/**
	 * 将所有event放入events
	 * 通过包扫描,反射动态加载event
	 * @throws ClassNotFoundException 
	 * 
	 */
	@PostConstruct
	private void setEvents() throws ClassNotFoundException {
		if(StringUtils.isEmpty(adapterClassPath)){
			return;
		}
		List<Class<?>> classList = PackageUtil.getClass(adapterClassPath, eventAdptClassPattern);
		for (Class<?> clazz : classList) {
			if (EventAdapter.class.isAssignableFrom(clazz)) {
				eventAdapters.add((EventAdapter) context.getBean(clazz));
			}
		}
	}
	
	private MessageAdapter getMessageAdapter(Channel.Type channelType) {
		for (MessageAdapter adapter : adapters) {
			if (adapter.support(channelType)) {
				return adapter;
			}
		}
		return new DefaultMessageAdapter(channelType);
	}

	private EventAdapter getEventAdapter(EventType eventType) {
		for (EventAdapter adapter : eventAdapters) {
			if (adapter.match(eventType)) {
				return adapter;
			}
		}
		return null;
	}
	
	@Override
	public void process(Message message, Channel channel, CommonEvent event) throws MessageProcessException {
		if (event == null) {
			getMessageAdapter(channel.getType()).handle(message, channel);
		} else {
			defaultMessageAdapter.handle(message, channel, event, getEventAdapter(event.getType()));
		}
	}

	@Override
	public void process(List<Message> messages, Channel channel, CommonEvent event) throws MessageProcessException {
		MessageAdapter adapter = getMessageAdapter(channel.getType());
		for (Message message : messages) {
			adapter.handle(message, channel);
		}
	}

	@Override
	public void process(Message message, List<Channel> channels, CommonEvent event) throws MessageProcessException {
		for (Channel channel : channels) {
			process(message, channel, event);
		}
	}
}
