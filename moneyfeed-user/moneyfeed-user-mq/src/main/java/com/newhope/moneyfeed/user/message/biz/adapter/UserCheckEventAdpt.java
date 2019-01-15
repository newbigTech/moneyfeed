package com.newhope.moneyfeed.user.message.biz.adapter;


import com.newhope.moneyfeed.mq.adapter.Channel;
import com.newhope.moneyfeed.mq.adapter.Message;
import com.newhope.moneyfeed.mq.adapter.event.CommonEvent;
import com.newhope.moneyfeed.mq.adapter.event.EventType;
import com.newhope.moneyfeed.mq.adapter.impl.DefaultMessageAdapter;
import com.newhope.moneyfeed.mq.exception.MessageProcessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Event Adapter: 应用消费者处理器
 * @author RDC-201
 *
 */
@Component
public class UserCheckEventAdpt extends DefaultMessageAdapter {
	public final  static Logger logger = LoggerFactory.getLogger(UserCheckEventAdpt.class);



	public UserCheckEventAdpt() {
		super(Channel.Type.Common);
	}

	@Override
	public boolean match(EventType eventType) {
        return EventType.USER_CHECK_EVENT.name().equals(eventType.name());
    }
	
	@Override
	public void eventHandle(Message message, CommonEvent event) throws MessageProcessException {

		logger.info("----------接受到消息------");

	}

}
