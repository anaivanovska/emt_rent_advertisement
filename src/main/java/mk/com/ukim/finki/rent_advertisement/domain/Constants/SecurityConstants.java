package mk.com.ukim.finki.rent_advertisement.domain.Constants;


public class SecurityConstants {
    public static final String SECRET= "Ageiw892_njwfb782";
    public static final String HEADER_NAME = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final long EXPIRATION_TIME = 864000000;
    public static final String ALLOWED_HEADERS = "*";
    public static final String ALLOWED_ORIGINS ="*";
    public static final String EXPOSED_HEADERS = "Authorization, Content-Type, Expires, Last-modified, Access-Control-Allow-Origin";
    public static final String ALLOWED_METHODS = "*";
}
