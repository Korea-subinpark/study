package com.emp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EmpUI implements ActionListener {
	private JFrame frame;
	private JList list;
	private JTextField txtEmpNo;
	private JTextField txtName;
	private JTextField txtDept;
	private JTextField txtPosition;
	private JLabel lblStatus;
	private JButton btnAdd;
	private JButton btnSearch;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnSave;
	private JButton btnUpload;
	private JButton btnClear;

	IEmpMgr mgr = EmpMgrImpl.getInstance();

	public EmpUI() {
		frame = new JFrame("EmpManager Client");
		lblStatus = new JLabel(" ");
		lblStatus.setBackground(Color.LIGHT_GRAY);
		lblStatus.setForeground(Color.BLUE);
		txtEmpNo = new JTextField();
		txtName = new JTextField();
		txtPosition = new JTextField();
		txtDept = new JTextField();
		btnAdd = new JButton("Add");
		btnUpdate = new JButton("Update");
		btnSearch = new JButton("Search");
		btnClear = new JButton("Clear");
		btnDelete = new JButton("Delete");
		btnSave = new JButton("SaveToFile");
		btnUpload = new JButton("SendToServer");
		list = new JList();

		JPanel inputs = new JPanel();
		JPanel inputLables = new JPanel();
		JPanel inputFields = new JPanel();
		JPanel inputBtns = new JPanel();
		inputLables.setLayout(new GridLayout(4, 1));
		JLabel lblEmpNo = new JLabel("EmpNo");
		lblEmpNo.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblGrade = new JLabel("Position");
		lblGrade.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblDiv = new JLabel("Department");
		lblDiv.setHorizontalAlignment(SwingConstants.CENTER);
		inputLables.add(lblEmpNo);
		inputLables.add(lblName);
		inputLables.add(lblGrade);
		inputLables.add(lblDiv);
		inputFields.setLayout(new GridLayout(4, 1));
		inputFields.add(txtEmpNo);
		inputFields.add(txtName);
		inputFields.add(txtPosition);
		inputFields.add(txtDept);
		inputBtns.setLayout(new GridLayout(1, 4));
		inputBtns.add(btnAdd);
		inputBtns.add(btnUpdate);
		inputBtns.add(btnDelete);
		inputBtns.add(btnSearch);
		inputBtns.add(btnClear);

		inputs.setLayout(new BorderLayout());
		inputs.add(inputLables, BorderLayout.WEST);
		inputs.add(inputFields, BorderLayout.CENTER);
		inputs.add(inputBtns, BorderLayout.SOUTH);

		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		center.add(list);
		center.add(lblStatus, BorderLayout.NORTH);

		JPanel pnlBtns = new JPanel();
		pnlBtns.setLayout(new GridLayout(1, 4));
		pnlBtns.add(btnSave);
		pnlBtns.add(btnUpload);

		frame.add(inputs, BorderLayout.NORTH);
		frame.add(center);
		frame.add(pnlBtns, BorderLayout.SOUTH);
		frame.setSize(500, 400);
		frame.setVisible(true);
		showList();

	}

	/** 이벤트를 등록합니다. */
	public void addEvent() {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		btnAdd.addActionListener(this);
		btnClear.addActionListener(this);
		btnDelete.addActionListener(this);
		btnSave.addActionListener(this);
		btnSearch.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnUpload.addActionListener(this);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent ee) {
				if (list.getSelectedValue() == null)
					return;
				Employee e = (Employee) list.getSelectedValue();
				txtEmpNo.setText(e.getEmpNo() + "");
				txtName.setText(e.getName());
				txtPosition.setText(e.getPosition());
				txtDept.setText(e.getDept());
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			addRecord();
		} else if (e.getSource() == btnUpdate) {
			updateRecord();
		} else if (e.getSource() == btnSearch) {
			searchRecord();
		} else if (e.getSource() == btnClear) {
			clearField();
			lblStatus.setText(" ");
		} else if (e.getSource() == btnDelete) {
			deleteRecord();
		} else if (e.getSource() == btnSave) {
			saveRecord();
		} else if (e.getSource() == btnUpload) {
			new EmpClient(mgr.search()).start();
		}

	}

	private void addRecord() {
		String empNo = txtEmpNo.getText().trim();
		String name = txtName.getText().trim();
		String position = txtPosition.getText().trim();
		String dept = txtDept.getText().trim();
		if (empNo.equals("") || name.equals("") || position.equals("") || dept.equals("")) {
			lblStatus.setText("모든 항목를 입력해 주세요.");
			return;
		}
		try {
			mgr.add(new Employee(Integer.parseInt(empNo), name, position, dept));
			lblStatus.setText("등록 성공");
			showList();
			clearField();
		} catch (NumberFormatException e) {
			lblStatus.setText("EmpNo은 숫자로 입력해주세요");
			return;
		} catch (DuplicateException e) { 
			lblStatus.setText(e.toString());
		}
	}

	private void updateRecord(){
		String empNo=txtEmpNo.getText().trim();
		String name=txtName.getText().trim();
		String position=txtPosition.getText().trim();
		String dept=txtDept.getText().trim();
		if (empNo.equals("") || name.equals("") || position.equals("") || dept.equals("")) {
			lblStatus.setText("모든 항목를 입력해 주세요.");
			return;
		}
		try {
			Employee tmp = new Employee(Integer.parseInt(empNo),name,position,dept);
			mgr.update(tmp);
			lblStatus.setText("수정 성공");			
			showList();
			clearField();
		} catch (NumberFormatException e) {
			lblStatus.setText("EmpNo은 숫자로 입력해주세요");
			return;
		} catch (RecordNotFoundException e) {               
			lblStatus.setText(e.toString());           
		}
		
	}

	private void searchRecord() {
		String empNo=txtEmpNo.getText().trim();
		String name=txtName.getText().trim();
		String position=txtPosition.getText().trim();
		String dept=txtDept.getText().trim();
		
		if (empNo.equals("") && name.equals("") && position.equals("") && dept.equals("")) {
			showList();
			lblStatus.setText("전체 검색");
			return;
		}
		
		if(!name.equals("")) {
			try {
				List<Employee> temp = mgr.search(name);
				list.setListData(temp.toArray());
				lblStatus.setText("검색 완료");
				clearField();
				return;
			} catch (RecordNotFoundException e) {
				lblStatus.setText(e.toString());  
			}
		}
		
		if (empNo.equals("") && (position.equals("") || dept.equals(""))) {
			lblStatus.setText("직원 번호를 입력해 주세요.");
			return;
		}
		
		try {
			Employee tmp = mgr.search(Integer.parseInt(empNo));
			txtEmpNo.setText(tmp.getEmpNo() + "");
			txtName.setText(tmp.getName());
			txtPosition.setText(tmp.getPosition());
			txtDept.setText(tmp.getDept());
			lblStatus.setText("검색 성공");
		} catch (NumberFormatException e) {
			lblStatus.setText("EmpNo은 숫자로 입력해주세요");
			return;
		} catch (RecordNotFoundException e) {
			lblStatus.setText(e.toString());  
		}

	}

	private void deleteRecord() {
		String empNo=txtEmpNo.getText().trim();
		if(empNo.equals("")) {
			lblStatus.setText("직원 번호를 입력해 주세요.");
			return;
		}
		try {
			mgr.delete(Integer.parseInt(empNo));
			lblStatus.setText("삭제 성공");			
			showList();
			clearField();
		} catch (NumberFormatException e) {
			lblStatus.setText("EmpNo은 숫자로 입력해주세요");
			return;
		} catch (RecordNotFoundException e) {
			lblStatus.setText(e.toString());  
		}

	}

	private void saveRecord() {
		try {
			mgr.save("emp.dat");
			lblStatus.setText("저장 성공");
		} catch (Exception e) {
			lblStatus.setText(e.toString());
		}
	}

	/** AWT List 컴포넌트에 직원정보를 표시합니다. */
	private void showList() {
		List<Employee> ee = mgr.search();
		list.removeAll();
		list.setListData(ee.toArray());
	}

	/** 직원정보를 입력하는 TextField의 내용을 지움니다. */
	private void clearField() {
		txtEmpNo.setText(" ");
		txtName.setText(" ");
		txtPosition.setText(" ");
		txtDept.setText(" ");

	}

	public static void main(String[] args) {
		EmpUI ui = new EmpUI();
		ui.addEvent();
	}

	class EmpClient extends Thread {
		Socket client;
		OutputStream out;
		ObjectOutputStream oos;
		List<Employee> emps;

		public EmpClient(List<Employee> temp) {
			this.emps = temp;
		}

		public void run() {
			try {
				client = new Socket("localhost", 6000);
				out = client.getOutputStream();
				oos = new ObjectOutputStream(out);
				oos.writeObject(emps);
				System.out.println("전송 완료");
				oos.close();
				out.close();
				client.close();
			} catch (UnknownHostException e) {
				System.out.println("host를 알 수 없습니다");
			} catch (IOException e) {
				System.out.println("Stream을 닫는데 실패하였습니다");
			}
		}

	}

}
