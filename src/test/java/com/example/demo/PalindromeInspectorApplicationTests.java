package com.example.demo;

import com.example.demo.entity.textDataset;
import com.example.demo.service.ServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PalindromeInspectorApplicationTests {

	@Autowired
	public ServiceImpl inspector;

	@Test
	public void palindromeTest(){
		String text="madam";
		boolean res=inspector.isPalindrome(text);
		boolean exp=true;
		assertEquals(exp, res);
	}

	@Test
	public void palindromeTest2(){
		String text="apple";
		boolean res=inspector.isPalindrome(text);
		boolean exp=false;
		assertEquals(exp, res);
	}

	@Test
	public void palindromeTest3(){
		String text="aaaa";
		boolean res=inspector.isPalindrome(text);
		boolean exp=true;
		assertEquals(exp, res);
	}

	@Test
	public void palindromeTest4(){
		String text="milk";
		boolean res=inspector.isPalindrome(text);
		boolean exp=false;
		assertEquals(exp, res);
	}

	@Test
	public void palindromeTest5(){
		String text="Able was I ere I saw elba";
		boolean res=inspector.isPalindrome(text);
		boolean exp=true;
		assertEquals(exp, res);
	}

	@Test
	public void validTest(){
		String text="123Number";
		boolean res=inspector.isValid(text);
		boolean exp=false;
		assertEquals(exp,res);
	}

	@Test
	public void validTest2(){
		String text=" spaces ";
		boolean res=inspector.isValid(text);
		boolean exp=false;
		assertEquals(exp,res);
	}

	@Test
	public void validTest3(){
		String text="**punctuation%^&*";
		boolean res=inspector.isValid(text);
		boolean exp=false;
		assertEquals(exp,res);
	}


	@Test
	public void validTest4(){
		String text="Nu123mber";
		boolean res=inspector.isValid(text);
		boolean exp=false;
		assertEquals(exp,res);
	}

	@Test
	public void validTest5(){
		String text="Nu  mber";
		boolean res=inspector.isValid(text);
		boolean exp=false;
		assertEquals(exp,res);
	}

	@Test
	public void validTest6(){
		String text="Number-*/+";
		boolean res=inspector.isValid(text);
		boolean exp=false;
		assertEquals(exp,res);
	}

	@Test
	public void validTest7(){
		String text="Number";
		boolean res=inspector.isValid(text);
		boolean exp=true;
		assertEquals(exp,res);
	}

	@Test
	public void findTest(){
		String text="aaa";
		textDataset res=inspector.findText(text);
		textDataset actual=new textDataset("aaa",true);
		assertEquals(actual,res);
	}

	@Test
	public void insertTest(){
		textDataset dataset = new textDataset();
		dataset.setText("aaa");
		dataset.setResult(true);
		String res=inspector.storeInMongodb(dataset);
		String exp="Inserted successfully";
		assertEquals(exp,res);
	}
}
