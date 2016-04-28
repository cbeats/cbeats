package Cbeats;

import java.util.Scanner;

public class Sms{
	private Project[] pros = new Project[3];//���ڱ���γ̶���
	private int index;	//��¼������ʵ�ʿγ̵ĸ���

	public void save(Project project){
		if(index >= pros.length){
			//�������չ
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
		System.out.println("********�γ���Ϣ����ϵͳ********");
		System.out.println("*1 ��ѯ���пγ���Ϣ");
		System.out.println("*2 ¼��γ���Ϣ");
		System.out.println("*3 ɾ���γ���Ϣ");
		System.out.println("*4 ͨ����Ų��ҿγ���Ϣ");
		System.out.println("*5 �޸Ŀγ���Ϣ");
		System.out.println("*exit �˳�ϵͳ��");
		System.out.println("*help ��ȡ����");
		System.out.println("********************************");
	}

	public static void main(String[] args){
		Sms sms = new Sms();
		sms.menu();
		//ɨ��������
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.print("*�������Ӧָ��:");
			String option = sc.nextLine();
			switch(option){
				case "1":	//��ѯ����
					System.out.println("���������пγ̵���Ϣ��");
					Project[] pros = sms.queryAll();
					for(Project pro : pros){
						System.out.println(pro);
					}
					System.out.println("�ܹ���ѯ��"+sms.index+"���γ�");
					break;
				case "2":	//¼��
					while(true){
						System.out.println("������γ̵���Ϣ��no#name#numofclasses�������롾break��������һ��Ŀ¼");
						String proStr = sc.nextLine();
						if(proStr.equals("break")){
							break;//���ص���һ��Ŀ¼
						}
						
						String[] proArr = proStr.split("#");
						
						long no  = Long.parseLong(proArr[0]);
						String name = proArr[1];
						int numofclasses = Integer.parseInt(proArr[2]);
						//��װ����
						Project pro = new Project(no,name,numofclasses);
						sms.save(pro);
						System.out.println("����ɹ���");
					}
					break;
				case "3":	//ɾ��
					while(true){
						System.out.println("������Ҫɾ���γ̵ı��,��������break������һ��Ŀ¼");
						String noStr = sc.nextLine();
						if(noStr.equals("break")){
							break;
						}
						long no = Long.parseLong(noStr);
			
						Project pro = sms.queryByNo(no);
						if(pro==null){
							System.out.println("��Ҫɾ�����û���Ϣ�����ڣ�");
							continue;
						}
						sms.deleteByNo(no);
						System.out.println("ɾ���ɹ���");
					}	
					break;
				case "4":	
					while(true){
						System.out.println("������Ҫ����ѧ����id,��������break������һ��Ŀ¼");
						String noStr = sc.nextLine();
						if(noStr.equals("break")){
							break;
						}
						long no = Long.parseLong(noStr);
						Project pro = sms.queryByNo(no);
						System.out.println(pro==null?"sorry,not found!":pro);
					}	
					break;
				case "5":	//�޸�
					while(true){
						System.out.println("������Ҫ�޸Ŀγ̵ı��,��������break������һ��Ŀ¼");
						String noStr = sc.nextLine();
						if(noStr.equals("break")){
							break;
						}
						long no = Long.parseLong(noStr);
		
						Project pro = sms.queryByNo(no);
						if(pro==null){
							System.out.println("��Ҫ�޸ĵ��û���Ϣ�����ڣ�");
							continue;
						}
						System.out.println("ԭ��ϢΪ��"+pro);
						System.out.println("����������Ϣ��name#numofclasses����");
						// tom#12
						String str = sc.nextLine();
						String[] proArr = str.split("#");
						String name = proArr[0];
						int numofclasses = Integer.parseInt(proArr[1]);
						Project newPro = new Project(no,name,numofclasses);
						sms.update(newPro);
						System.out.println("�޸ĳɹ���");
					}	
					break;
				case "exit":
					System.out.println("bye bye,��ӭ�ٴ�ʹ�ã�");
					System.exit(0);
				case "help":
					sms.menu();
					break;
				default:
					System.out.println("�������");

			}
		}
	}
}