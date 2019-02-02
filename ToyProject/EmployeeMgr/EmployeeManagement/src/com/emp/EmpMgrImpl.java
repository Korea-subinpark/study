package com.emp;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class SortedByNo implements Comparator<Employee> {//직원번호로 정렬
	@Override
	public int compare(Employee o1, Employee o2) {
		return o1.getEmpNo() - o2.getEmpNo();
	}
}

public class EmpMgrImpl implements IEmpMgr {	
	
    /** 직원나 매니저의 정보를 저장하기 위한 리스트 */
	private List<Employee> emps = new ArrayList<Employee>();
	private static EmpMgrImpl instance; 

	private EmpMgrImpl() {
		load("emp.dat");
	}
	
	public static EmpMgrImpl getInstance() {
		if(instance == null)
			instance = new EmpMgrImpl();
		return instance;
	}
	
	
	/** 파일로 부터 자료 읽어서 메모리(ArrayList)에 저장하기*/
	public void load(String filename) {
		File  file=new File(filename);
		System.out.println(file);
		if (!file.exists()) return; 
		emps.clear();
		ObjectInputStream ois=null;
		Object ob=null;
		try{	
			ois=new ObjectInputStream(new FileInputStream(file));
			while(true){//마지막 EOF Exception발생
				ob=ois.readObject();
				emps.add((Employee)ob);
			}
		}catch(EOFException ee){System.out.println("읽기 완료");
		}catch(FileNotFoundException fe){
			System.out.println("파일이 존재하지 않습니다");
		}catch(IOException ioe){
			System.out.println(ioe);
		}catch(ClassNotFoundException ce){
			System.out.println("같은 클래스 타입이 아닙니다");
		}finally{
			if(ois !=null){
				try{
					ois.close();
				}catch(IOException oe){System.out.println("파일을 닫는데 실패했습니다");}
			}
		}
	}
  
	public List<Employee>  search(){
		Collections.sort(emps, new SortedByNo());
    	return emps;
    }


	@Override
	public void save(String filename) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for (int i = 0; i < emps.size(); i++) {
				oos.writeObject(emps.get(i));
			}
			System.out.println("저장 완료");
			
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			System.out.println("파일이 존재하지 않습니다");
		} catch (IOException e1) {
			System.out.println("파일을 닫는데 실패했습니다");
		}
	}


	@Override
	public void add(Employee b) throws DuplicateException {
		try {
			exceptionCheck(b.getEmpNo());
		} catch (RecordNotFoundException e) {
			emps.add(b);
		} 
		
	}


	@Override
	public Employee search(int num) throws RecordNotFoundException {
		try {
			exceptionCheck(num);
		} catch (DuplicateException e) {
			for (int i = 0; i < emps.size(); i++) {
				Employee em = emps.get(i);
				if(em.getEmpNo() == num) {
					return em;
				}
			}
		}
		throw new RecordNotFoundException();
	}
	
	public List<Employee> search(String name) throws RecordNotFoundException {
		List<Employee> temp = new ArrayList<>();
		for (int i = 0; i < emps.size(); i++) {
			Employee em = emps.get(i);
			if(em.getName().equals(name)) {
				temp.add(em);
			}
		}
		if(temp == null)
			throw new RecordNotFoundException();
		return temp;
	}

	@Override
	public void update(Employee b) throws RecordNotFoundException {
		try {
			exceptionCheck(b.getEmpNo());
		} catch (DuplicateException e) {
			for (int i = 0; i < emps.size(); i++) {
				Employee em = emps.get(i);
				if(em.getEmpNo() == b.getEmpNo()) {
					em.setName(b.getName());
					em.setPosition(b.getPosition());
					em.setDept(b.getDept());
				}
			}
		}
	}


	@Override
	public void delete(int num) throws RecordNotFoundException {
		try {
			exceptionCheck(num);
		} catch (DuplicateException e) {
			for (int i = 0; i < emps.size(); i++) {
				Employee em = emps.get(i);
				if(em.getEmpNo() == num) {
					emps.remove(em);
				}
			}
		}
	}
	
	public void exceptionCheck(int num) throws DuplicateException, RecordNotFoundException {
		for (int i = 0; i < emps.size(); i++) {
			if(emps.get(i).getEmpNo() == num)
				throw new DuplicateException();
		}
		
		throw new RecordNotFoundException();
	}

}

class DuplicateException extends Exception {
	@Override
	public String toString() {
		return this.getClass().getName() + ":데이터가 중복되었습니다.";
	}
}

class RecordNotFoundException extends Exception {
	@Override
	public String toString() {
		return this.getClass().getName() + ":데이터가 없습니다.";
	}
}
