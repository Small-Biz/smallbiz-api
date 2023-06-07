package com.example.accessingdatamysql;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//Created by: Chok Kwong Cheng (Ray)
//Created at: 28 Feb, 2023
/*
class Node{
	int value;
	List<Node> children;
	
	public Node(int value) {
		this.value=value;
		children=new ArrayList<Node>();
	}
	
	public void addChild(Node child) {
		this.children.add(child);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(value);
		builder.append("=>(");
		builder.append(children);
		builder.append(")");
		return builder.toString();
	}
	
}
*/
public class ThentiaTask2 {

	public static Node buildTree( int[][] edgeArray) {

		Map<Integer,Node> nodeMap=new HashMap<Integer,Node>();
		
		List<int[]>edgeList=new ArrayList<>(Arrays.asList(edgeArray));
		
		for ( int i=0; i <edgeList.size(); i ++) {
			int[] edge=edgeList.get(i);
			System.out.println(edge[0] + ","+ edge[1]);
			
			boolean _0AsParent=false;
			boolean _1AsParent=false;
			
			if (edge[0]==1) {
				_0AsParent=true;
			}else if ( edge[1]==1) {
				_1AsParent=true;
			}else if ( !nodeMap.containsKey(edge[0]) && !nodeMap.containsKey(edge[1]) ){
				edgeList.add(edge);
				continue;
			}
			
			if ( !nodeMap.containsKey(edge[0])){
				nodeMap.put(edge[0],new Node(edge[0]));
				if ( !_0AsParent&&!_1AsParent) {
					_1AsParent=true;
				}
			}
			if ( !nodeMap.containsKey(edge[1])){
				nodeMap.put(edge[1],new Node(edge[1]));				
				if ( !_0AsParent&&!_1AsParent) {
					_0AsParent=true;
				}
			}
			
			if (_0AsParent) {
				nodeMap.get(edge[0]).addChild(nodeMap.get(edge[1]));
			}else if (_1AsParent) {
				nodeMap.get(edge[1]).addChild(nodeMap.get(edge[0]));
			}
			
		}
		
		return nodeMap.get(1);
		
	}
	
	
	public static void main(String[] avg) {

//		int[][] input= {{4,5},{5,3},{1,5},{2,1}};
//		int[][] input= {{4,5},{5,3},{1,5},{2,5}};
		
		int[][] input= {{4,5},{5,3},{1,5},{6,7},{1,6},{2,1}};
//		int[][] input= {{4,5},{5,3},{6,7},{1,6},{1,5},{2,5}};

		Node n=buildTree( input);
		System.out.println("n.value: " + n.value);
		System.out.println("n: " + n);
	}
}