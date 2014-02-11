/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.apache.shiro.session.mgt;

import org.apache.shiro.session.mgt.SimpleSession;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-3-20 下午2:29
 * <p>Version: 1.0
 */
public class OnlineSession extends SimpleSession {

    // Serialization reminder:
    // You _MUST_ change this number if you introduce a change to this class
    // that is NOT serialization backwards compatible.  Serialization-compatible
    // changes do not require a change to this number.  If you need to generate
    // a new number in this case, use the JDK's 'serialver' program to generate it.
    private static final long serialVersionUID = -7125642695178165650L;

    public static enum OnlineStatus {
        on_line("在线"), hidden("隐身"), force_logout("强制退出");
        private final String info;

        private OnlineStatus(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }

    private static final int USER_AGENT_BIT_MASK = 1 << bitIndexCounter++;
    private static final int STATUS_BIT_MASK = 1 << bitIndexCounter++;


    /**
     * 用户浏览器类型
     */
    private String userAgent;

    /**
     * 在线状态
     */
    private OnlineStatus status = OnlineStatus.on_line;

    /**
     * 用户登录时系统IP
     */
    private String systemHost;

    public OnlineSession() {
        super();
    }

    public OnlineSession(String host) {
        super(host);
    }


    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public OnlineStatus getStatus() {
        return status;
    }

    public void setStatus(OnlineStatus status) {
        this.status = status;
    }

    public String getSystemHost() {
        return systemHost;
    }

    public void setSystemHost(String systemHost) {
        this.systemHost = systemHost;
    }

    /**
     * 属性是否改变 优化session数据同步
     */
    private transient boolean attributeChanged = false;

    public void markAttributeChanged() {
        this.attributeChanged = true;
    }

    public void resetAttributeChanged() {
        this.attributeChanged = false;
    }

    public boolean isAttributeChanged() {
        return attributeChanged;
    }

    @Override
    public void setAttribute(Object key, Object value) {
        super.setAttribute(key, value);
    }


    @Override
    public Object removeAttribute(Object key) {
        return super.removeAttribute(key);
    }


    /**
     * Serializes this object to the specified output stream for JDK Serialization.
     *
     * @param out output stream used for Object serialization.
     * @throws java.io.IOException if any of this object's fields cannot be written to the stream.
     * @since 1.0
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        short alteredFieldsBitMask = getAlteredFieldsBitMask();
        out.writeShort(alteredFieldsBitMask);
        if (userAgent != null) {
            out.writeObject(userAgent);
        }
        if (status != null) {
            out.writeObject(status);
        }

    }

    /**
     * Reconstitutes this object based on the specified InputStream for JDK Serialization.
     *
     * @param in the input stream to use for reading data to populate this object.
     * @throws java.io.IOException            if the input stream cannot be used.
     * @throws ClassNotFoundException if a required class needed for instantiation is not available in the present JVM
     * @since 1.0
     */
    @SuppressWarnings({"unchecked"})
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        short bitMask = in.readShort();

        if (isFieldPresent(bitMask, USER_AGENT_BIT_MASK)) {
            this.userAgent = (String) in.readObject();
        }
        if (isFieldPresent(bitMask, STATUS_BIT_MASK)) {
            this.status = (OnlineStatus) in.readObject();
        }
    }

    /**
     * Returns a bit mask used during serialization indicating which fields have been serialized. Fields that have been
     * altered (not null and/or not retaining the class defaults) will be serialized and have 1 in their respective
     * index, fields that are null and/or retain class default values have 0.
     *
     * @return a bit mask used during serialization indicating which fields have been serialized.
     * @since 1.0
     */
    private short getAlteredFieldsBitMask() {
        int bitMask = 0;
        bitMask = userAgent != null ? bitMask | USER_AGENT_BIT_MASK : bitMask;
        bitMask = status != null ? bitMask | STATUS_BIT_MASK : bitMask;
        return (short) bitMask;
    }

    /**
     * Returns {@code true} if the given {@code bitMask} argument indicates that the specified field has been
     * serialized and therefore should be read during deserialization, {@code false} otherwise.
     *
     * @param bitMask      the aggregate bitmask for all fields that have been serialized.  Individual bits represent
     *                     the fields that have been serialized.  A bit set to 1 means that corresponding field has
     *                     been serialized, 0 means it hasn't been serialized.
     * @param fieldBitMask the field bit mask constant identifying which bit to inspect (corresponds to a class attribute).
     * @return {@code true} if the given {@code bitMask} argument indicates that the specified field has been
     *         serialized and therefore should be read during deserialization, {@code false} otherwise.
     * @since 1.0
     */
    private static boolean isFieldPresent(short bitMask, int fieldBitMask) {
        return (bitMask & fieldBitMask) != 0;
    }
}
