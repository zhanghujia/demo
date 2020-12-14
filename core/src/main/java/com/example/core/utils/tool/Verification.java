package com.example.core.utils.tool;

import java.util.ArrayList;
import java.util.List;

public class Verification {

	public static boolean verificationAndString(String ...args){
		
		for(int i=0;i<args.length;i++){
			String str = args[i];
			if((null == str) || (str.isEmpty())) {
				return false;
			}
				
		}
		return true;
	}
	
	public static boolean verificationOrString(String ...args){
		
		for(int i=0;i<args.length;i++){
			String str = args[i];
			if((null != str) && (!str.isEmpty())) {
				return true;
			}
				
		}
		return false;
	}
		
	//检查string类型是否有效，基本检查，null,empty
	public static boolean verificationString(String str){

		return (null != str) && (!str.isEmpty());
	}
	
	public static boolean verificationBoolean(String str){
		
		if((null == str) || (str.isEmpty())) {
			return false;
		} else return ("0".equals(str)) || ("1".equals(str));
	}
	
	public static boolean verifiList(List<?> list){
		return (null != list) && (!list.isEmpty());
	}
	
	public static List<?> getReturnList(List<?> list, int perPage,int page) {
		/*
		List<?> newlist = new ArrayList<>();
		if((perPage <=0) || (page < 1) || (null == list) || (list.size() ==0))  {
			return newlist;
		}
		
		//超过范围
		if(((page-1) * perPage) > list.size()) {
			return newlist;
		}
		
		
		for(int index = ((page-1) * perPage); index < (page * perPage);index ++){
			if(index >= list.size()){
				break;
			} 
			newlist.add(list.get(index));
			
		}
		return newlist;
		*/
		
		int start,end;
		
		if((perPage <=0) || (page < 1) || (null == list) || (list.size() ==0) ||
				(((page-1) * perPage) > list.size()))  {
			return new ArrayList<>();
		}
		
		start = (page-1) * perPage;
		//这里subList是左闭右开的，不包括toindex的这个元素，所以如果是需要取最后一个元素，toindex+1
		end = ((start + perPage) >= list.size())?(list.size()-1+1):(start+perPage);
		return list.subList(start, end);
	}
}
