/**
 * 
 */
package com.api.order.rabbitmq;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import aos.framework.core.utils.AOSPropertiesHandler;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Administrator
 *
 */
@Component
public class Sender {
	
//	@Resource
//    private AmqpTemplate rabbitTemplate;

    public void send(String id,String total,String user_id){
        
        String host = AOSPropertiesHandler.getProperty("mq.host");
		int port = Integer.valueOf(AOSPropertiesHandler.getProperty("mq.port"));
		String password = AOSPropertiesHandler.getProperty("mq.password");
		String username = AOSPropertiesHandler.getProperty("mq.username");
		String vhost = AOSPropertiesHandler.getProperty("mq.vhost");
		
		//String publisher = AOSPropertiesHandler.getProperty("mq.publisher");
		
        ConnectionFactory factory = new ConnectionFactory();
      
        factory.setHost(host);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setPort(port);
        factory.setVirtualHost(vhost);
        //factory.setPublisherConfirms(true);
        
        
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("x-message-ttl", 10000);
		message.put("x-id", id);
		message.put("x-total", total);
		message.put("x-user_id", user_id);
        
        com.rabbitmq.client.Connection connection = null;
		try {
			connection = factory.newConnection();
		} catch (IOException | TimeoutException e1) {
			e1.printStackTrace();
		}
        //创建一个通道
        Channel channel = null;
		try {
			channel = connection.createChannel();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        //  声明一个队列        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
     
        String queue="";
        //发送消息到队列中
        try {
			 queue = channel.queueDeclare().getQueue();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        System.out.println("Producer queue +'" + queue + "'");
     
			try {
		
				channel.queueDeclare("OrderQueue", true, false, false, null);
				channel.basicPublish("", "OrderQueue", null,  SerializationUtils.serialize(message));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
        System.out.println("Producer Send +'" + message.get("x-id") + "'");
        //关闭通道和连接
    
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			}
	
      
			try {
				connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	
//        ApplicationContext context = new ClassPathXmlApplicationContext("amqp/amqp-producer.xml");
//        AmqpTemplate template = (AmqpTemplate) context.getBean("rabbitTemplate");
        
        //onnectionFactory factory = new ConnectionFactory();
//        String host = AOSPropertiesHandler.getProperty("mq.host");
//		int port = Integer.valueOf(AOSPropertiesHandler.getProperty("mq.port"));
//		String password = AOSPropertiesHandler.getProperty("mq.password");
//		String username = AOSPropertiesHandler.getProperty("mq.username");
//		String vhost = AOSPropertiesHandler.getProperty("mq.vhost");
//		String publisher = AOSPropertiesHandler.getProperty("mq.publisher");
		
		
     
       // rabbitTemplate.convertAndSend("OrderQueue", sendMsg);
    }
    
    
  /*  public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        String host = AOSPropertiesHandler.getProperty("mq.host");
    		int port = Integer.valueOf(AOSPropertiesHandler.getProperty("mq.port"));
    		String password = AOSPropertiesHandler.getProperty("mq.password");
    		String username = AOSPropertiesHandler.getProperty("mq.username");
    		String vhost = AOSPropertiesHandler.getProperty("mq.vhost");
    		
        //设置RabbitMQ地址
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setPort(port);
        factory.setVirtualHost(vhost);
        //创建一个新的连接
       
        com.rabbitmq.client.Connection connection = null;
		try {
			connection = factory.newConnection();
		} catch (IOException | TimeoutException e1) {
			e1.printStackTrace();
		}
        //创建一个通道
        Channel channel = null;
		try {
			channel = connection.createChannel();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        //  声明一个队列        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "OrderQueue";
        
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                //String message = new String(body, "UTF-8");
                Map message = (HashMap) SerializationUtils.deserialize(body);
                System.out.println("Customer Received '" + message.get("x-id") + "'");
            }
        };
        //自动回复队列应答 -- RabbitMQ中的消息确认机制
        channel.basicConsume(message, true, consumer);
    }*/

}
