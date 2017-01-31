package ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class Ldap2Test {

	public static String ldapUri = "ldap://ipa.capmedia.io:389";
	public static String usersContainer = "cn=users,cn=accounts,dc=capmedia,dc=io";

	public static void main(String args[]) {	

		String username = "uid=java_test,cn=users,cn=accounts,dc=capmedia,dc=io";
		String password = "ddes@323sWQ1123&";

		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, ldapUri);
		env.put(Context.SECURITY_PRINCIPAL, username);
		env.put(Context.SECURITY_CREDENTIALS, password);
		try {
			DirContext ctx = new InitialDirContext(env);
			SearchControls ctls = new SearchControls();
			String[] attrIDs = { "cn" };
			ctls.setReturningAttributes(attrIDs);
			ctls.setSearchScope(SearchControls.ONELEVEL_SCOPE);

			NamingEnumeration answer = ctx.search(usersContainer, "(objectclass=ipaUserGroup)", ctls);
			while (answer.hasMore()) {
				SearchResult rslt = (SearchResult) answer.next();
				Attributes attrs = rslt.getAttributes();
				System.out.println(attrs.get("cn"));
			}

			ctx.close();

		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

}
