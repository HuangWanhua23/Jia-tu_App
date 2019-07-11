package com.example.app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract.Helpers;

public class MyServer {
	// ����������
	public static ServerSocket cServerSocket;
	// ����
	public static Socket cSocket;
	// �˿�
	public static final int PORT = 8888;
    public static void main(String[] args){
		DataInputStream dis = null;
		DataOutputStream dos = null;
		Connection con = null;    //�����������ݿ�  
		Statement stmt=null;     //����ִ�����ݿ���ز���SQL���	
		try {
				cServerSocket = new ServerSocket(PORT);
				System.out.println("---���ܾ�����������ʼ����---");
				while (true) {
				System.out.println("���ڵȴ��ͻ�����...");
				// ���ﴦ�ڵȴ�״̬�����û�пͻ������ӣ����򲻻�����ִ��
				cSocket = cServerSocket.accept();
				dis = new DataInputStream(cSocket.getInputStream());
				dos = new DataOutputStream(cSocket.getOutputStream());
				// ��ȡ����
				String clientStr = dis.readUTF();
				// д������
				char[] c = clientStr.toCharArray();				
				int one = -1;    //��������"|"
				int two = -1;    //��������"#"
				int three=-1;    //��������"/"����ʾ�û�������ע��/�����¼/�������룩
				for (int i = 0; i < c.length; i++) {
					if((c[i] == '/')){
						three = i;
					}
				}
				String flag = new String(c, three+1 , c.length -three- 1);//�����ж�ִ�е���ע�������ǵ�½����	
				if(flag.equals("�����¼")){
					// �ҵ���һ����ʶ��
					for (int i = 0; i < c.length; i++) {
						if((c[i] == '#')){
							one =i;
						}else if((c[i] == '|')) {
							two = i;
						}else if((c[i] == '/')){
							three = i;
						}
					}
					System.out.println("---�ͻ����ѳɹ�����---");
					// �õ��ͻ��˵�IP
					System.out.println("�ͻ��˵�IP=" + cSocket.getInetAddress());
					// �õ��ͻ��˵Ķ˿ں�
					System.out.println("�ͻ��˵Ķ˿ں�=" + cSocket.getPort());
					// �õ����ض˿ں�
					System.out.println("���ط������˿ں�=" + cSocket.getLocalPort());
					//�õ��ͻ��˴�����δ���������Ϣ����
					System.out.println("�ͻ��˵�¼�˺�|����/��������:" + clientStr);
					//������Ϣ���ֿ���ţ�				
					String user1 = new String(c, 0, two);
					System.out.println("�ͻ��˵�¼�˺�user:" + user1);
					String password1 = new String(c, two+1, three-two-1);   //��ʼλ�úͳ�������ȡ���ַ���
					System.out.println("�ͻ��˵�¼����password:" + password1);	
					String judge = new String(c, three+1, c.length - three - 1);
					System.out.println("�ͻ��˾������judge:" + judge);
					System.out.println();
					//�������ݿ�����ƥ��Ͳ���
					try{
						   System.out.println("���ԽӲ������ܾ���Appע����Ϣ���ݿ⡰");
						   Class.forName("org.sqlite.JDBC");
						   con = DriverManager.getConnection("jdbc:sqlite:login.db");
						   con.setAutoCommit(false);    //false��sql������ύ��Ӧ�ó����𣬳���������commit����rollback����
						   System.out.println("Opened database successfully!!");   //�����ݿ�ɹ���������
						   //����ǵ�һ�δ����ñ���ִ�����´����
					       stmt = con.createStatement();
					       //SQL���ƥ�����ݿ�
						       System.out.println("����ƥ����");
					           ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
					           String msg1=null;
					           while ( rs.next() ) {
					        	   //ȡ��ע��ʱ���û��˺�USER������PASSWORD���¼ʱ���˺�user1������password1���бȽ�
					              String  userdata = rs.getString("USER");
					              String  passworddata = rs.getString("PASSWORD");
					              if((user1.equals(userdata))&&(password1.equals(passworddata))){
					            	    msg1="�����¼";
										//System.out.println("��¼������ע������һ�£������¼����");
										break;
									}else{
										msg1="�������¼";
					              		//System.out.println("��¼������ע�����ݲ�һ�£��������¼����");
									}
					           }
					           System.out.println(msg1);
					           dos.writeUTF(msg1);
					       rs.close();
						   stmt.close();
						   con.commit();
						   con.close();	
						   System.out.println();
						}catch ( Exception e ) {
						   System.err.println( e.getClass().getName() + ": " + e.getMessage() );
						   System.exit(0);
						}
					System.out.println("---------------------------------");
				}else if(flag.equals("����ע��")){
					for (int i = 0; i < c.length; i++) {
						if((c[i] == '#')){
							one =i;
						}else if((c[i] == '|')) {
							two = i;
						}else if((c[i] == '/')){
							three = i;
						}
					}
					System.out.println("---�ͻ����ѳɹ�����---");
					// �õ��ͻ��˵�IP
					System.out.println("�ͻ��˵�IP=" + cSocket.getInetAddress());
					// �õ��ͻ��˵Ķ˿ں�
					System.out.println("�ͻ��˵Ķ˿ں�=" + cSocket.getPort());
					// �õ����ض˿ں�
					System.out.println("���ط������˿ں�=" + cSocket.getLocalPort());
					//�õ��ͻ��˴�����δ���������Ϣ����
					System.out.println("�ͻ���ע���˺�#����|ȷ������/��������:" + clientStr);
					//������Ϣ���ֿ���ţ�				
					String user1 = new String(c, 0, one);
					System.out.println("�ͻ���ע���˺�user:" + user1);
					String password1 = new String(c, one+1, two-one-1);   //��ʼλ�úͳ�������ȡ���ַ���
					System.out.println("�ͻ���ע������password:" + password1);	
					String password2 = new String(c, two+1, three-two-1);   //��ʼλ�úͳ�������ȡ���ַ���
					System.out.println("�ͻ���ȷ������password:" + password2);
					String judge = new String(c, three+1, c.length -three- 1);
					System.out.println("�ͻ��˾������judge:" + judge);
					try{
							System.out.println("���ԽӲ������ܾ���Appע����Ϣ���ݿ⡰");
							Class.forName("org.sqlite.JDBC");
						    con = DriverManager.getConnection("jdbc:sqlite:login.db");
							con.setAutoCommit(false);    //false��sql������ύ��Ӧ�ó����𣬳���������commit����rollback����
							System.out.println("Opened database successfully!!");   //�����ݿ�ɹ���������
						    stmt = con.createStatement();
						    //��ע������ƥ�����ݿ⣬������ڸ��˻�����������������
						    con.setAutoCommit(false);
						    //��ʼ��f=0  �˺��ظ���f=1 ����ȫ�����ݿ�û���ظ����˺���f��=1
						    int f=0;   //��������sqlite���ݿ���˺ź����룬����˺��ظ������������û�У��������Ӧ��ע���˺�����
						    ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
					        while (rs.next() ) {
					        //ȡ��ע��ʱ���û��˺�USER������PASSWORD���¼ʱ���˺�user1������password1���бȽ�
						       String  userdata = rs.getString("user");
					        //System.out.println(userdata);
					           String  passworddata= rs.getString("password");
					        //System.out.println(passworddata);
					        //�����ݿ�����ȡ���˻������룬������ע����˺ţ�������бȶ�
					           if(user1.equals(userdata)){
							      System.out.println("���û��Ѿ�ע������޷��ظ�ע�ᣡ��");
								  dos.writeUTF("���˻��Ѿ�ע�ᣡ��");
								  f=1;
								  break;
							     }
					         }
					           if(f!=1){
					       //�������ݿ⣬�����ݿ��������Ӧ��ע���˺ź�����
					           String sql ="insert into company(id,user,password) values(null,'" + user1 + "','" + password2 + "')"; 
					           stmt.executeUpdate(sql);
									 System.out.println("ע��ɹ�����");
									 dos.writeUTF("ע��ɹ�����");
					           }
					           rs.close();
					           stmt.close();
					           con.commit();
							   con.close();	
							   System.out.println();
						    }catch ( Exception e ) {
							   System.err.println( e.getClass().getName() + ": " + e.getMessage() );
							   System.exit(0);
							}
					     System.out.println("---------------------------------");
			         }
				else if(flag.equals("�һ�����")){
					// �ҵ���һ����ʶ��
					for (int i = 0; i < c.length; i++) {
						if((c[i] == '#')){
							one =i;
						}else if((c[i] == '|')) {
							two = i;
						}else if((c[i] == '/')){
							three = i;
						}
					}
					System.out.println("---�ͻ����ѳɹ�����---");
					// �õ��ͻ��˵�IP
					System.out.println("�ͻ��˵�IP=" + cSocket.getInetAddress());
					// �õ��ͻ��˵Ķ˿ں�
					System.out.println("�ͻ��˵Ķ˿ں�=" + cSocket.getPort());
					// �õ����ض˿ں�
					System.out.println("���ط������˿ں�=" + cSocket.getLocalPort());
					//�õ��ͻ��˴�����δ���������Ϣ����
					System.out.println("�ͻ��������һ�|��֤��/��������:" + clientStr);
					//������Ϣ���ֿ���ţ�				
					String user1 = new String(c, 0, two);
					System.out.println("�ͻ����˺�user:" + user1);
					String ma = new String(c, two+1, three-two-1);   //��ʼλ�úͳ�������ȡ���ַ���
					System.out.println("�ͻ�����֤��captcha:" + ma);	
					String judge = new String(c, three+1, c.length - three - 1);
					System.out.println("�ͻ��˾������judge:" + judge);
						try{
							System.out.println("���ԽӲ������ܾ���Appע����Ϣ���ݿ⡰");
							Class.forName("org.sqlite.JDBC");
						    con = DriverManager.getConnection("jdbc:sqlite:login.db");
							con.setAutoCommit(false);    //false��sql������ύ��Ӧ�ó����𣬳���������commit����rollback����
							System.out.println("Opened database successfully!!");   //�����ݿ�ɹ���������
						    stmt = con.createStatement();
						    //��ע������ƥ�����ݿ⣬������ڸ��˻�����������������
						    con.setAutoCommit(false);
						    int f=0; //��ʼ��f=0  �˺��ظ���f=1 ����ȫ�����ݿ�û���ظ����˺���f��=1
						    ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
					        while (rs.next() ) {
					        //ȡ��ע��ʱ���û��˺�USER������PASSWORD���¼ʱ���˺�user1������password1���бȽ�
						         String  userdata = rs.getString("user");
					        //System.out.println(userdata);
					        //�����ݿ�����ȡ���˻������룬������ע����˺ţ�������бȶ�
					             if((user1.equals(userdata))&&(ma.equals("12345"))){
							       System.out.println("�ɹ��ҵ����û��˺�");
								   dos.writeUTF("�ɹ��һ����룡��");
								   f=1;
								   break;
							     }
					           }
					        if(f==0){
					        	System.out.println("û���ҵ����û��˺�");
					        	dos.writeUTF("����");
					        }
					           rs.close();
					           stmt.close();
					           con.commit();
							   con.close();	
							   System.out.println();
						    }catch ( Exception e ) {
							   System.err.println( e.getClass().getName() + ": " + e.getMessage() );
							   System.exit(0);
							}
						 System.out.println("---------------------------------");
			   }else if(flag.equals("��������")){
					// �ҵ���һ����ʶ��
					for (int i = 0; i < c.length; i++) {
						if((c[i] == '#')){
							one =i;
						}else if((c[i] == '|')) {
							two = i;
						}else if((c[i] == '/')){
							three = i;
						}
					}
					System.out.println("---�ͻ����ѳɹ�����---");
					// �õ��ͻ��˵�IP
					System.out.println("�ͻ��˵�IP=" + cSocket.getInetAddress());
					// �õ��ͻ��˵Ķ˿ں�
					System.out.println("�ͻ��˵Ķ˿ں�=" + cSocket.getPort());
					// �õ����ض˿ں�
					System.out.println("���ط������˿ں�=" + cSocket.getLocalPort());
					//�õ��ͻ��˴�����δ���������Ϣ����
					System.out.println("�ͻ��������һ�|��֤��/��������:" + clientStr);
					//������Ϣ���ֿ���ţ�				
					String user1 = new String(c, 0, two);
					System.out.println("�ͻ����˺�user:" + user1);
					String newpassword = new String(c, two+1, three-two-1);   //��ʼλ�úͳ�������ȡ���ַ���
					System.out.println("�ͻ�����������newpassword:" + newpassword);	
					String judge = new String(c, three+1, c.length - three - 1);
					System.out.println("�ͻ��˾������judge:" + judge);
						try{
							System.out.println("���ԽӲ������ܾ���Appע����Ϣ���ݿ⡰");
							Class.forName("org.sqlite.JDBC");
						    con = DriverManager.getConnection("jdbc:sqlite:login.db");
							con.setAutoCommit(false);    //false��sql������ύ��Ӧ�ó����𣬳���������commit����rollback����
							System.out.println("Opened database successfully!!");   //�����ݿ�ɹ���������
						    stmt = con.createStatement();
						    //��ע������ƥ�����ݿ⣬������ڸ��˻�����������������
						    con.setAutoCommit(false);
						    String sql ="update company set password="+newpassword+" where user="+user1; 
						    //System.out.println(sql);
					        stmt.executeUpdate(sql);
					        System.out.println("��������ɹ�����");
						    dos.writeUTF("���óɹ�");
					        stmt.close();
					        con.commit();
							con.close();	
							System.out.println();
						}catch ( Exception e ) {
							System.err.println( e.getClass().getName() + ": " + e.getMessage() );
							System.exit(0);
					 }
						 System.out.println("---------------------------------");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (dis != null) {
					dis.close();
				}
				if (dos != null) {
					dos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	    
		}
	}
}
