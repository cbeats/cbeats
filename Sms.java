package Cbeats;

import java.util.Scanner;

public class Sms{
	private Project[] pros = new Project[3];//用于保存课程对象
	private int index;	//记录数组中实际课程的个数

	public void save(Project project){
		if(index >= pros.length){
			//数组的扩展
			Project[] demo = new Project[pros.length+3];
			System.arraycopy(pros,0,demo,0,index);
			pros = demo;
		}
		pros[index++] = project;
	}

    
	public void update(Project project){
		for(int i=0;i<index;i++){
			if(project.getNo() == pros[i].getNo()){
				pros[i].setName(project.getName());
			}
		}
	}

	
    public void deleteByNo(long no){
		int num = getIndexByNo(no);
		for(int i=num ;i<index-1;i++){
			pros[i] = pros[i+1];
		}
		pros[--index] = null;
	}

	
	public Project[] queryAll(){
		Project[] demo = new Project[index];
		System.arraycopy(pros,0,demo,0,index);
		return demo;
	}

	
	public Project queryByNo(long no){
		int num = getIndexByNo(no);
		return num==-1?null:pros[num];
	}

	
	private int getIndexByNo(long no){
		int num = -1;
		for(int i=0;i<index;i++){
			if(pros[i].getNo() == no){
				num = i;
				break;
			}
		}
		return num;
	}

	
	public void menu(){
		System.out.println("********课程信息管理系统********");
		System.out.println("*1 查询所有课程信息");
		System.out.println("*2 录入课程信息");
		System.out.println("*3 删除课程信息");
		System.out.println("*4 通过编号查找课程信息");
		System.out.println("*5 修改课程信息");
		System.out.println("*exit 退出系统！");
		System.out.println("*help 获取帮助");
		System.out.println("********************************");
	}

	public static void main(String[] args){
		Sms sms = new Sms();
		sms.menu();
		//扫描器对象
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.print("*请输入对应指令:");
			String option = sc.nextLine();
			switch(option){
				case "1":	//查询所有
					System.out.println("以下是所有课程的信息：");
					Project[] pros = sms.queryAll();
					for(Project pro : pros){
						System.out.println(pro);
					}
					System.out.println("总共查询到"+sms.index+"个课程");
					break;
				case "2":	//录入
					while(true){
						System.out.println("请输入课程的信息【no#name#numofclasses】或输入【break】返回上一级目录");
						String proStr = sc.nextLine();
						if(proStr.equals("break")){
							break;//返回到上一级目录
						}
						
						String[] proArr = proStr.split("#");
						
						long no  = Long.parseLong(proArr[0]);
						String name = proArr[1];
						int numofclasses = Integer.parseInt(proArr[2]);
						//封装对象
						Project pro = new Project(no,name,numofclasses);
						sms.save(pro);
						System.out.println("保存成功！");
					}
					break;
				case "3":	//删除
					while(true){
						System.out.println("请输入要删除课程的编号,或者输入break返回上一级目录");
						String noStr = sc.nextLine();
						if(noStr.equals("break")){
							break;
						}
						long no = Long.parseLong(noStr);
			
						Project pro = sms.queryByNo(no);
						if(pro==null){
							System.out.println("您要删除的用户信息不存在！");
							continue;
						}
						sms.deleteByNo(no);
						System.out.println("删除成功！");
					}	
					break;
				case "4":	
					while(true){
						System.out.println("请输入要查找学生的id,或者输入break返回上一级目录");
						String noStr = sc.nextLine();
						if(noStr.equals("break")){
							break;
						}
						long no = Long.parseLong(noStr);
						Project pro = sms.queryByNo(no);
						System.out.println(pro==null?"sorry,not found!":pro);
					}	
					break;
				case "5":	//修改
					while(true){
						System.out.println("请输入要修改课程的编号,或者输入break返回上一级目录");
						String noStr = sc.nextLine();
						if(noStr.equals("break")){
							break;
						}
						long no = Long.parseLong(noStr);
		
						Project pro = sms.queryByNo(no);
						if(pro==null){
							System.out.println("您要修改的用户信息不存在！");
							continue;
						}
						System.out.println("原信息为："+pro);
						System.out.println("请输入新信息【name#numofclasses】：");
						// tom#12
						String str = sc.nextLine();
						String[] proArr = str.split("#");
						String name = proArr[0];
						int numofclasses = Integer.parseInt(proArr[1]);
						Project newPro = new Project(no,name,numofclasses);
						sms.update(newPro);
						System.out.println("修改成功！");
					}	
					break;
				case "exit":
					System.out.println("bye bye,欢迎再次使用！");
					System.exit(0);
				case "help":
					sms.menu();
					break;
				default:
					System.out.println("输入错误！");

			}
		}
	}
}