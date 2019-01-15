package com.newhope.moneyfeed.mq.adapter;

import java.util.Map;
import java.util.Set;

public class Channel {
	public static enum Type {
		SMS, Email, AppPush, Wechat, Common, SITE;
	}

	private String address;
	private Type type;
	private Map<String, String> extra;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Map<String, String> getExtra() {
		return extra;
	}

	public void setExtra(Map<String, String> extra) {
		this.extra = extra;
	}

	public String toString() {
		if (extra == null)
			return null;
		StringBuffer sb = new StringBuffer();
        extra.entrySet().stream().forEach(
             stringStringEntry -> {
                 sb.append(stringStringEntry.getKey()).append("=").append(stringStringEntry.getValue()).append("|");
             }
        );
        for (String key : extra.keySet()) {
			sb.append(key).append("=").append(extra.get(key)).append("|");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}
