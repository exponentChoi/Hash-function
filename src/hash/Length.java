package hash;

import java.security.MessageDigest;
import java.util.Scanner;

public class Length {
 
	public static String getHash(String input) {
		StringBuffer result = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(input.getBytes());
			byte bytes[] = md.digest();
			for(int i=0; i<bytes.length;i++) {
				result.append(
						Integer.toString((bytes[i] & 0xff) + 0x100,16).substring(1)
						);						
			}
		}catch (Exception e) {
			
		}
		return result.toString();
	}
	
    public static void main(String[] args) {
    	
    	while(true) {
    	System.out.println("1. 영문, 숫자, 특수문자를 입력해 주세요 (한글, 한자 등은 제외)\n2. 종료를 원하시면 '끝내자'를 입력하세요");
    	
    	Scanner s = new Scanner(System.in);
    	String in = s.nextLine();
    	boolean language = false;
    	for(int i=0;i<in.length();i++)
    	{
    		int index = in.charAt(i);
    		if(index<33|index>126)
    		{
    			language=true;
    		}
    	}
    	if(language)
    	{
    		System.out.println("다시 입력하세요");    		
    	}
    //	char[] language= in.toCharArray();
    //	int ascii=(int)language;
    	//if(language< 33|language>126)
    	//{
    	//	System.out.println("다시 입력하세요");
    	//}
    	else if(in.equals("끝내자")) // 프로그램 종료 명령어
    	{
    		break;
    	}
    	else if(in.getBytes().length>127|in.getBytes().length<=0)
    	{
    		System.out.println("다시 입력하세요(byte 길이범위 : 1byte ~ 127byte)");
    		System.out.println("입력값 byte길이 : "+in.getBytes().length+"\n");
    	}
    	else {
    	String hashStr =  getHash(in);
    	String str = new String(hashStr.getBytes(),0,32);
    	//System.out.println("입력한 값 : "+in+"\n결과 값 : "+getHash(in)+"\n");
    	System.out.println("입력한 값 : "+in+" ("+in.getBytes().length+"byte)\n결과 값 : "+str+" ("+str.getBytes().length+"byte)\n");
    	System.out.println("결과값 byte"+getHash(in).getBytes().length);
    	System.out.println("입력값 byte"+in.getBytes().length);
    	//System.out.println(language);
    	//System.out.println( java.util.Arrays.toString(language) );
    	    	}    	
    	}

		System.out.println("사용해주셔서 감사합니다.");
    }
}