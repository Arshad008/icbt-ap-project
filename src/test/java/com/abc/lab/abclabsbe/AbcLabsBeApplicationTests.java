package com.abc.lab.abclabsbe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.lab.abclabsbe.Dtos.CreateTestDto;
import com.abc.lab.abclabsbe.Models.User;
import com.abc.lab.abclabsbe.Repositories.TestRepository;
import com.abc.lab.abclabsbe.Repositories.UserRepository;
import com.abc.lab.abclabsbe.Services.AppointmentService;
import com.abc.lab.abclabsbe.Services.TestService;
import com.abc.lab.abclabsbe.Services.UserService;

@SpringBootTest
class AbcLabsBeApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private TestService testService;

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private TestRepository testRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	void registerUserTest() {
		try{
			System.out.println("User registration test: started");
			Map<String, String> userData = new HashMap<>();

			userData.put("firstName", "Will");
			userData.put("lastName", "Smith");
			userData.put("dateOfBirth", "1995-10-10");
			userData.put("gender", "Male");
			userData.put("email", "willsmith@gmail.com");
			userData.put("phoneNumber", "0769638521");
			userData.put("password", "12345678");
			userData.put("address", "17/3, Mosque Road, Mount Lavinia");

			userService.registerUser(userData);

			System.out.println("User registration test: passed");
		} catch(Exception err) {
			System.out.println("User registration test: failed");
			System.out.println(err);
		}
	}

	@Test
	void loginUserTest() {
		try {
			System.out.println("Add test: started");
			userService.loginUser("willsmith@gmail.com", "12345678");
			System.out.println("User login test: passed");
		} catch (Exception err) {
			System.out.println("User login test: failed");
			System.out.println(err);
		}
	}

	@Test
	void addTest() {
		try {
			System.out.println("Add test: started");

			CreateTestDto testData = new CreateTestDto();
			List<String> testLabels = new ArrayList<String>();
			testLabels.add("Fasting Blood Sugar (FBS): 70 - 99 mg/dL");

			testData.setName("Blood Glucose");
			testData.setDescription("Measures the amount of glucose (sugar) in the blood to assess diabetes or monitor blood sugar levels.");
			testData.setPrice(2200.00);
			testData.setTestLabels(testLabels);

			testService.createTest(testData);

			System.out.println("Add test: passed");
		} catch (Exception err) {
			System.out.println("Add test: failed");
			System.out.println(err);
		}
	}

	@Test
	void deleteTest() {
		try {
			System.out.println("Delet test: started");

			testService.deleteTest("65e2d84cac8603056481b665");

			System.out.println("Delet test: passed");
		} catch (Exception err) {
			System.out.println("Delet test: failed");
			System.out.println(err);
		}
	}

	@Test
	void bookAppointment() {
		try {
			System.out.println("Book appointment test: started");

			Optional<User> userOptional = userRepository.findById("65e2d2d19c4a642017268edc");
			Optional<com.abc.lab.abclabsbe.Models.Test> testOptional = testRepository.findById("65dc1582377adf18d59e7b54");

			if (testOptional.isPresent() && userOptional.isPresent()) {
				User user = userOptional.get();
      	com.abc.lab.abclabsbe.Models.Test test = testOptional.get();

				appointmentService.bookAppointment("2024-03-10", test, user);
			}

			System.out.println("Book appointment test: passed");
		} catch (Exception err) {
			System.out.println("Book appointment test: failed");
			System.out.println(err);
		}
	}

	@Test
	void collectSampleTest() {
		try {
			System.out.println("Collect sample test: started");

			appointmentService.updateStatus("65e2dcfd679ae576011dd580", "Sample Collected");

			System.out.println("Collect sample test: passed");
		} catch (Exception err) {
			System.out.println("Collect sample test: failed");
			System.out.println(err);
		}
	}
	
	@Test
	void upateTestResultTest() {
		try {
			System.out.println("Update test result test: started");

			TestData testDataOne = new TestData();
			testDataOne.setLabel("Less than 5.7%");
			testDataOne.setValue("5");


			List<Object> testDataList = new ArrayList<Object>();

			appointmentService.updateTestData("65e2dcfd679ae576011dd580", testDataList);

			System.out.println("Update test result test: passed");
		} catch (Exception err) {
			System.out.println("Update test result test: failed");
			System.out.println(err);
		}
	}
}

class TestData {
	private String label;
	private String value;

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
