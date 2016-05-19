package com.bailian.ta.util;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.bailian.ta.log.LogUtil;
import com.ibm.mq.jms.MQQueue;
import com.ibm.mq.jms.MQQueueConnectionFactory;

/**
 * 消息发送工具类
 * <p/>
 * <p>
 * <b>User:</b>zhanggm
 * <ahref="mailto:guomingzhang2008@163.com">guomingzhang2008@163.com</a>
 * </p>
 * <p>
 * <b>Date:</b>2015-12-22
 * </p>
 *
 * @author 张国明
 */

public class MQSendUtils {
	// #端口号
    private static final int APP_MQ_PORT = 1414;
	// #1381为中文
    private static final int APP_MQ_CCSID = 1381;
	// #传输类型
    private static final int APP_MQ_TRANSPORT_TYPE = 1;
    private static final String APP_MQ_CHANNEL = "SYSTEM.DEF.SVRCONN";

	// #主机地址-发送方
	private static final String QUEUE_MANAGER_HOST_SENDER = "10.201.128.130";
	// #队列管理器名称-发送方
	private static final String QUEUE_MANAGER_SENDER = "BLESBTEST_QMGR";
	// #队列名称-发送方
	private static final String QUEUE_NAME_SENDER = "ORDER.ESB.TO.SSS";
	/*
	 * // #主机地址-接收方 private static final String queue_manager_host_receiver =
	 * "10.201.128.104"; // #队列管理器名称-接收方 private static final String
	 * queue_manager_receiver = "BLQMGR2"; // #队列名称-接收方 private static final
	 * String queue_name_receiver = "ORDER.ESB.TO.LH";
	 */

    private static JmsTemplate jmsTemplate = new JmsTemplate();
    private static final MQQueueConnectionFactory jmsConnectionFactory = new MQQueueConnectionFactory();
    private static final MQQueue queue = new MQQueue();
    static {
        jmsTemplate.setConnectionFactory(jmsConnectionFactory);
        jmsTemplate.setDefaultDestination(queue);
        jmsTemplate.setPubSubDomain(false);

        try {
            jmsConnectionFactory.setHostName(QUEUE_MANAGER_HOST_SENDER);
            jmsConnectionFactory.setQueueManager(QUEUE_MANAGER_SENDER);

            jmsConnectionFactory.setPort(APP_MQ_PORT);
            jmsConnectionFactory.setCCSID(APP_MQ_CCSID);
            jmsConnectionFactory.setTransportType(APP_MQ_TRANSPORT_TYPE);
            jmsConnectionFactory.setChannel(APP_MQ_CHANNEL);

            queue.setBaseQueueName(QUEUE_NAME_SENDER);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    /**
	 * 发送消息
	 * 
	 * @param msg
	 *            消息内容
	 */
    public static void send(String msg) {
        MessageCreator messageCreator = createTxtMsg(msg);
        jmsTemplate.send(messageCreator);
		LogUtil.step("发送消息至队列：" + QUEUE_NAME_SENDER, "消息：" + msg);
    }

    private static MessageCreator createTxtMsg(final String msg) {
        return new MessageCreator() {
            @Override
			public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText(msg);
                return textMessage;
            }
        };
    }
}
