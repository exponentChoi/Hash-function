package hash;

import java.security.MessageDigest;
import java.util.Scanner;

public class Length {
 
	public static String getHash(String input) {
		StringBuffer result = new StringBuffer();
		try { // 예외일 경우
			MessageDigest md = MessageDigest.getInstance("SHA-256"); // SHA-256 형식사용 
			md.update(input.getBytes()); // 입력한 hash값 업데이트
			byte bytes[] = md.digest(); // hash값 digest 얻기
			for(int i=0; i<bytes.length;i++) { // 
				result.append( // 문자열 형태로 변환
						Integer.toString((bytes[i] & 0xff) + 0x100,16).substring(1) // 처음값 그대로 돌리기 위함
						);						
			}
		}catch (Exception e) { // 예외 발생시 처리방법 			
		}
		return result.toString(); //hash 결과값 반환
	}
	
    public static void main(String[] args) {
    	
    	while(true) { // 프로그램을 끝내고싶을때 가지 반복
    	System.out.println("1. 영문, 숫자, 특수문자를 입력해 주세요 (한글, 한자 등은 제외)\n2. 종료를 원하시면 '끝내자.'를 입력하세요");
    	
    	Scanner s = new Scanner(System.in); // 임의의 데이터 입력
    	String in = s.nextLine(); // in 에 입력값 저장
    	boolean language = false;  // 영문, 숫자, 특수문자 입력위한 변수설정
    	for(int i=0;i<in.length();i++)  
    	{
    		int index = in.charAt(i); // in 입력값을 ASCII 코드로 변환하여 index에 저장
    		if(index<32|index>126) // 숫자,특수문자,영문 ASCII코드 범위  32~126 설정 (space 포함)
    		{
    			language=true; // 숫자, 특수문자, 영문 이외에 값이 들어갈 경우 true
    		}
    	}
    	if(in.equals("끝내자.")) // 프로그램 종료 명령어
    	{
    		break; // 만족시 반복문 종료
    	}
    	else if(language) // 숫자, 특수문자, 영문 이외값일 경우 
    	{
    		System.out.println("영문, 숫자, 특수문자만 입력해 주세요.\n");    		
    	}
    	
    	else if(in.getBytes().length>127|in.getBytes().length<=0) // 입력데이터 byte 값 설정     , 0<입력데이터byte<=127
    	{
    		System.out.println("다시 입력하세요(0 < byte <= 127)");
    		System.out.println("입력값 byte길이 : "+in.getBytes().length+"\n");
    	}
    	else {
    	String hashStr =  getHash(in);  // hash로 변환된 함수를 hashStr에 입력
    	String str = new String(hashStr.getBytes(),0,32); // hash로 변환된 데이터를'str에 고정된길이 32byte로 입력
    	System.out.println("입력한 값 : "+in+" ("+in.getBytes().length+"byte)\n결과 값 : "+str+" ("+str.getBytes().length+"byte)\n");    	
    	    	}    	
    	}

		System.out.println("사용해주셔서 감사합니다.");
    }
}