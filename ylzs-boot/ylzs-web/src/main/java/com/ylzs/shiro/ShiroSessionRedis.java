package com.ylzs.shiro;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ylzs.core.constant.CommonConstant;
import com.ylzs.redis.RedisService;
import com.ylzs.redis.serialize.ObjectTranscoder;
import com.ylzs.util.CommonUtil;

@Service("shiroSessionRedis")
public class ShiroSessionRedis extends EnterpriseCacheSessionDAO {
	@Autowired
	private RedisService<Object> redisService;
	@SuppressWarnings("rawtypes")
	@Autowired
	private ObjectTranscoder objectTranscoder;
	
	@Override
    protected Serializable doCreate(Session session) {
		String sessionId = CommonUtil.uuid();
        assignSessionId(session, sessionId);
        String key = sessionId + CommonConstant.SHRIO_SESSION;
        redisService.set(key, objectTranscoder.serialize(session));
        redisService.expireMinutes(key,43200);//一个月自动清理redis中的无session
        return sessionId;
    }
	
    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = super.doReadSession(sessionId);
        if(null == session){
        	byte[] byteSession = (byte[]) redisService.get(sessionId + CommonConstant.SHRIO_SESSION);
        	if(byteSession != null && byteSession.length > 0){
                session = (Session) objectTranscoder.deserialize(byteSession);    
            }
        }
        session.setAttribute("userId", "test123");
        return session;
    }
    
    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        String key = session.getId() + CommonConstant.SHRIO_SESSION;
        redisService.set(key, objectTranscoder.serialize(session));
        redisService.expireMinutes(key + CommonConstant.SHRIO_SESSION,43200);//一个月自动清理redis中的无session
    }
    
    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        redisService.delete(session.getId() + CommonConstant.SHRIO_SESSION);
    }
    
    // 把session对象转化为byte保存到redis中
    public byte[] sessionToByte(Session session){
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        byte[] bytes = null;
        try {
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(session);
            bytes = bo.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
  

    // 把byte还原为session
    public Session byteToSession(byte[] bytes){
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        ObjectInputStream in;
        SimpleSession session = null;
        try {
            in = new ObjectInputStream(bi);
            session = (SimpleSession) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return session;
    }
}
