/**
 * 
 */
package com.userakkaskills.security;

/**
 * @author Robert
 *
 */
public interface SecurityParam {
	public static final String HEADER_NAME="Authorization";
	public static final String SECRET="teyemlong@gmail.com";
	public static final long EXPIRATION=10*24*3600*1000; 
	public static final String PREFIX_HEADER="Bearer ";

}
