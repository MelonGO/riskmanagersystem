package tools;

public class Utility {

	public static boolean isEmptyString(String src){
		if(src!=null && !src.trim().isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	
}
