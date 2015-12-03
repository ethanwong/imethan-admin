package cn.imethan.common.listener;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Hibernate事情监听注册类.
 * 因为spring配置文件Bean的初始化默认配置成lazy,所以在上面这个Bean里加上了@Lazy(false)的注解,确保监听器的注入.
 * 
 * @author zblink
 * @since JDK 1.7
 * @see
 */
@Component
@Lazy(false)
public class HibernateEventListener {
    
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private AuditListener auditListener;
    
    @PostConstruct
    public void registerListeners() {
        EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory).getServiceRegistry().getService(EventListenerRegistry.class);
        registry.setListeners(EventType.SAVE_UPDATE, auditListener);
    }

}
