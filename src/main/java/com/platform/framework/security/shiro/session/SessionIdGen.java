/*
 * Copyright &copy; <a href="http://www.lufengc.cc">lufengc</a> All rights reserved.
 */

package com.platform.framework.security.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * sessionId 生成器
 *
 * @author lufengcheng
 * @date 2016-01-15 09:56:22
 */
public class SessionIdGen implements SessionIdGenerator {

	/** 
	 * 实现generateId方法
	 * @param session session
	 * @return Serializable
	 * @see SessionIdGenerator#generateId(Session)
	 */
	@Override
	public Serializable generateId(Session session) {
		return UUID.randomUUID().toString();
	}

}
