package com.bookstore.dao;
import java.sql.*;
import java.util.ArrayList;

import com.bookstore.pojo.Book;


public class BookDao {
	private  Connection connection;
	public BookDao() {
		initialize("jdbc:oracle:thin:@//localhost:1521/XE","javatraining","hawking");
		
	}

	public void initialize(String url,String username,String password) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	 public boolean insert(Book b) {
		int r=0;
		 try {
			PreparedStatement pre=connection.prepareStatement("insert into books values(?,?,?,?,?,?)");
			pre.setInt(1, b.getId());
			pre.setString(2,b.getName() );
			pre.setString(3, b.getAuthor());
			pre.setString(4, b.getSubject());
			pre.setFloat(5, b.getPrice());
			pre.setString(6, b.getFile());
			
			r=pre.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(r!=0)
			return true;
		else
			return false;
		}
	 public boolean update(Book b) {
		 int r=0;
		try {
			String sql="update books set name='"+b.getName()+"', author='"+b.getAuthor()+"',subject='"+b.getSubject()+"',price="+b.getPrice()+",file_name='"+b.getFile()+"' where id="+b.getId();
			PreparedStatement p=connection.prepareStatement(sql);
			
			r=p.executeUpdate();
        } catch (SQLException e) {
			e.printStackTrace();
		}
		if(r!=0)
			return true;
		else
			return false;
		
	}
	 public boolean delete(Book b) {
		 int r=0;
		 try {
			PreparedStatement p=connection.prepareStatement("delete from books where id="+b.getId());
			r=p.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(r!=0)
				return true;
			else
				return false;
	}
	 public void alterAddColumn(String Col,String type) {
		try {
			String sql="alter table employees add "+Col+" "+type;
			PreparedStatement p=connection.prepareStatement(sql);
			
			System.out.println(p.executeUpdate());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	 public void alterDropColumn(String Col) {
		try {
			String sql="alter table books drop "+Col;
			PreparedStatement p=connection.prepareStatement(sql);
			
			System.out.println(p.executeUpdate());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	 
	 public void truncate() {
		 PreparedStatement p;
		try {
			p = connection.prepareStatement("truncate books");
			p.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 }

	 public ArrayList<Book> search() {
		 ArrayList<Book> b=new ArrayList<>();
		 try {
			String sql="select * from books ";
			PreparedStatement p=connection.prepareStatement(sql);
			ResultSet rs=p.executeQuery();
			
			while(rs.next()) {
				b.add(new Book( rs.getInt("id"), rs.getString("name"),rs.getString("author") ,rs.getString("subject") , rs.getInt("price"), rs.getString("file")));
			}
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	 public ArrayList<Book> search(String extras) {
		 ArrayList<Book> b=new ArrayList<>();
		 try {
				String sql="select * from books "+extras;
				PreparedStatement p=connection.prepareStatement(sql);
				ResultSet rs=p.executeQuery();
				
				while(rs.next()) {
					b.add(new Book( rs.getInt("id"), rs.getString("name"),rs.getString("author") ,rs.getString("subject") , rs.getInt("price"), rs.getString("file_name")));
				}
				System.out.println();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 	return b;
		}
	
	
}
