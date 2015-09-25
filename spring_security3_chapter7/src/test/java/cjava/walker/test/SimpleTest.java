package cjava.walker.test;

import org.junit.Test;
import org.springframework.security.acls.domain.AbstractPermission;
import org.springframework.security.acls.domain.BasePermission;

public class SimpleTest {
	
	@Test
	public void testAclFormattingUtils(){
		System.out.println(BasePermission.READ.getPattern());
		System.out.println(BasePermission.WRITE.getPattern());
		System.out.println(BasePermission.CREATE.getPattern());
		System.out.println(BasePermission.DELETE.getPattern());
		System.out.println(BasePermission.ADMINISTRATION.getPattern());
		
		System.out.println(CustomizedPermision.RWCD.getPattern());
	}
}


class CustomizedPermision extends AbstractPermission{
	
	public static CustomizedPermision RWCD = new CustomizedPermision(Integer.parseInt("010101010101",2),'X');

	private static final long serialVersionUID = 1384345882845366970L;
	
	public CustomizedPermision(int mask) {
		super(mask);
		
	}
	public CustomizedPermision(int mask, char code) {
		super(mask, code);
	}
	
	
}

