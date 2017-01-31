package ldap;

import java.net.URI;
import java.util.Optional;

import com.google.common.cache.CacheBuilderSpec;
import com.yammer.dropwizard.authenticator.LdapAuthenticator;
import com.yammer.dropwizard.authenticator.LdapConfiguration;
import com.yammer.dropwizard.authenticator.User;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.util.Duration;

public class LdapAuth {

	public static void main(String[] args) {
		
		LdapConfiguration conf = new LdapConfiguration();
		conf.setUri(URI.create("ldap://ipa.capmedia.io:389"));
		
		conf.setCachePolicy(CacheBuilderSpec.parse("maximumSize=10000, expireAfterWrite=10m"));
		
		conf.setUserFilter("cn=users,cn=accounts,dc=capmedia,dc=io");
		conf.setGroupFilter("cn=groups,cn=accounts,dc=capmedia,dc=io");

		conf.setUserNameAttribute("uid");

		conf.setGroupNameAttribute("cn");
		conf.setGroupMembershipAttribute("member");
		conf.setGroupClassName("ipaUserGroup");
		
		conf.setConnectTimeout(Duration.seconds(1));
		conf.setReadTimeout(Duration.seconds(1));
		
		LdapAuthenticator auth = new LdapAuthenticator(conf);
		try {
			Optional<User> authenticate = auth.authenticateAndReturnPermittedGroups(new BasicCredentials("java_test", "ddes@323sWQ1123&"));
			System.out.println(authenticate);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		
	}
}
