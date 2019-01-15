package com.newhope.moneyfeed.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

public class StringUtils {

	public static String[] tokenizeToStringArray(String str, String delimiters) {
		return tokenizeToStringArray(str, delimiters, true, true);
	}

	public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens,
			boolean ignoreEmptyTokens) {

		if (str == null) {
			return null;
		}
		StringTokenizer st = new StringTokenizer(str, delimiters);
		List<String> tokens = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (trimTokens) {
				token = token.trim();
			}
			if (!ignoreEmptyTokens || token.length() > 0) {
				tokens.add(token);
			}
		}
		return toStringArray(tokens);
	}
	 @SuppressWarnings({"unchecked", "rawtypes"})
	    public static String[] toStringArray(Collection collection) {
	        if (collection == null) {
	            return null;
	        }
	        return (String[]) collection.toArray(new String[collection.size()]);
	    }
}
