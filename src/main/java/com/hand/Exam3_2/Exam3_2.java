package com.hand.Exam3_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Exam3_2 {
	
	public static Connection getCoonection(){
		Connection conn =null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila?characterEncoding=utf8", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
		
	} 
	
	public static void check(int id)  {
		Connection conn=getCoonection();
		Statement st=null;
		ResultSet rs=null;
		ResultSet rs2=null;
	try {//customer_id
		String sql1="select film_id,title,last_update from "
				+ "rental r,iventory i,film f where"
				+ "r.customer_id="+id+" and r.iventory_id=i.iventory_id"
				+ "and i.film_id=f.film_id";
		
		//String sql2="select f.film_id,f.title from film f where f.film_id=628";
		
		String sql3="select first_name,last_name from customer where customer_id="+id;
		 st =conn.createStatement();
		 
		 rs= st.executeQuery(sql3); 
		 
		//rs= st.executeQuery(sql);
		//int count=0;
		//System.out.println("城市：");
		rs.first();
		System.out.println(rs.getString("first_name")+"."+rs.getString("last_name")+"租用的film->");
		System.out.println("film ID|film 名称	|租用时间");
		
		String sql2="select film_id,title from "
				+ "film f where film_id =(selcet film_id from iventory i where "
				+ "i.iventory_id =(select iventory_id from rental r where r.customer_id ="
				+ "(select customer_id from custom c where c.customer_id="+id+"))";
		rs2= st.executeQuery(sql1); 
		//rs.beforeFirst();
		while (rs2.next()) {
			//System.out.println("Country"+" "+rs.getString("country")+"的城市->");
			//System.out.println("城市 ID|城市名称");
			System.out.print(rs2.getString("f.film_id")+"  ");
			System.out.print("|");
			System.out.print(rs2.getString("f.title"));
			System.out.print("|");
			System.out.print(rs.getString("r2.last_update"));
			System.out.println();
		}
		//System.out.println("done");
		//System.out.print("记录："+count);
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			rs.close();
		} catch (Exception e2) {
			// TODO: handle exception
		}
		try {
			st.close();
		} catch (Exception e3) {
			// TODO: handle exception
		}
		try {
			conn.close();
		} catch (Exception e4) {
			// TODO: handle exception
		}
	}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("输入：");
		System.out.print("请输入Customer Id:");
		Scanner a1=new Scanner(System.in); 
		System.out.println();
		int customer_id =a1.nextInt();
		System.out.println("输出：");
		check(customer_id);
	}
	
	

}

